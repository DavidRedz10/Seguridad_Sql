package com.example.seguridadsql.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DbContactos extends DbHelper {

    Context context;
    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public long insertarContacto(String cedula, String nombres, String apellidos, String direccion, String telefono, String email, Bitmap imagen) {
        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("cedula", cedula);
            values.put("nombres", nombres);
            values.put("apellidos", apellidos);
            values.put("direccion", direccion);
            values.put("telefono", telefono);
            values.put("email", email);
            values.put("imagen", getByteArrayFromBitmap(imagen)); // agregar imagen a los valores

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
