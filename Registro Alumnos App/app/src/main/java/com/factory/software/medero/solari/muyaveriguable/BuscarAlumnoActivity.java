package com.factory.software.medero.solari.muyaveriguable;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public class BuscarAlumnoActivity extends AppCompatActivity {
    Spinner spnAño, spnDivision;
    String curso, apellido, nombre;
    EditText txtApellido,txtNombre;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_alumno);

        spnAño = (Spinner) findViewById(R.id.spnAño);
        String[] año = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 "};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, año);
        spnAño.setAdapter(adapter);

        spnDivision = (Spinner) findViewById(R.id.spnDiv);
        String[] division = {"A", "B", "C", "D"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, division);
        spnDivision.setAdapter(adapter2);

        txtApellido = (EditText) findViewById(R.id.txtApellidoBuscAlum);
        txtNombre = findViewById(R.id.txtNombreBuscAlum);

        btnBuscar = (Button) findViewById(R.id.BucarAlumnox);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificacion();
            }
        });
    }

    private void click1() {
        Intent i = new Intent(this, LstAutorizadosActivity.class);

        nombre = txtNombre.getText().toString();
        i.putExtra("Nombre", nombre);

        apellido = txtApellido.getText().toString();
        i.putExtra("Apellido", apellido);

        curso = spnAño.getSelectedItem().toString() + spnDivision.getSelectedItem().toString();
        i.putExtra("Curso", curso);

        startActivity(i);
        finish();
    }

    public void verificacion() {
        if (TextUtils.isEmpty(txtApellido.getText().toString().trim())) {
            txtApellido.setError("El campo no puede estar vacío");
            return;
        }
        if (TextUtils.isEmpty(txtNombre.getText().toString().trim())) {
            txtNombre.setError("El campo no puede estar vacío");
            return;
        }
        final String Nombre= txtNombre.getText().toString();
        final String Apellido = txtApellido.getText().toString();
        final String Curso = spnAño.getSelectedItem().toString() + spnDivision.getSelectedItem().toString();
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        click1();
                    } else {
                        Toast.makeText(BuscarAlumnoActivity.this, "Alumno no registrado", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(BuscarAlumnoActivity.this);
                        builder.setMessage("Por favor, registre al alumno primero")
                                .setNegativeButton("Salir", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Verificacion2 verificacion2 = new Verificacion2(Nombre, Apellido, Curso, respoListener);
        RequestQueue queue = Volley.newRequestQueue(BuscarAlumnoActivity.this);
        queue.add(verificacion2);
    }

}