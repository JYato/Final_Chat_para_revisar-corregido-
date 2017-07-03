package com.example.jason.final_chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;

import com.example.jason.final_chat.ContactosBD;


public class SQLControlador {
    private ContactosBD chelper;
    private Context ourrcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c){
        ourrcontext = c;
    }

    public SQLControlador abrirBasedeDatos() throws SQLException{
        chelper = new ContactosBD(ourrcontext);
        database = chelper.getWritableDatabase();
        return this;
    }

    public void cerrar(){
        chelper.close();
    }

    public void insertarDatos(String nombre, String apellido,String email){
        ContentValues cv = new ContentValues();
        cv.put(ContactosBD.DatosTabla.COLUMNA_NOMBRES,nombre);
        cv.put(ContactosBD.DatosTabla.COLUMNA_APELLIDOS,apellido);
        cv.put(ContactosBD.DatosTabla.COLUMNA_EMAIL,email);
        database.insert(ContactosBD.DatosTabla.NOMBRE_TABLA,null,cv);
    }

    public Cursor leerDatos(){
        String[] todaslascolumnas = new String[]{
                ContactosBD.DatosTabla.COLUMNA_ID,ContactosBD.DatosTabla.COLUMNA_NOMBRES,ContactosBD.DatosTabla.COLUMNA_APELLIDOS,ContactosBD.DatosTabla.COLUMNA_EMAIL};
        Cursor c = database.query(ContactosBD.DatosTabla.NOMBRE_TABLA, todaslascolumnas, null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long memberID, String memberName, String memberApellido, String memberEmail){
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(ContactosBD.DatosTabla.COLUMNA_NOMBRES,memberName);
        cvActualizar.put(ContactosBD.DatosTabla.COLUMNA_APELLIDOS,memberApellido);
        cvActualizar.put(ContactosBD.DatosTabla.COLUMNA_EMAIL,memberEmail);
        int i = database.update(ContactosBD.DatosTabla.NOMBRE_TABLA, cvActualizar, ContactosBD.DatosTabla.COLUMNA_ID + "=" + memberID, null);
        return i;
    }

    public void deleteData(long memberID){
        database.delete(ContactosBD.DatosTabla.NOMBRE_TABLA, ContactosBD.DatosTabla.COLUMNA_ID + "=" + memberID, null);
    }
}