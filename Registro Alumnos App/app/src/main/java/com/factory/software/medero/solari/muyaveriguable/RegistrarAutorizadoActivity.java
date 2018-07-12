package com.factory.software.medero.solari.muyaveriguable;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarAutorizadoActivity extends AppCompatActivity {
    EditText txtDNI, txtNombre, txtApellido, txtTelefono, txtDNIAlumno;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_autorizado);
        txtDNI = (EditText) findViewById(R.id.txtDNIAutorizado);
        txtNombre = (EditText) findViewById(R.id.txtNombreAutorizado);
        txtApellido = (EditText) findViewById(R.id.txtApellidoAutorizado);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDNIAlumno = (EditText) findViewById(R.id.txtAlumnoDNI);

        btnRegistrar = (Button) findViewById(R.id.btnRegAu);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click1();
            }
        });
    }

    private void Click1() {
buttonCheck();
    }

    public void buttonCheck() {
        if (TextUtils.isEmpty(txtDNI.getText().toString().trim())) {
            txtDNI.setError("El campo no puede estar vacío");
            return;
        }
        if (TextUtils.isEmpty(txtNombre.getText().toString().trim())) {
            txtNombre.setError("El campo no puede estar vacío");
            return;
        }
        if (TextUtils.isEmpty(txtApellido.getText().toString().trim())) {
            txtApellido.setError("El campo no puede estar vacío");
            return;
        }
        if (TextUtils.isEmpty(txtDNIAlumno.getText().toString().trim())) {
            txtDNIAlumno.setError("El campo no puede estar vacío");
            return;
        }
        else {
            verificacion();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public void verificacion() {

        final int DNI = Integer.parseInt(txtDNIAlumno.getText().toString());
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        registrarAutorizado();
                    } else {
                        Toast.makeText(RegistrarAutorizadoActivity.this, "Alumno no registrado", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarAutorizadoActivity.this);
                        builder.setMessage("Por favor, registre al alumno primero")
                                .setNegativeButton("Salir", null)
                                .create().show();
                        btnRegistrar.setEnabled(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Verificacion verificacion= new Verificacion(DNI, respoListener);
        RequestQueue queue = Volley.newRequestQueue(RegistrarAutorizadoActivity.this);
        queue.add(verificacion);
        /*
        Verficacion verficacion= new Verficacion(DNI, respoListener);
        RequestQueue queue = Volley.newRequestQueue(RegistrarAutorizadoActivity.this);
        queue.add(verficacion);
        */
    }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void registrarAutorizado() {
        final int DNI = Integer.parseInt(txtDNI.getText().toString());
        final String Nombre = txtNombre.getText().toString();
        final String Apellido = txtApellido.getText().toString();
        final String Telefono = txtTelefono.getText().toString();
        //final int Telefono = Integer.parseInt(txtTelefono.getText().toString());

        Response.Listener<String> respoListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                try {
                    JSONObject jsonResponse = new JSONObject(response2);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        registrarAutorizadoXalumno();
                    } else {
                        Toast.makeText(RegistrarAutorizadoActivity.this, "Registro incorrecto", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarAutorizadoActivity.this);
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
        RegistrarAutorizado registrarAutorizado = new RegistrarAutorizado(DNI, Nombre, Apellido, Telefono, respoListener2);
        RequestQueue queue = Volley.newRequestQueue(RegistrarAutorizadoActivity.this);
        queue.add(registrarAutorizado);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void registrarAutorizadoXalumno() {

        final int AlumnoDNI = Integer.parseInt(txtDNIAlumno.getText().toString());
        final int AutorizadoDNI = Integer.parseInt(txtDNI.getText().toString());

        Response.Listener<String> respoListener3 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response3) {
                try {
                    JSONObject jsonResponse = new JSONObject(response3);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Toast.makeText(RegistrarAutorizadoActivity.this, "Datos registrados", Toast.LENGTH_SHORT).show();
                        txtDNIAlumno.setText("");

                        btnRegistrar.setEnabled(true);
                    } else {
                        Toast.makeText(RegistrarAutorizadoActivity.this, "Registro incorrecto", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarAutorizadoActivity.this);
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
        RegistrarAutorizadosXalumnos registrarAutorizadosXalumnos = new RegistrarAutorizadosXalumnos(AlumnoDNI, AutorizadoDNI, respoListener3);
        RequestQueue queue = Volley.newRequestQueue(RegistrarAutorizadoActivity.this);
        queue.add(registrarAutorizadosXalumnos);

    }
}
