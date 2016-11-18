package com.example.gatsu.theevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Evento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        final RelativeLayout eventContainer = (RelativeLayout) findViewById(R.id.eventContainer);
        TextView selectEvento = (TextView) this.findViewById(R.id.seleccionaEvento);
        TextView misEventos = (TextView) this.findViewById(R.id.misEventos);
        selectEvento.setTypeface(CustomFontsLoader.getTypeface(this,CustomFontsLoader.Bold));
        misEventos.setTypeface(CustomFontsLoader.getTypeface(this,CustomFontsLoader.Light));

        int numEventos = 0;

        while(numEventos < 3) {
            ImageView imgEvento = new ImageView(this);
            imgEvento.setId(numEventos+1);
            imgEvento.setImageResource(R.mipmap.eventos323);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(dpiTopx(15), dpiTopx(10), dpiTopx(15), 0);
            params.addRule(RelativeLayout.BELOW, numEventos);
            eventContainer.addView(imgEvento,params);
            numEventos++;
        }

        ImageView agregar = new ImageView(this);
        agregar.setId(numEventos+1);
        agregar.setImageResource(R.mipmap.btnagregarevento);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpiTopx(25), 0, 0);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, numEventos);
        eventContainer.addView(agregar,params);

        TextView agregarTxt = new TextView(this);
        RelativeLayout.LayoutParams paramsTxt = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsTxt.setMargins(0, dpiTopx(15), 0, dpiTopx(15));
        paramsTxt.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsTxt.addRule(RelativeLayout.BELOW, agregar.getId());
        System.out.println("AGREGAR ID: "+agregar.getId());
        agregarTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        agregarTxt.setText(R.string.agregarEvento);
        eventContainer.addView(agregarTxt,paramsTxt);
    }

    private int dpiTopx(float dips)
    {
        float DP = getResources().getDisplayMetrics().density;
        return Math.round(dips * DP);
    }
}
