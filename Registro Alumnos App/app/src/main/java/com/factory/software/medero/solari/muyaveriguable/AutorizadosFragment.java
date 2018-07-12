package com.factory.software.medero.solari.muyaveriguable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AutorizadosFragment extends Fragment implements View.OnClickListener{
    Button btnRegAut, btnBusAut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_autorizados, container, false);

        btnRegAut = (Button) view.findViewById(R.id.btnRegAutAct);
        btnRegAut.setOnClickListener(this);
        btnBusAut = (Button) view.findViewById(R.id.btnBusAutAct);
        btnBusAut.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBusAutAct:
                Intent intent = new Intent(getActivity(), BuscarAutorizadoActivity.class);
                getActivity().startActivity(intent);
                break;

            case R.id.btnRegAutAct:
                Intent intent2 = new Intent(getActivity(), RegistrarAutorizadoActivity.class);
                getActivity().startActivity(intent2);
                break;
            default:
                break;
        }
    }
}