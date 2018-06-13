package com.factory.software.medero.solari.muyaveriguable;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
public class BuscarAlumnoActivity extends AppCompatActivity {
    Spinner spnAño, spnDivision;
    EditText txtApellido;
    Button btnBuscar;
    ListView listaResultado;

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

        listaResultado = (ListView) findViewById(R.id.lstResultado);

        btnBuscar = (Button) findViewById(R.id.BucarAlumnox);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1();
            }
        });
    }

    private void click1() {
        final String Apellido = txtApellido.getText().toString();
        final String Curso = spnAño.getSelectedItem().toString() + spnDivision.getSelectedItem().toString();
        btnBuscar.setEnabled(false);
        Response.Listener<String> respoListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                try {
                    JSONObject jsonResponse = new JSONObject(response2);
                    boolean success = jsonResponse.getBoolean("success");



                    if (success) {
                        Toast.makeText(BuscarAlumnoActivity.this, "Datos consultados", Toast.LENGTH_SHORT).show();
                        txtApellido.setText("");

                        btnBuscar.setEnabled(true);

                        JSONArray jarray =  jsonResponse.getJSONArray("results");
                        ArrayAdapter<String>  pp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jsonResponse.toJSONArray());
                        listaResultado.setAdapter(pp);

                    } else {
                        Toast.makeText(BuscarAlumnoActivity.this, "Consulta incorrecto", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(BuscarAlumnoActivity.this);
                        builder.setMessage("Error de consulta")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                        btnBuscar.setEnabled(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        BuscarAlumno buscarAlumno = new BuscarAlumno(Apellido, Curso, respoListener2);
        RequestQueue queue = Volley.newRequestQueue(BuscarAlumnoActivity.this);
        queue.add(buscarAlumno);
    }
}