package com.factory.software.medero.solari.muyaveriguable;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class RegistarAlumnoActivity extends AppCompatActivity {
    EditText txtDNI, txtNombre, txtApellido, txtFechaNac, txtDireccion, txtTelefono;
    Button btnRegistrar;
    Spinner spnAño, spnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_alumno);

        txtDNI = (EditText) findViewById(R.id.txtDNIAlumno);
        txtNombre = (EditText) findViewById(R.id.txtNombreAlumno);
        txtApellido = (EditText) findViewById(R.id.txtApellidoAlumno);
        txtFechaNac = (EditText) findViewById(R.id.txtFechaNac);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) findViewById(R.id.txtTelefonoAlumno);

        spnAño = (Spinner) findViewById(R.id.spnAñoR);
        String[] año = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 "};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, año);
        spnAño.setAdapter(adapter);

        spnDiv = (Spinner) findViewById(R.id.spnDivR);
        String[] division = {"A", "B", "C", "D"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, division);
        spnDiv.setAdapter(adapter2);

        btnRegistrar = (Button) findViewById(R.id.btnRegAl);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click1();
            }
        });
    }

    private void Click1() {
        btnRegistrar.setEnabled(false);
        final int DNI = Integer.parseInt(txtDNI.getText().toString());
        final String Nombre = txtNombre.getText().toString();
        final String Apellido = txtApellido.getText().toString();
        final String FechaNacimiento = txtFechaNac.getText().toString();
        final String Direccion = txtDireccion.getText().toString();
        final String Curso = spnAño.getSelectedItem().toString() + spnDiv.getSelectedItem().toString();
        final int TelefonoContacto = Integer.parseInt(txtTelefono.getText().toString());

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Toast.makeText(RegistarAlumnoActivity.this, "Datos registrados", Toast.LENGTH_SHORT).show();
                        txtDNI.setText("");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDireccion.setText("");
                        txtFechaNac.setText("");
                        txtTelefono.setText("");

                        btnRegistrar.setEnabled(true);
                                    /*Intent i = new Intent(RegistrarVisitaActivity.this, RegistrarVisitaActivity.class);
                                    startActivity(i);*/
                    } else {
                        Toast.makeText(RegistarAlumnoActivity.this, "Registro incorrecto", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistarAlumnoActivity.this);
                        builder.setMessage("Error de registro")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                        btnRegistrar.setEnabled(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RegistrarAlumno registrarVisita = new RegistrarAlumno(DNI, Nombre, Apellido, FechaNacimiento, Direccion, Curso, TelefonoContacto, respoListener);
        RequestQueue queue = Volley.newRequestQueue(RegistarAlumnoActivity.this);
        queue.add(registrarVisita);

    }
}

                /*
                String registro = "http://192.168.43.228/registrarAlumno.php?dni="+Integer.parseInt(txtDNI.getText().toString())+"&nom="+txtNombre.getText()+"&ape="+txtApellido.getText()+"&fec="+txtFechaNac.getText()+"&dir="+txtDireccion.getText()+"&cur="+spnAño.getSelectedItem().toString()+"&tel="+Integer.parseInt(txtTelefono.getText().toString());
                EnviarRecibirDatos(registro);
                */
/*
    public void EnviarRecibirDatos(String URL){

        Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        //CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
*/
