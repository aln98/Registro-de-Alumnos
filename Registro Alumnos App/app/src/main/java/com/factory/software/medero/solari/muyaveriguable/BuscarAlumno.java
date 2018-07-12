package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 22/11/2017.
 */

public class BuscarAlumno extends StringRequest{
    private static final String LOGIN_REQUEST_URL = ConexionDB.serverPath() + "BuscarAlumno.php";
    private Map<String,String> params;
    public BuscarAlumno(String Apellido, int DNI, Response.Listener<String> listener) {
        super (Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Apellido", Apellido);
        params.put("DNI", Integer.toString(DNI));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}