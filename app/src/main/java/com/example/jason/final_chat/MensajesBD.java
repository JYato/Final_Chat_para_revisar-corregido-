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

public class MensajesBD extends SQLiteOpenHelper {
    public static class DatosTablams implements BaseColumns {
        public static final String NOMBRE_TABLA = "mensajes";
        public static final String COLUMNA_ID = "_id";
        public static final String COLUMNA_TEXTO = "texto";
        public static final String COLUMNA_FECHA_HORA = "fechahora";
        public static final String COLUMNA_CONTACTID = "contactoid";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String CLEAR_TABLA =
                "CREATE TABLE " + DatosTablams.NOMBRE_TABLA + "(" +
                        DatosTablams.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatosTablams.COLUMNA_TEXTO + TEXT_TYPE + COMMA_SEP +
                        DatosTablams.COLUMNA_FECHA_HORA + TEXT_TYPE + COMMA_SEP +
                        DatosTablams.COLUMNA_CONTACTID + "INTEGER "+
                        " )";
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTablams.NOMBRE_TABLA;
    }

    public static final int DATABASE_VERSION = 1;
    public  static final String DATABASE_NAME = "MensajesBasedeDatos.db";

    public MensajesBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTablams.CLEAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatosTablams.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}