package com.example.seguridadsql;

import static com.example.seguridadsql.db.DbHelper.TABLE_VEHICULOS;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.seguridadsql.db.DbHelper;
import com.example.seguridadsql.db.DbVehiculos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class NuevoVehiculo extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 1001;
    private Bitmap mSelectedImageBitmap;
    private ImageView mPreviewImageView;
    private Button mGalleryButton;
    private Button mUploadButton;

    EditText txtPlaca, txtApellidos, txtCedula, txtClase_vehiculo, txtSerial, txtMarca, txtColor;
    Button btnGuarda;
    DbVehiculos dbVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevovehiculo);

        // ...
        txtPlaca = findViewById(R.id.txtPlaca);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCedula = findViewById(R.id.txtCedula);
        txtClase_vehiculo = findViewById(R.id.txtClase_Vehiculo);
        txtSerial = findViewById(R.id.txtSerial);
        txtMarca = findViewById(R.id.txtMarca);
        txtColor = findViewById(R.id.txtColor);

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
                        if (!txtPlaca.getText().toString().equals("") && !txtApellidos.getText().toString().equals("")) {
                            DbVehiculos dbVehiculos = new DbVehiculos(NuevoVehiculo.this);
                            //String placa, String apellidos, String cedula, String clase_vehiculo, String serial, String marca, String color, Bitmap imagen
                            long id = dbVehiculos.insertarContacto(txtPlaca.getText().toString(), txtApellidos.getText().toString(), txtCedula.getText().toString(), txtClase_vehiculo.getText().toString(), txtSerial.getText().toString(), txtMarca.getText().toString(), txtColor.getText().toString(), BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));

                            if (id > 0) {
                                Toast.makeText(NuevoVehiculo.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                                limpiar();
                            } else {
                                Toast.makeText(NuevoVehiculo.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(NuevoVehiculo.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(NuevoVehiculo.this, "SELECCIONE UNA IMAGEN", Toast.LENGTH_LONG).show();
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
        txtPlaca.setText("");
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