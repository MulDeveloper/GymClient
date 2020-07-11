package dev.muldev.gymapp.Adaptador;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.muldev.gymapp.MainActivity;
import dev.muldev.gymapp.Modelos.Clase;
import dev.muldev.gymapp.R;

public class AdaptadorClase extends RecyclerView.Adapter<AdaptadorClase.MiViewHolder> {

    private ArrayList<Clase> lista;
    public static final String RUTA_IMAGENES = "https://voka.muldev.dev/files/";
    public static final String RUTA_API_RESERVA = "http://192.168.1.133:8080/api/clases";


    public static class MiViewHolder extends RecyclerView.ViewHolder {

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void enlazaItems(Clase c) {

            TextView titulo = itemView.findViewById(R.id.nomClase);
            ImageView img = itemView.findViewById(R.id.fotoClase);
            TextView hora = itemView.findViewById(R.id.hora);
            TextView duracion = itemView.findViewById(R.id.duracion);


            titulo.setText(c.getDescripcion());
            hora.setText(c.getHoraClase());
            duracion.setText(c.getDuracion()+"min");

            String ruta = RUTA_IMAGENES + c.getDescripcion().toLowerCase() + ".jpg";

            Glide.with(itemView.getContext())
                    .load(ruta)
                    .into(img);


        }
    }


    public AdaptadorClase(ArrayList<Clase> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public AdaptadorClase.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contenido_item_clases, parent, false);
        return new AdaptadorClase.MiViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorClase.MiViewHolder holder, final int position) {
        holder.enlazaItems(lista.get(position));

        //reservamos la clase
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //asynctask de reserva de clase
                try {
                    boolean respuesta = new Reserva(lista.get(position).getId()).execute().get();
                    if(respuesta){
                        //hemos reservado correctamente
                        new SweetAlertDialog(holder.itemView.getContext(),SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("OK!")
                                .setContentText("Clase reservada correctamente!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null).show();

                    }
                    else{
                        new SweetAlertDialog(holder.itemView.getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("ERROR!")
                                .setContentText("Ya habias reservado esta clase!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });




    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

}

class Reserva extends AsyncTask<Void,Void,Boolean> {

    private int idClase;

    public Reserva(int idClase) {
        this.idClase = idClase;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        //peticion a la API de login

        try{
            URL url = new URL(AdaptadorClase.RUTA_API_RESERVA+"?nomusu="+MainActivity.username
                    +"&id=" + idClase +
                    "&token=" + MainActivity.token);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write(0);
            out.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            String str = br.readLine();

            if (str.equals("true")) {
                return true;
            }
            else{
                return false;
            }



        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
