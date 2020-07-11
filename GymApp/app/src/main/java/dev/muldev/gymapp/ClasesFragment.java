package dev.muldev.gymapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.muldev.gymapp.Adaptador.AdaptadorClase;
import dev.muldev.gymapp.Modelos.Clase;


public class ClasesFragment extends Fragment implements AdapterView.OnItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Clase> clases;
    public static Spinner spinner;

    public static List<String> dias;

    public static final String RUTA_API_CLASES = "http://192.168.1.133:8080/api/clases/dia/";

    public static Activity ac;

    public AppBarConfiguration appbar;

    public ClasesFragment() {
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_clases, container, false);


        //recycler view

        recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerClases);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        //spinner con los 7 dias siguientes

        spinner = vista.findViewById(R.id.spinner_dia);

        dias = generaDias();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, dias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        ac = getActivity();

        //nav view volver

        BottomNavigationView navView = vista.findViewById(R.id.nav_view_clases);
        navView.setOnNavigationItemSelectedListener(this);
        appbar = new AppBarConfiguration.Builder(R.id.nav_view_clases).build();
        navView.setItemIconTintList(null);


        return vista;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public List generaDias(){

        //desde hoy
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String hoy = now.format(formatter);
        String uno = now.plusDays(1).format(formatter);
        String dos = now.plusDays(2).format(formatter);
        String tres = now.plusDays(3).format(formatter);
        String cuatro = now.plusDays(4).format(formatter);
        String cinco = now.plusDays(5).format(formatter);
        String seis = now.plusDays(6).format(formatter);

        return Arrays.asList(hoy, uno, dos,tres,cuatro,cinco,seis);




    }

    public static void cargaClases(){

        ac.runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 mAdapter = new AdaptadorClase(clases);
                                 recyclerView.setAdapter(mAdapter);
                                }
                             }
                         );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(dias.get(position));
        new CargaClases(dias.get(position)).execute();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ac.onBackPressed();
        return false;
    }
}

class CargaClases extends AsyncTask<Void,Void,Boolean> {

    private String fecha;

    public CargaClases(String fecha) {
        this.fecha = fecha;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API de login

        String respuesta="";

        ClasesFragment.clases = new ArrayList<>();

        try{
            URL url = new URL(ClasesFragment.RUTA_API_CLASES+fecha);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(httpCon.getInputStream());
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                    respuesta += line;
            }

            JSONArray jsonArray = new JSONArray(respuesta);

            for (int i=0;i<jsonArray.length();i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Clase clase = new Clase(
                            obj.getInt("idclase"),
                            obj.getString("horaClase"),
                            obj.getString("duracion"),
                            obj.getString("descripcion"),
                            obj.getJSONArray("listaClientes").length()
                    );

                    ClasesFragment.clases.add(clase);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


                in.close();

            ClasesFragment.cargaClases();


        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}
