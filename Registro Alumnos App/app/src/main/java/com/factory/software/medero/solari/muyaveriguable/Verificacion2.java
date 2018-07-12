package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 29/11/2017.
 */

public class Verificacion2 extends StringRequest {
    private static final String LOGIN_REQUEST_URL = ConexionDB.serverPath() + "verificacion2.php";
    private Map<String, String> params;
    public Verificacion2 (String Nombre, String Apellido, String Curso, Response.Listener<String> listener){
        super (Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Nombre", Nombre);
        params.put("Apellido", Apellido);
        params.put("Curso", Curso);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
