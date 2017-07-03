package com.example.jason.final_chat;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static com.example.jason.final_chat.R.*;
import static com.example.jason.final_chat.R.id.parent;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Button Agregarm;
    ///String[] personas = new String[] {"Oscar Ramos","Andre Hurtado","Alejandro Flores","Favaro Shin","Brandon Mike","Jason Ain"};
    SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre, tv_miemAp, tv_miemEm, aviso;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBasedeDatos();
        Agregarm = (Button) findViewById(id.Agregar);
        lista = (ListView) findViewById(R.id.listaview);
        Agregarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iagregar = new Intent(MainActivity.this,AgregarMiembro.class);
                startActivity(iagregar);
            }
        });
        aviso = (TextView) findViewById(id.aviso);
        Cursor cursor = dbconeccion.leerDatos();
        String[] from = new String[]{ContactosBD.DatosTabla.COLUMNA_ID, ContactosBD.DatosTabla.COLUMNA_NOMBRES, ContactosBD.DatosTabla.COLUMNA_APELLIDOS, ContactosBD.DatosTabla.COLUMNA_EMAIL};
        int[] to = new int[]{id.miembro_id, id.miembro_nombre, id.miembro_apellido, id.miembro_email};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, layout.formato_fila,cursor, from, to, 1);
        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, final View view, int position, long id) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Desea hacer algun cambio con este contacto");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogo1, int i) {
                        tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                        tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);
                        tv_miemAp = (TextView) view.findViewById(R.id.miembro_apellido);
                        tv_miemEm = (TextView) view.findViewById(R.id.miembro_email);
                        String aux_miembroId = tv_miemID.getText().toString();
                        String aux_miembroNombre = tv_miemNombre.getText().toString();
                        String aux_miembroApellido = tv_miemAp.getText().toString();
                        String aux_miembroEmail = tv_miemEm.getText().toString();

                        Intent modify_intent = new Intent(getApplicationContext(),ModificarMiembro.class);
                        modify_intent.putExtra("miembroId",aux_miembroId);
                        modify_intent.putExtra("miembroNombre",aux_miembroNombre);
                        modify_intent.putExtra("miembroApellido",aux_miembroApellido);
                        modify_intent.putExtra("miembroEmail",aux_miembroEmail);
                        startActivity(modify_intent);
                    }
                });
                dialogo1.show();
                return false;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mint = new Intent(getApplicationContext(),chat.class);
                tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);
                tv_miemAp = (TextView) view.findViewById(R.id.miembro_apellido);
                tv_miemEm = (TextView) view.findViewById(R.id.miembro_email);
                String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();
                String aux_miembroApellido = tv_miemAp.getText().toString();
                String aux_miembroEmail = tv_miemEm.getText().toString();
                mint.putExtra("miembroId",aux_miembroId);
                mint.putExtra("miembroNombre",aux_miembroNombre);
                mint.putExtra("miembroApellido",aux_miembroApellido);
                mint.putExtra("miembroEmail",aux_miembroEmail);
                startActivity(mint);
            }
        });
        /*ContactosBD contactsbd = new ContactosBD((getApplicationContext()));
        SQLiteDatabase db = contactsbd.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ContactosBD.DatosTabla.COLUMNA_ID);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,personas);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mint = new Intent(getApplicationContext(),chat.class);
                mint.putExtra("nombre",personas[position]);
                startActivity(mint);
            }

        });*/
    }

}