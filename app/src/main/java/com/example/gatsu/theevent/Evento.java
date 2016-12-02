package com.example.gatsu.theevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Evento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        ListView eventContainer = (ListView) findViewById(R.id.eventContainer);
        TextView selectEvento = (TextView) this.findViewById(R.id.seleccionaEvento);
        TextView misEventos = (TextView) this.findViewById(R.id.misEventos);
        ImageView agregarbtn = (ImageView) this.findViewById(R.id.btnagregarimg);
        selectEvento.setTypeface(CustomFontsLoader.getTypeface(this,CustomFontsLoader.Bold));
        misEventos.setTypeface(CustomFontsLoader.getTypeface(this,CustomFontsLoader.Light));

        String[] nombres = new String[] { "Amaya y Juan", "Amaya y Juan", "Amaya y Juan"
                 };

        String[] lugares = new String[] { "Nombre del Lugar", "Nombre del Lugar", "Nombre del Lugar"
                 };

        String[] descripciones = new String[] { "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam"
                 };

        String[] fechas = new String[] { "12/Marzo/2017", "13/Marzo/2017", "12/Abril/2017"
                 };

        AdapterEventos adapter = new AdapterEventos(this, nombres,lugares,descripciones,fechas);
        eventContainer.setAdapter(adapter);

        eventContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent intent = new Intent(Evento.this, Login.class);
                startActivity(intent);
            }

        });

        agregarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Evento.this, Camera.class);
                startActivity(intent);
            }

        });
    }
    }

