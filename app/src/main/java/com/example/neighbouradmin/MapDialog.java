package com.example.neighbouradmin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.neighbouradmin.model.Incident;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapDialog extends AppCompatDialogFragment {
    private MapView mapView;
    private GoogleMap googleMap;
    private Incident incident;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle = getArguments();
        this.incident = (Incident) bundle.getSerializable("incident");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.map_dialog, null);

        builder.setView(view)
                .setTitle("Incident Location")
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        mapView = view.findViewById(R.id.dialogMap);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        MapsInitializer.initialize(getActivity().getApplicationContext());
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng( incident.getLocation().getLatitude(),
                                    incident.getLocation().getLongitude()),15));
                googleMap.addMarker(new MarkerOptions() .title(incident.getDescription())
                                    .position(new LatLng(   incident.getLocation().getLatitude(),
                                                            incident.getLocation().getLongitude()))
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        });


        return builder.create();
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}
