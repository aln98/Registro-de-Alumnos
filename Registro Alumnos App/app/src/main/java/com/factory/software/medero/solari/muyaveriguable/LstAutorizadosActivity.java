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

public class LstAutorizadosActivity extends AppCompatActivity {
    ListView lstResultado2;
    String nombre, curso, apellido;
    TextView txtApellido, txtCurso, txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_autorizados);
        lstResultado2 = findViewById(R.id.lstResultado2);

        txtApellido = findViewById(R.id.textApellido);
        apellido = getIntent().getExtras().getString("Apellido");
        txtApellido.setText(apellido);

        txtCurso = findViewById(R.id.textCurso);
        curso = getIntent().getExtras().getString("Curso");
        txtCurso.setText(curso);

        txtNombre = findViewById(R.id.textNombre);
        nombre = getIntent().getExtras().getString("Nombre");
        txtNombre.setText(nombre);

        click1();
    }

    private void click1() {
        final String Curso = txtCurso.getText().toString();
        final String Apellido = txtApellido.getText().toString();
        final String Nombre = txtNombre.getText().toString();
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    JSONArray jArray = jsonResponse.getJSONArray("datos");

                    if (success) {
                        Toast.makeText(LstAutorizadosActivity.this, "Datos consultados", Toast.LENGTH_SHORT).show();
                        txtApellido.setText("");
                        txtCurso.setText("");
                        txtNombre.setText("");

                        ArrayList<String> items = new ArrayList<String>();
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            int dni = json_data.getInt("DNI");
                            String nombre = json_data.getString("Nombre");
                            String apellido = json_data.getString("Apellido");
                            String telefono = json_data.getString("Telefono");

                            items.add("DNI: " + Integer.toString(dni) + "\nNombre: " + nombre + " - Apellido: " + apellido + "\nTelefono: " + telefono);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LstAutorizadosActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, items);

                        lstResultado2.setAdapter(adapter);

                    } else {
                        Toast.makeText(LstAutorizadosActivity.this, "Consulta incorrecto", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(LstAutorizadosActivity.this);
                        builder.setMessage("Error de consulta")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        BuscarAutorizado buscarAutorizado = new BuscarAutorizado(Curso, Apellido, Nombre, respoListener);//aca
        RequestQueue queue = Volley.newRequestQueue(LstAutorizadosActivity.this);
        queue.add(buscarAutorizado);
    }
}
