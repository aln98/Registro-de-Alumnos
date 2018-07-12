package com.factory.software.medero.solari.muyaveriguable;

import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;

public class RegistarAlumnoActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtDNI, txtNombre, txtApellido, txtFechaNac, txtDireccion, txtTelefono;
    Button btnRegistrar, btnFecha;
    Spinner spnAño, spnDiv;
    private int año, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_alumno);

        txtDNI = (EditText) findViewById(R.id.txtDNIAlumno);
        txtNombre = (EditText) findViewById(R.id.txtNombreAlumno);
        txtApellido = (EditText) findViewById(R.id.txtApellidoAlumno);

        btnFecha = findViewById(R.id.btnFecha);
        btnFecha.setOnClickListener(this);

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
        if (TextUtils.isEmpty(txtDireccion.getText().toString().trim())) {
            txtDireccion.setError("El campo no puede estar vacío");
            return;
        }
        if (TextUtils.isEmpty(txtFechaNac.getText().toString().trim())) {
            txtFechaNac.setError("El campo no puede estar vacío");
            return;
        }
        else {
            registrar();
        }
    }

    public void registrar() {
        btnRegistrar.setEnabled(false);
        final int DNI = Integer.parseInt(txtDNI.getText().toString());
        final String Nombre = txtNombre.getText().toString();
        final String Apellido = txtApellido.getText().toString();
        final String FechaNacimiento = txtFechaNac.getText().toString();
        final String Direccion = txtDireccion.getText().toString();
        final String Curso = spnAño.getSelectedItem().toString() + spnDiv.getSelectedItem().toString();
        final String TelefonoContacto = txtTelefono.getText().toString();
        //final int TelefonoContacto = Integer.parseInt(txtTelefono.getText().toString());

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
        RegistrarAlumno registrarAlumno = new RegistrarAlumno(DNI, Nombre, Apellido, FechaNacimiento, Direccion, Curso, TelefonoContacto, respoListener);
        RequestQueue queue = Volley.newRequestQueue(RegistarAlumnoActivity.this);
        queue.add(registrarAlumno);

    }

    @Override
    public void onClick(View v) {
        if (v==btnFecha) {
            final Calendar calendar= Calendar.getInstance();
            año = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtFechaNac.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                }
            }
                    , año, mes, dia);
            datePickerDialog.show();
        }
    }
}