package com.example.gatsu.theevent;

/**
 * Created by Gatsu on 12/2/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterTimeline extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] nombres;
    private final String[] dias;
    private final String[] mensajes;
    private final String[] comentarios;
    private final String[] likes;
    ArrayList<Bitmap> imagenes;

    static class ViewHolder {
        public TextView nombreTxt;
        public TextView mensajeTxt;
        public TextView diaTxt;
        public TextView gustaTxt;
        public TextView comentariosTxt;
        public RelativeLayout image;
        public ImageView btnmegusta;
        public ImageView btncomentario;
        public ImageView btnruta;
        public TextView numlikes;
        public ImageView vertblack;
        public RelativeLayout timelinehead;
        public LinearLayout megustalayout;
        public LinearLayout comentariolayout;
        public LinearLayout rutalayout;
        public ImageView imgperfil;
        public ImageView foto1;
        public ImageView foto2;

    }

    public AdapterTimeline(Activity context, String[] nombres, String[] dias, String[] mensajes, ArrayList<Bitmap> imagenes,String[] comentarios,String[] likes) {
        super(context, R.layout.activity_timeline, nombres);
        this.context = context;
        this.nombres = nombres;
        this.dias = dias;
        this.mensajes = mensajes;
        this.imagenes = imagenes;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.post_layout, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.timelinehead = (RelativeLayout) rowView.findViewById(R.id.timelineHead);
            viewHolder.nombreTxt = (TextView) rowView.findViewById(R.id.nombrePer);
            viewHolder.diaTxt = (TextView) rowView.findViewById(R.id.diatxt);
            viewHolder.mensajeTxt = (TextView) rowView.findViewById(R.id.mensajeTime);
            viewHolder.gustaTxt = (TextView) rowView.findViewById(R.id.txt);
            viewHolder.comentariosTxt = (TextView) rowView.findViewById(R.id.numComentarios);
            viewHolder.numlikes = (TextView) rowView.findViewById(R.id.burbuja);

            viewHolder.btnmegusta = (ImageView) rowView.findViewById(R.id.btnmegusta);
            viewHolder.btncomentario = (ImageView) rowView.findViewById(R.id.btncomentario);
            viewHolder.vertblack = (ImageView) rowView.findViewById(R.id.vertblack);


            viewHolder.imgperfil = (ImageView) rowView.findViewById(R.id.imgperfil);
            viewHolder.foto1 = (ImageView) rowView.findViewById(R.id.foto1);
            viewHolder.foto2 = (ImageView) rowView.findViewById(R.id.foto2);

            viewHolder.nombreTxt.setTypeface(CustomFontsLoader.getTypeface(context,CustomFontsLoader.Regular));
            viewHolder.diaTxt.setTypeface(CustomFontsLoader.getTypeface(context,CustomFontsLoader.Light));
            viewHolder.mensajeTxt.setTypeface(CustomFontsLoader.getTypeface(context,CustomFontsLoader.Light));
            viewHolder.gustaTxt.setTypeface(CustomFontsLoader.getTypeface(context,CustomFontsLoader.Light));
            viewHolder.comentariosTxt.setTypeface(CustomFontsLoader.getTypeface(context,CustomFontsLoader.Light));

            viewHolder.image = (RelativeLayout) rowView
                    .findViewById(R.id.timelineHead);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.timelinehead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Evento.class));
            }
        });

        String dia;
        String nombre = nombres[position];
        if (dias[position].equals("0")){
            dia = "Hoy";
        }
        else if (dias[position].equals("1")){
            dia = "Hace " + dias[position] + " dia";
        }
        else{
            dia = "Hace " + dias[position] + " dias";
        }
        String mensaje = mensajes[position];
        String numComentarios = comentarios[position]+ " comentarios";
        String numLikes = "   +" + likes[position]+ "    ";
        if (imagenes.size()>0) {
            Bitmap imagen = imagenes.get(position);
            Drawable dr = new BitmapDrawable(imagen);
            holder.image.setBackgroundDrawable(dr);
        }
        holder.nombreTxt.setText(nombre);
        holder.diaTxt.setText(dia);
        holder.mensajeTxt.setText(mensaje);
        holder.comentariosTxt.setText(numComentarios);
        holder.numlikes.setText(numLikes);
        if(position==getCount()- 1){
                holder.vertblack.setVisibility(View.VISIBLE);
        }
        return rowView;
    }

}
