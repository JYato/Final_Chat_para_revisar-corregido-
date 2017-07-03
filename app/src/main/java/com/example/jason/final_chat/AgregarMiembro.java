package com.example.jason.final_chat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarMiembro extends Activity implements OnClickListener {
    TextView txid, txap, txem;
    EditText et, etap, etem;
    Button Agregar, read_bt;
    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_miembro);
        txid = (TextView) findViewById(R.id.tvmiembro_id);
        et = (EditText) findViewById(R.id.et_miembro_id);
        txap = (TextView) findViewById(R.id.tvApellido);
        etap = (EditText) findViewById(R.id.et_apellido);
        txem = (TextView) findViewById(R.id.tvemail);
        etem = (EditText) findViewById(R.id.et_email);
        Agregar = (Button) findViewById(R.id.btnAgregarId);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBasedeDatos();
        Agregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAgregarId :
                String name = et.getText().toString();
                String apellido = etap.getText().toString();
                String email = etem.getText().toString();
                dbconeccion.insertarDatos(name,apellido,email);
                Intent main = new Intent(AgregarMiembro.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
            default :
                break;
        }
    }
}
