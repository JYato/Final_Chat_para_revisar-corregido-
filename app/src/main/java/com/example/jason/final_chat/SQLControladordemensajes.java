package com.example.jason.final_chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;

import com.example.jason.final_chat.MensajesBD;


public class SQLControladordemensajes {
    private MensajesBD chelperms;
    private Context ourrcontext;
    private SQLiteDatabase database;

    public SQLControladordemensajes(Context c){
        ourrcontext = c;
    }

    public SQLControladordemensajes abrirBasedeDatos() throws SQLException{
        chelperms = new MensajesBD(ourrcontext);
        database = chelperms.getWritableDatabase();
        return this;
    }

    public void cerrar(){
        chelperms.close();
    }

    public void insertarDatos(String texto, String fecha_hora, int contactid){
        ContentValues cvd = new ContentValues();
        cvd.put(MensajesBD.DatosTablams.COLUMNA_TEXTO,texto);
        cvd.put(MensajesBD.DatosTablams.COLUMNA_FECHA_HORA,fecha_hora);
        cvd.put(MensajesBD.DatosTablams.COLUMNA_CONTACTID,contactid);
        /*cv.put(ContactosBD.DatosTabla.COLUMNA_APELLIDOS,apellido);
        cv.put(ContactosBD.DatosTabla.COLUMNA_EMAIL,email);*/
        database.insert(MensajesBD.DatosTablams.NOMBRE_TABLA,null,cvd);
    }

    public Cursor leerDatos(){
        String[] todaslascolumnasms = new String[]{
                MensajesBD.DatosTablams.COLUMNA_ID,MensajesBD.DatosTablams.COLUMNA_TEXTO,MensajesBD.DatosTablams.COLUMNA_FECHA_HORA,MensajesBD.DatosTablams.COLUMNA_CONTACTID};
        Cursor c = database.query(MensajesBD.DatosTablams.NOMBRE_TABLA, todaslascolumnasms, null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    /**public int actualizarDatos(long memberID, String memberName, String memberApellido, String memberEmail){
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(ContactosBD.DatosTabla.NOMBRE_TABLA,memberName);
        cvActualizar.put(ContactosBD.DatosTabla.NOMBRE_TABLA,memberApellido);
        cvActualizar.put(ContactosBD.DatosTabla.NOMBRE_TABLA,memberEmail);
        int i = database.update(ContactosBD.DatosTabla.NOMBRE_TABLA, cvActualizar, ContactosBD.DatosTabla.COLUMNA_ID + "=" + memberID, null);
        return i;
    }*/
    public void deleteData(long memberID){
        database.delete(MensajesBD.DatosTablams.NOMBRE_TABLA, MensajesBD.DatosTablams.COLUMNA_ID + "=" + memberID, null);
    }
}
