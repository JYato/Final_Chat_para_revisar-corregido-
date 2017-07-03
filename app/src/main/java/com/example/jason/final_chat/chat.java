package com.example.jason.final_chat;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class chat extends AppCompatActivity{
    TextView titulo;
    ListView cajax;
    Button boton;
    EditText agregar;
    SQLControladordemensajes dbconeccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        titulo = (TextView) findViewById(R.id.titulo);
        boton = (Button) findViewById(R.id.enviar);
        cajax = (ListView) findViewById(R.id.caja);
        agregar = (EditText) findViewById(R.id.entrada);
        dbconeccion = new SQLControladordemensajes(this);
        dbconeccion.abrirBasedeDatos();
        Intent intent = getIntent();
        Bundle helps = intent.getExtras();
        int idcontactor = 0;
        if(helps != null){
            String dato = helps.getString("miembroNombre");
            titulo.setText(dato);
            idcontactor = Integer.parseInt(helps.getString("miembroId"));
        }
        Cursor cursor = dbconeccion.leerDatos();
        String[] from = new String[]{MensajesBD.DatosTablams.COLUMNA_ID, MensajesBD.DatosTablams.COLUMNA_TEXTO, MensajesBD.DatosTablams.COLUMNA_FECHA_HORA, MensajesBD.DatosTablams.COLUMNA_CONTACTID};
        int[] to = new int[]{R.id.miembro_idm, R.id.miembro_texto, R.id.miembro_fecha_hora, R.id.miembro_contactid};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(chat.this, R.layout.formato_mensajes,cursor, from, to, 1);
        cajax.setAdapter(adapter);
        cajax.setSelection(cajax.getAdapter().getCount()-1);
        final int finalIdcontactor = idcontactor;
        boton.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String nueva_entrada = agregar.getText().toString();
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
                dbconeccion.insertarDatos(nueva_entrada, time, finalIdcontactor);
                adapter.notifyDataSetChanged();
                agregar.setText("");
                Intent refresh = new Intent(getApplicationContext(), chat.class);
                startActivity(refresh);
                finish();
            }
        });


    }

    /**@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enviar :
                String mas = agregar.getText().toString();
                String fin = cajax + "" + mas;
                cajax.setText(fin);
        }
    }*/
}