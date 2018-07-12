package com.factory.software.medero.solari.muyaveriguable;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuscarAutorizadoActivity extends AppCompatActivity {
    EditText txtDNI, txtApellido;
    Button btnBuscar;
    String apellido, dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_autorizado);

        txtApellido = findViewById(R.id.txtApellidoBuscAu1);
        txtDNI = findViewById(R.id.txtDNIBuscAu1);

        btnBuscar = findViewById(R.id.BucarAutorizadox);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificacion();
            }
        });
    }

    public void click1() {

        Intent i = new Intent(this, LstAlumnosActivity.class);

        apellido = txtApellido.getText().toString();
        i.putExtra("Apellido", apellido);

        dni = txtDNI.getText().toString();
        i.putExtra("DNI", dni);

        startActivity(i);
        finish();
    }

    public void verificacion() {
        if (TextUtils.isEmpty(txtApellido.getText().toString().trim())) {
            txtApellido.setError("El campo no puede estar vacío");
            return;
        }
        if (TextUtils.isEmpty(txtDNI.getText().toString().trim())) {
            txtDNI.setError("El campo no puede estar vacío");
            return;
        }
        final int DNI = Integer.parseInt(txtDNI.getText().toString());
        final String Apellido = txtApellido.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        click1();
                    } else {
                        Toast.makeText(BuscarAutorizadoActivity.this, "Autorizado no registrado", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(BuscarAutorizadoActivity.this);
                        builder.setMessage("Por favor, registre al autorizado primero")
                                .setNegativeButton("Salir", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Verificacion3 verificacion3 = new Verificacion3(DNI, Apellido, respoListener);
        RequestQueue queue = Volley.newRequestQueue(BuscarAutorizadoActivity.this);
        queue.add(verificacion3);
    }
}
