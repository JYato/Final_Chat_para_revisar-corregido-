package com.example.jason.final_chat;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.IOException;

/**
 * Created by Jason on 29/06/2017.
 */

public class ContactosBD extends SQLiteOpenHelper {
    public static class DatosTabla implements BaseColumns {
        public static final String NOMBRE_TABLA = "contactos";
        public static final String COLUMNA_ID = "_id";
        public static final String COLUMNA_NOMBRES = "nombre";
        public static final String COLUMNA_APELLIDOS = "apellido";
        public static final String COLUMNA_EMAIL = "email";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String CLEAR_TABLA =
                "CREATE TABLE " + DatosTabla.NOMBRE_TABLA + "(" +
                        DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatosTabla.COLUMNA_NOMBRES + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.COLUMNA_APELLIDOS + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.COLUMNA_EMAIL + TEXT_TYPE+
                        " )";
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTabla.NOMBRE_TABLA;
    }

    public static final int DATABASE_VERSION = 1;
    public  static final String DATABASE_NAME = "ContactosBasedeDatos.db";

    public ContactosBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTabla.CLEAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}