package com.example.seguridadsql;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguridadsql.db.DbDenuncia;

public class Denuncia extends AppCompatActivity {

    EditText txtPlaca, txtDenuncia, txtSpoa, txtFiscalia, txtJuzgado, txtFecha, txtObservacion;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denuncia);

        txtPlaca = findViewById(R.id.txtPlaca);
        txtDenuncia = findViewById(R.id.txtDenuncia);
        txtSpoa = findViewById(R.id.txtSpoa);
        txtFiscalia = findViewById(R.id.txtFiscalia);
        txtJuzgado = findViewById(R.id.txtJuzgado);
        txtFecha = findViewById(R.id.txtFecha);
        txtObservacion = findViewById(R.id.txtObservacion);


        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtPlaca.getText().toString().equals("") && !txtDenuncia.getText().toString().equals("")) {

                    DbDenuncia dbDenuncia = new DbDenuncia(Denuncia.this);
                    long id = dbDenuncia.insertarContacto(txtPlaca.getText().toString(), txtDenuncia.getText().toString(), txtSpoa.getText().toString(),txtFiscalia.getText().toString(),txtJuzgado.getText().toString(),txtFecha.getText().toString(),txtObservacion.getText().toString());

                    if (id > 0) {
                        Toast.makeText(Denuncia.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(Denuncia.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Denuncia.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtPlaca.setText("");
        txtDenuncia.setText("");
        txtSpoa.setText("");
        txtFiscalia.setText("");
        txtJuzgado.setText("");
        txtFecha.setText("");
        txtObservacion.setText("");
    }
}