package com.example.gatsu.theevent;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Timeline extends AppCompatActivity {
    private String serverUrl = "http://distro.mx/TheEvent/webservices/the3v3nt.php";
    public SharedPreferences datosPersistentes;
    ProgressDialog pDialog;
    private String[] nombres;
    private String[] dias;
    private String[] mensajes;
    private String[] comentarios;
    private String[] likes;
    private String id;
    JSONObject res;
    final String[] data ={"Opcion 1","Opcion 2",};
    ListView eventContainer;
    ArrayList<Bitmap> imagenes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.timelinemenu, data);

        final Button btnDrawerTimeline = (Button) findViewById(R.id.btnDrawerTimeline);
        final Button btnDrawerTimelineClose = (Button) findViewById(R.id.btnDrawerTimelineClose);


        new AsyncEventos().execute("timeline");

//        eventContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                Intent intent = new Intent(Timeline.this, Evento.class);
//                startActivity(intent);
//            }
//
//        });
        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        final ListView navList = (ListView) findViewById(R.id.drawer);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override @SuppressWarnings("Deprecation")
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){

                });
                switch(pos){
                    case 1:


                        break;
                    case 2:
                        drawer.closeDrawer(navList);
                        break;

                }
                //drawer.closeDrawer(navList);
            }
        });

        final Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.up_bottom);

        btnDrawerTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.setVisibility(View.VISIBLE);
                drawer.openDrawer(GravityCompat.START);
                btnDrawerTimeline.setVisibility(View.GONE);
                drawer.startAnimation(bottomUp);
                btnDrawerTimelineClose.setVisibility(View.VISIBLE);
            }
        });

        btnDrawerTimelineClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(navList);
                drawer.setVisibility(View.GONE);
                btnDrawerTimeline.setVisibility(View.VISIBLE);
                btnDrawerTimelineClose.setVisibility(View.GONE);
            }
        });

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            InputStream input = connection.getInputStream();
            options.inSampleSize = calculateInSampleSize(options, 50, 50);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(input,null,options);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject obtenerPosts() {
        datosPersistentes = getSharedPreferences("The3v3nt", Context.MODE_PRIVATE);
        id = datosPersistentes.getString("idusrThe3v3nt","");
        Log.v("ESTE ES EL IDUSUARIO",id);
        JSONData conexion = new JSONData();
        JSONObject respuesta = null;
        try {
            respuesta = conexion.conexionServidor(serverUrl, "action=timeline&idusuario=" + id);

            if (respuesta.getString("success").equals("OK")) {

                JSONArray posts = respuesta.getJSONArray("posts");
                nombres = new String[posts.length()];
                dias = new String[posts.length()];
                mensajes = new String[posts.length()];
                comentarios = new String[posts.length()];
                likes = new String[posts.length()];
                imagenes.clear();


                int i = 0;

                while (i < posts.length()) {
                    JSONObject post = posts.getJSONObject(i);
                    nombres[i] = post.getString("nombre");
                    dias[i] = post.getString("fecha");
                    mensajes[i] = post.getString("post");
                    comentarios[i] = post.getString("numcomentarios");
                    likes[i] = post.getString("numlikes");
                    String remotePath = "http://distro.mx/TheEvent/imagenes/timeline/" + post.getString("imagen");
                    Bitmap myBitMap = getBitmapFromURL(remotePath);
                    imagenes.add(myBitMap);
                    i++;
                }

            }
            return respuesta;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    public class AsyncEventos extends AsyncTask<String, String, String> {

        public AsyncEventos() {
            //set context variables if required
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            eventContainer = (ListView) findViewById(R.id.eventContainerTimeline);
            pDialog = new ProgressDialog(Timeline.this);
            // Set progressbar message
            pDialog.setMessage("Cargando Biografia...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            // Show progressbar
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            //String urlString = params[0]; // URL to call

            String resultToDisplay = "";

            InputStream in = null;
            try {
                switch (params[0]) {
                    case "timeline":
                        res = obtenerPosts();
                        return res.getString("success");
                    case "eventimg":
                        String remotePath = params[1];
                        return "OK";
                }


            } catch (Exception e) {

                System.out.println(e.getMessage());

                return e.getMessage();

            }

            try {
                return res.getString("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Error";
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            AdapterTimeline adapter = new AdapterTimeline(Timeline.this, nombres, dias, mensajes, imagenes, comentarios, likes);
            eventContainer.setAdapter(adapter);

        }
    }
}

