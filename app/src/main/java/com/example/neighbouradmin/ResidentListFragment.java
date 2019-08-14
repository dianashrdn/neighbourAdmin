package com.example.neighbouradmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.neighbouradmin.Controller.ResidentController;
import com.example.neighbouradmin.model.Resident;
import com.google.common.base.Verify;

public class ResidentListFragment extends Fragment {
    RecyclerView residentRecycler;
    ResidentAdapter residentAdapter;
    private TextView username, email, address, phoneNumber, userVerify;
    private CardView userCard;
    private CheckBox checkBox;
    private View rootView;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.resident_fragment, container, false);
        residentRecycler = rootView.findViewById(R.id.homeRecycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        residentAdapter = new ResidentAdapter(getContext());
        ResidentController residentController = new ResidentController(getContext());
        residentController.getResidents(residentAdapter);
        residentRecycler.setLayoutManager(layoutManager);

        residentRecycler.setAdapter(residentAdapter);
        username = rootView.findViewById(R.id.cardResidentName);
        email = rootView.findViewById(R.id.cardResidentEmail);
        address = rootView.findViewById(R.id.cardResidentAddress);
        phoneNumber = rootView.findViewById(R.id.cardResidentPhone);
        checkBox = rootView.findViewById(R.id.checkBox);
        userCard = rootView.findViewById(R.id.userCard);
        userVerify = rootView.findViewById(R.id.userVerify);

        return rootView;

    }

    @SuppressWarnings("deprecation")
   //@Override
    public void returnUser(final Resident resident) {
        Thread userReturnThread = new Thread() {
            @Override
            public void run() {
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (checkBox.isChecked()) {
                            resident.setVerify(1);
                        } else
                            resident.setVerify(0);
                    }
                });
            }
        };
    }
}
