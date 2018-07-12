package com.factory.software.medero.solari.muyaveriguable;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LstAlumnosActivity extends AppCompatActivity {

    ListView listaResultado;
    String dni, apellido;
    TextView txtApellido, txtDni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_alumnos);
        listaResultado = findViewById(R.id.lstResultado);

        txtApellido = findViewById(R.id.textView13);
        apellido = getIntent().getExtras().getString("Apellido");
        txtApellido.setText(apellido);

        txtDni = findViewById(R.id.textView14);
        dni = getIntent().getExtras().getString("DNI");
        txtDni.setText(dni);

        click1();

    }

    private void click1() {
        final String Apellido = txtApellido.getText().toString();
        final int DNI = Integer.parseInt(txtDni.getText().toString());
        Response.Listener<String> respoListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                try {
                    JSONObject jsonResponse = new JSONObject(response2);
                    boolean success = jsonResponse.getBoolean("success");
                    JSONArray jArray = jsonResponse.getJSONArray("datos");

                    if (success) {
                        Toast.makeText(LstAlumnosActivity.this, "Datos consultados", Toast.LENGTH_SHORT).show();
                        txtApellido.setText("");
                        txtDni.setText("");

                        ArrayList<String> items = new ArrayList<String>();
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            int dni = json_data.getInt("DNI");
                            String nombre = json_data.getString("Nombre");
                            String apellido = json_data.getString("Apellido");
                            String curso = json_data.getString("Curso");

                            items.add("DNI: " + Integer.toString(dni) + "\nNombre: " + nombre + " - Apellido: " + apellido + "\nCurso: " +curso);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LstAlumnosActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, items);

                        listaResultado.setAdapter(adapter);

                    } else {
                        Toast.makeText(LstAlumnosActivity.this, "Consulta incorrecto", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(LstAlumnosActivity.this);
                        builder.setMessage("Error de consulta")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        BuscarAlumno buscarAlumno = new BuscarAlumno(Apellido, DNI, respoListener2);
        RequestQueue queue = Volley.newRequestQueue(LstAlumnosActivity.this);
        queue.add(buscarAlumno);
    }

}
