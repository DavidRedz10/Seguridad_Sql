package com.example.seguridadsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class LoginView extends AppCompatActivity {

    Button loginbtn;
    EditText userText;
    EditText passText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);


        loginbtn = (Button)findViewById(R.id.button2);
        userText = (EditText)findViewById(R.id.editText);
        passText =  (EditText)findViewById(R.id.editText2);
        Typeface builder;
        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v ){
                final String user = userText.getText().toString();
                final String pass = passText.getText().toString();
                if(user.equals("user") && pass.equals("123")) {
                    Intent i = new Intent(LoginView.this, MenuActivity.class);
                    startActivity(i);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginView.this);
                    builder.setTitle("Usuario o Contraseña incorrectos!");
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }
            }

        });

    }
}