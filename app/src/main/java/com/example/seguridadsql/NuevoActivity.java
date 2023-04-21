package com.example.seguridadsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.seguridadsql.db.DbContactos;
import com.example.seguridadsql.db.DbVehiculos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class NuevoActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 1001;
    private Bitmap mSelectedImageBitmap;
    private ImageView mPreviewImageView;
    private Button mGalleryButton;
    private Button mUploadButton;

    EditText txtCedula, txtNombres, txtApellidos, txtDireccion, txtTelefono, txtEmail;
    Button btnGuarda;
    DbContactos dbContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        // ...
        txtCedula = findViewById(R.id.txtCedula);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);

        mPreviewImageView = findViewById(R.id.img_preview);
        mGalleryButton = findViewById(R.id.btn_gallery);
        mUploadButton = findViewById(R.id.btn_upload);

        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });

        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSelectedImageBitmap != null) {
                    byte[] imageBytes = getByteArrayFromBitmap(mSelectedImageBitmap);
                    if (imageBytes != null) {
                        if (!txtCedula.getText().toString().equals("") && !txtNombres.getText().toString().equals("")) {
                            DbContactos dbContactos = new DbContactos(NuevoActivity.this);

                            long id = dbContactos.insertarContacto(txtCedula.getText().toString(), txtNombres.getText().toString(), txtApellidos.getText().toString(), txtDireccion.getText().toString(), txtTelefono.getText().toString(), txtEmail.getText().toString(), BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));

                            if (id > 0) {
                                Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                                limpiar();
                            } else {
                                Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "SELECCIONE UNA IMAGEN", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    private void limpiar() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                mPreviewImageView.setImageBitmap(bitmap);
                mSelectedImageBitmap = bitmap; // asignamos la imagen seleccionada a la variable mSelectedImageBitmap
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}