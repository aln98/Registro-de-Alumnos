package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 23/11/2017.
 */

public class RegistrarAutorizado extends StringRequest{
    private static final String REGISTER_REQUEST_URL="http://192.168.0.189/Averigable/registrarAutorizado.php";
    private Map<String,String> params;
    public RegistrarAutorizado (int DNI, String Nombre, String Apellido, int Telefono, Response.Listener<String> listener) {
        super (Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("DNI", DNI+"");
        params.put("Nombre", Nombre);
        params.put("Apellido", Apellido);
        params.put("Telefono", Telefono+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

