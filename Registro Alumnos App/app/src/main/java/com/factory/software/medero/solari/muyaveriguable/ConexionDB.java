package com.factory.software.medero.solari.muyaveriguable;

import android.app.Application;

/**
 * Created by Coto on 25/11/2017.
 */

public class ConexionDB extends Application {
    public static String serverPath() {
        return "http://192.168.43.228/MuyAveriguable/";
    }
}