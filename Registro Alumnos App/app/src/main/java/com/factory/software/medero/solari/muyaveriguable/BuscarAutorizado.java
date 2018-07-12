package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 27/11/2017.
 */

public class BuscarAutorizado extends StringRequest {
    private static final String LOGIN_REQUEST_URL = ConexionDB.serverPath() + "BuscarAutorizado.php";
    private HashMap<String,String> params;
    public BuscarAutorizado(String Curso, String Apellido, String Nombre, Response.Listener<String> listener) {
        super (Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Curso", Curso);
        params.put("Apellido", Apellido);
        params.put("Nombre", Nombre);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
