package com.example.neighbouradmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neighbouradmin.Controller.ResidentController;

public class ResidentListFragment extends Fragment {
    RecyclerView residentRecycler;
    ResidentAdapter residentAdapter;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.resident_fragment, container, false);
        residentRecycler = rootView.findViewById(R.id.homeRecycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        residentAdapter = new ResidentAdapter(getContext());
        ResidentController residentController = new ResidentController(getContext());
        residentController.getResidents(residentAdapter);
        residentRecycler.setLayoutManager(layoutManager);

        residentRecycler.setAdapter(residentAdapter);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Resident List");
    }
}
