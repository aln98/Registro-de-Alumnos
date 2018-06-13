package com.factory.software.medero.solari.muyaveriguable;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AlumnosFragment extends Fragment implements View.OnClickListener {
    Button btnRegAl, btnBusAl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_alumnos, container, false);

        btnRegAl = (Button) view.findViewById(R.id.btnRegAlAct);
        btnRegAl.setOnClickListener(this);
        btnBusAl = (Button) view.findViewById(R.id.btnBusAlAct);
        btnBusAl.setOnClickListener(this);

        return view;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegAlAct:
                Intent intent = new Intent(getActivity(), RegistarAlumnoActivity.class);
                getActivity().startActivity(intent);
                break;

            case R.id.btnBusAlAct:
                Intent intent2 = new Intent(getActivity(), BuscarAlumnoActivity.class);
                getActivity().startActivity(intent2);
                break;
            default:
                break;
        }
    }
}