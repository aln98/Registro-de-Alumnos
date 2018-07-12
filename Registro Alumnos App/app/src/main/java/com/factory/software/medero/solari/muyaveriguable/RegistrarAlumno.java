package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 23/11/2017.
 */

public class RegistrarAlumno extends StringRequest{
    private static final String REGISTER_REQUEST_URL = ConexionDB.serverPath() + "registrarAlumno.php";
    private Map<String,String> params;
    public RegistrarAlumno (int DNI, String Nombre, String Apellido, String FechaNacimiento, String Direccion, String Curso, String TelefonoContacto, Response.Listener<String> listener) {
        super (Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("DNI", DNI+"");
        params.put("Nombre", Nombre);
        params.put("Apellido", Apellido);
        params.put("FechaNacimiento", FechaNacimiento);
        params.put("Direccion", Direccion);
        params.put("Curso", Curso);
        params.put("TelefonoContacto", TelefonoContacto);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
