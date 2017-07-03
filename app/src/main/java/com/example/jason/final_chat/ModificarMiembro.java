package com.example.jason.final_chat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModificarMiembro extends Activity implements OnClickListener {
    TextView txid, txap, txem;
    EditText etmid, etap, etem;
    Button Actualizar, Eliminar;
    long member_id;
    int dato = 0;
    SQLControlador dbcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_miembro);

        dbcon = new SQLControlador(this);
        dbcon.abrirBasedeDatos();
        txid = (TextView) findViewById(R.id.tvmiembro_nombre);
        etmid = (EditText) findViewById(R.id.et_miembro_nombre);
        txap = (TextView) findViewById(R.id.tvApellido);
        etap = (EditText) findViewById(R.id.et_apellido);
        txem = (TextView) findViewById(R.id.tvemail);
        etem = (EditText) findViewById(R.id.et_email);
        Actualizar = (Button) findViewById(R.id.btnActualizar);
        Eliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
        String memberID = i.getStringExtra("miembroId");
        String memberName = i.getStringExtra("miembroNombre");
        String memberApellido = i.getStringExtra("miembroApellido");
        String memberEmail = i.getStringExtra("miembroEmail");

        member_id = Long.parseLong(memberID);
        etmid.setText(memberName);
        etap.setText(memberApellido);
        etem.setText(memberEmail);

        Actualizar.setOnClickListener(this);
        Eliminar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnActualizar :
                String memName_upd = etmid.getText().toString();
                String memAp_upd = etap.getText().toString();
                String memEm_upd = etem.getText().toString();
                dbcon.actualizarDatos(member_id,memName_upd,memAp_upd,memEm_upd);
                this.returnHome();
                break;
            case R.id.btnEliminar :
                dbcon.deleteData(member_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
