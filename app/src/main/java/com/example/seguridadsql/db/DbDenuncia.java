package com.example.seguridadsql.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbDenuncia extends DbHelper {

    Context context;
    public DbDenuncia (@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String placa, String denuncia, String spoa, String fiscalia, String juzgado, String fecha, String observacion ){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("placa", placa);
            values.put("denuncia", denuncia);
            values.put("spoa", spoa);
            values.put("fiscalia", fiscalia);
            values.put("juzgado", juzgado);
            values.put("fecha", fecha);
            values.put("observacion", observacion);

            id = db.insert(TABLE_DENUNCIAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
