package com.example.seguridadsql.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class DbVehiculos extends DbHelper {

    Context context;
    public DbVehiculos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public long insertarContacto(String placa, String apellidos, String cedula, String clase_vehiculo, String serial, String marca, String color, Bitmap imagen) {
        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("placa", placa);
            values.put("apellidos", apellidos);
            values.put("cedula", cedula);
            values.put("clase_vehiculo", clase_vehiculo);
            values.put("serial", serial);
            values.put("marca", marca);
            values.put("color", color);
            values.put("imagen", getByteArrayFromBitmap(imagen)); // agregar imagen a los valores

            id = db.insert(TABLE_VEHICULOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}

