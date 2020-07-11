package dev.muldev.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    public static final String RUTA_API = "http://192.168.1.133:8080/login?username=";

    private EditText et_username;
    private EditText et_pass;

    public static String token = "";
    public static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);



        et_username = findViewById(R.id.etUsername);
        et_pass = findViewById(R.id.etPass);


    }

    public void cambiaFragment(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, f)
                .addToBackStack(null)
                .commit();
    }

    public void login(View v){
        try {
            boolean res = new Login(et_username.getText().toString(), et_pass.getText().toString()).execute().get();
            if (!res){
                error();
            }
            else{
                cambiaFragment(new PerfilFragment(username));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void error(){
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Error en el login!")
                .show();
    }



}

class Login extends AsyncTask<Void,Void,Boolean> {

    private String username;
    private String pass;

    public Login(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API de login

        try{
            URL url = new URL(MainActivity.RUTA_API+username+"&password=" + pass);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write(0);
            out.close();

            int responseCode = httpCon.getResponseCode();
            //segun en responsecode sabremos si nos hemos logueado o no
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                String str = br.readLine();
                JSONObject json = new JSONObject(str);
                MainActivity.token = json.getString("token");
                MainActivity.username = username;
                return true;

            } else if (responseCode == 401) {
                return false;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}

