package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 27/11/2017.
 */

public class Verificacion extends StringRequest {
    private static final String LOGIN_REQUEST_URL = ConexionDB.serverPath() + "verificacion.php";
    private Map<String,String> params;
    public Verificacion (int DNI, Response.Listener<String> listener) {
        super (Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("DNI", Integer.toString(DNI));
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
