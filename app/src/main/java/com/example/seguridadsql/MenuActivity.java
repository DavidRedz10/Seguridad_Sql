package com.example.seguridadsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.content.*;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.*;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import android.util.*;


import com.example.seguridadsql.db.DbHelper;

import java.io.File;
import java.nio.file.Path;

public class MenuActivity extends AppCompatActivity {

    Button btnCrear;
    Button btnEliminar;
    Button btnNext;
    Button btnBici;
    Button btnView;

    Button btnDenuncia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnCrear = findViewById(R.id.btnCrear);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnNext = findViewById(R.id.añadir);
        btnBici = findViewById(R.id.button3);
        btnView = findViewById(R.id.button6);
        btnDenuncia = findViewById(R.id.button7);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MenuActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if( db!= null){
                    Toast.makeText(MenuActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MenuActivity.this, "BASE NO CREADA", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DbHelper dbHelper = new DbHelper(MenuActivity.this);
                //SQLiteDatabase db = dbHelper.getWritableDatabase();
                //db.execSQL("DROP TABLE IF EXISTS " + "usuarios");
                File file = new File("/data/data/com.example.seguridadsql/databases/personas.db");
                if (file.exists()) {
                    file.delete();
                    Toast.makeText(getApplicationContext(), "Archivo eliminado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "El archivo no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, NuevoActivity.class);
                startActivity(intent);
            }
        });

        btnBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, NuevoVehiculo.class);
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Viewuser.class);
                startActivity(intent);
            }
        });

        btnDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Denuncia.class);
                startActivity(intent);
            }
        });


        }

}

