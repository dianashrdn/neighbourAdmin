package com.example.neighbouradmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neighbouradmin.Controller.IncidentController;

public class IncidentListFragment extends Fragment {
    RecyclerView incidentRecycler;
    IncidentAdapter incidentAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.incident_fragment, container, false);
        incidentRecycler = rootView.findViewById(R.id.incidentRecycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        incidentAdapter = new IncidentAdapter(getContext());
        IncidentController incidentController = new IncidentController(getContext());
        incidentController.getIncidents(incidentAdapter);
        incidentRecycler.setLayoutManager(layoutManager);

        incidentRecycler.setAdapter(incidentAdapter);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Incident List");
    }
}
