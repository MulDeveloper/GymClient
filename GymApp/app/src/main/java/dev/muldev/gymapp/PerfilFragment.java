package dev.muldev.gymapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.navigation.ui.AppBarConfiguration;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.muldev.gymapp.Modelos.ClienteMuestra;


public class PerfilFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static String username;
    public AppBarConfiguration appbar;

    public static final String RUTA_API_PERFIL = "http://192.168.1.133:8080/api/cliente/get?nomusu=";
    public static final String RUTA_API_ID_TARIFA = "http://192.168.1.133:8080/api/cliente/get/tarifa/";
    public static final String RUTA_API_GET_TARIFA = "http://192.168.1.133:8080/api/tarifa/";
    public static final String RUTA_API_MODIFICA = "http://192.168.1.133:8080/api/cliente/modifica?token=";
    public static final String RUTA_API_MODIFICA_ACCESO = "http://192.168.1.133:8080/api/cliente/modificaAcceso?nuevapass=";

    public static ClienteMuestra cliente;

    public static TextView tvnomape;
    public static TextView tvnif;
    public static TextView tvTarifa;

    public static EditText etTel;
    public static EditText etDir;
    public static EditText etPass;

    private Button btActualiza;

    public static Activity activity;



    public PerfilFragment(String username) {
        this.username = username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView user = vista.findViewById(R.id.nomape);
        user.setText(this.username);

        BottomNavigationView navView = vista.findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        appbar = new AppBarConfiguration.Builder(R.id.nav_view).build();
        navView.setItemIconTintList(null);

        tvnomape = vista.findViewById(R.id.nomape);
        tvnif = vista.findViewById(R.id.nif);
        tvTarifa = vista.findViewById(R.id.tv_tarifa);

        etTel = vista.findViewById(R.id.et_tel);
        etPass = vista.findViewById(R.id.et_pass);
        etDir = vista.findViewById(R.id.et_dir);

        btActualiza = vista.findViewById(R.id.bt_mod);

        btActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacion();
            }
        });

        activity = getActivity();



        //hacemos la peticion a la API para obtener los datos del cliente y populamos

        new CargaPerfil(username).execute();



        

        return vista;
    }

    public void cambiaFragment(Fragment f){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, f)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_clases:
                cambiaFragment(new ClasesFragment());
                break;

            case R.id.nav_salir:
                MainActivity.token = "";
                MainActivity.username = "";
                activity.finish();
                Intent a = new Intent(getContext(),MainActivity.class);
                startActivity(a);
                break;

        }

        return false;
    }

    public static void actualizaVista(){
        //populamos la vista con los datos del cliente
        //hacemos la peticion para obtener la tarifa que tiene contratada

        tvnomape.setText(cliente.getNombreCliente()+ " "+ cliente.getApellidoCliente());
        tvnif.setText(cliente.getNifCliente());
        etTel.setText(""+cliente.getTelCliente());
        etDir.setText(cliente.getDireccionCliente());

        new GetTarifa().execute();

    }

    public void actualiza(){

        //creamos el json y se lo pasamos a la asyncatask

        JSONObject jsonCliente = new JSONObject();

        try {
            jsonCliente.put("idcliente", cliente.getIdCliente());
            jsonCliente.put("nombreCliente", cliente.getNombreCliente());
            jsonCliente.put("apellidoCliente", cliente.getApellidoCliente());
            jsonCliente.put("emailCliente", cliente.getEmailCliente());
            jsonCliente.put("nifCliente", cliente.getNifCliente());
            //modificamos telefono y direccion
            jsonCliente.put("telCliente", Integer.parseInt(etTel.getText().toString()));
            jsonCliente.put("direccionCliente", etDir.getText().toString());

            jsonCliente.put("idMatricula", cliente.getIdMatricula());
            jsonCliente.put("fechaNacimiento", cliente.getFechaNacimiento());

            boolean modifica = new Modifica(jsonCliente).execute().get();

            if (!etPass.getText().toString().equals("")){
                //se quiere modificar la password, atacamos el endpoint de modificacion de acceso
                new ModificaAcceso().execute();

            }

            if (modifica){
                correcto();
            }
            else{
                error();
            }



        }
        catch (JSONException e){
            System.out.println("error json");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void confirmacion(){
        //preguntamos si queremos modificar los datos

        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Quieres modificar?")
                .setContentText("Quieres modificar los datos?")
                .setConfirmText("Si!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        //llamamos a la funcion
                        actualiza();
                    }
                })
                .setCancelButton("Cancelar", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    public void correcto(){
        new SweetAlertDialog(getActivity())
                .setTitleText("Modificado correctamente!")
                .show();

    }

    public void error(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("No se han podido modificar los datos")
                .show();
    }
}

class GetTarifa extends AsyncTask<Void,Void,Boolean> {


    public GetTarifa() {

    }

    @SuppressLint("WrongThread")
    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API de login

        String respuesta="";
        String tarifa="";

        try{
            URL url = new URL(PerfilFragment.RUTA_API_ID_TARIFA + PerfilFragment.cliente.getIdMatricula());
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(httpCon.getInputStream());
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                    respuesta += line;
            }

            in.close();

            int id = Integer.parseInt(respuesta);

            URL urlTarifa = new URL(PerfilFragment.RUTA_API_GET_TARIFA + id);
            HttpURLConnection connection = (HttpURLConnection) urlTarifa.openConnection();

            InputStream input = new BufferedInputStream(connection.getInputStream());
            if (input != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                    tarifa += line;
            }

            JSONObject json = new JSONObject(tarifa);

            //ponemos el valor de descripcion en el spinner no editable
            System.out.println(json.getString("descripcion"));

            PerfilFragment.tvTarifa.setText(json.getString("descripcion") + " - " + json.getString("totalIva")+"€");


            input.close();



        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }


}

class Modifica extends AsyncTask<Void,Void,Boolean> {

    private JSONObject json;

    public Modifica(JSONObject json) {
        this.json = json;
    }

    @SuppressLint("WrongThread")
    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API de login

        try{
            URL url = new URL(PerfilFragment.RUTA_API_MODIFICA + MainActivity.token);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream(), "UTF-8");
            out.write(json.toString());
            out.flush();
            out.close();

            int responseCode = httpCon.getResponseCode();

            System.out.println(responseCode);



        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}

class ModificaAcceso extends AsyncTask<Void,Void,Boolean> {


    public ModificaAcceso() {
    }

    @SuppressLint("WrongThread")
    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API

        try{
            URL url = new URL(PerfilFragment.RUTA_API_MODIFICA_ACCESO + PerfilFragment.etPass.getText().toString() + "&idcliente="
                    + PerfilFragment.cliente.getIdCliente()
                    +"&token=" + MainActivity.token);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write(0);
            out.close();

            int responseCode = httpCon.getResponseCode();

            if (responseCode == 200) {
                return true;

            } else{
                return false;
            }



        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}




class CargaPerfil extends AsyncTask<Void,Void,Boolean> {

    private String username;

    public CargaPerfil(String username) {
        this.username = username;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API de login

        String respuesta="";

        try{
            URL url = new URL(PerfilFragment.RUTA_API_PERFIL + username + "&token=" + MainActivity.token);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(httpCon.getInputStream());
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                    respuesta += line;
            }

            JSONObject json = new JSONObject(respuesta);

            PerfilFragment.cliente = new ClienteMuestra(
                    json.getLong("idcliente"),
                    json.getString("nombreCliente"),
                    json.getString("apellidoCliente"),
                    json.getString("emailCliente"),
                    json.getString("nifCliente"),
                    json.getLong("telCliente"),
                    json.getString("direccionCliente"),
                    json.getLong("idMatricula"),
                    json.getString("fechaNacimiento")

            );

            //llamamos a la funcion actualizar vista


            in.close();

            PerfilFragment.actualizaVista();


        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}
