package com.example.seguridadsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.seguridadsql.db.DbHelper;

public class Viewuser extends AppCompatActivity {

    private ListView listView;
    private MyCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = findViewById(R.id.list_view);
        adapter = new MyCursorAdapter(this, getCursor());
        listView.setAdapter(adapter);
    }

    private Cursor getCursor() {
        SQLiteDatabase db = new DbHelper(this).getReadableDatabase();
        return db.rawQuery("SELECT * FROM usuarios", null);
    }

    private class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            return inflater.inflate(R.layout.listview, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView cedulaTextView = view.findViewById(R.id.cedula);
            TextView nombresTextView = view.findViewById(R.id.nombres);
            TextView apellidosTextView = view.findViewById(R.id.apellidos);
            TextView direccionTextView = view.findViewById(R.id.direccion);
            TextView telefonoTextView = view.findViewById(R.id.telefono);
            TextView emailTextView = view.findViewById(R.id.email);
            ImageView imageView = view.findViewById(R.id.imagen);

            String cedula = cursor.getString(cursor.getColumnIndex("Cedula"));
            String nombres = cursor.getString(cursor.getColumnIndex("Nombres"));
            String apellidos = cursor.getString(cursor.getColumnIndex("Apellidos"));
            String direccion = cursor.getString(cursor.getColumnIndex("Direccion"));
            String telefono = cursor.getString(cursor.getColumnIndex("Telefono"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            byte[] imagenBytes = cursor.getBlob(cursor.getColumnIndex("Imagen"));

            Bitmap imagen = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

            cedulaTextView.setText(cedula);
            nombresTextView.setText(nombres);
            apellidosTextView.setText(apellidos);
            direccionTextView.setText(direccion);
            telefonoTextView.setText(telefono);
            emailTextView.setText(email);
            imageView.setImageBitmap(imagen);
        }
    }
}