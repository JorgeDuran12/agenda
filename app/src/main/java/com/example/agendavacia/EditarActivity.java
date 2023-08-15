package com.example.agendavacia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendavacia.db.DbContactos;
import com.example.agendavacia.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {
    EditText txtNombre,txtTelefono,txtCorreo;
    Button btnGuardar;
    Contactos contacto;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.ver_nombre);
        txtTelefono = findViewById(R.id.ver_numero);
        txtCorreo = findViewById(R.id.ver_email);
        btnGuardar = findViewById(R.id.ver_btn_guardar);


        if(savedInstanceState == null ){
            Bundle extras = getIntent().getExtras();
            if(extras == null ){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") &&
                        !txtTelefono.getText().toString().equals("") &&
                        !txtCorreo.getText().toString().equals("")) {

                    boolean correcto = dbContactos.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();
                        VerRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL ACTUALIZAR", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(EditarActivity.this, "SE DEBEN LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void VerRegistro(){
        Intent i = new Intent(this,VerActivity.class);
        startActivity(i);
    }
}