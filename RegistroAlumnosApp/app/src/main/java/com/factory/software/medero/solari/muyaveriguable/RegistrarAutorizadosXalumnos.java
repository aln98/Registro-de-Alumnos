package com.factory.software.medero.solari.muyaveriguable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Coto on 23/11/2017.
 */

public class RegistrarAutorizadosXalumnos extends StringRequest{

    private static final String REGISTER_REQUEST_URL="http://192.168.0.189/Averigable/registrarAutorizadoXalumno.php";
    private Map<String,String> params;
    public RegistrarAutorizadosXalumnos (int AlumnoDNI, int AutorizadoDNI, Response.Listener<String> listener2) {
        super (Request.Method.POST, REGISTER_REQUEST_URL, listener2, null);
        params = new HashMap<>();

        params.put("AlumnoDNI", AlumnoDNI+"");
        params.put("AutorizadoDNI", AutorizadoDNI+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}