package com.factory.software.medero.solari.muyaveriguable;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 29/11/2017.
 */

public class Verificacion3 extends StringRequest {
    private static final String LOGIN_REQUEST_URL = ConexionDB.serverPath() + "verificacion3.php";
    private Map<String, String> params;
    public Verificacion3 (int DNI, String Apellido, Response.Listener<String> listener) {
        super (Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("DNI", DNI+"");
        params.put("Apellido", Apellido);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
