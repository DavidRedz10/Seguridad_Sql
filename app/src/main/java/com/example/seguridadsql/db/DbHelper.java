package com.example.seguridadsql.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "personas.db";
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_VEHICULOS = "vehiculos";

    public static final String TABLE_DENUNCIAS = "denuncias";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + "usuarios" + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cedula TEXT NOT NULL," +
                "nombres TEXT NOT NULL," +
                "apellidos TEXT," +
                "direccion TEXT," +
                "telefono TEXT," +
                "email TEXT," +
                "imagen BLOB)" );
        sqLiteDatabase.execSQL("CREATE TABLE " + "vehiculos" + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "placa TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "cedula TEXT," +
                "clase_vehiculo TEXT," +
                "serial TEXT," +
                "marca TEXT," +
                "color TEXT," +
                "imagen BLOB)" );
        sqLiteDatabase.execSQL("CREATE TABLE " + "denuncias" + "(" +
                "cedula INTEGER PRIMARY KEY AUTOINCREMENT," +
                "placa TEXT NOT NULL," +
                "denuncia TEXT," +
                "spoa TEXT," +
                "fiscalia TEXT," +
                "juzgado TEXT," +
                "fecha TEXT," +
                "observacion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + "t_usuarios");
        onCreate(sqLiteDatabase);

    }

    public void onDelete(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + "t_usuarios");
    }
}
