package com.example.neighbouradmin.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.neighbouradmin.IncidentAdapter;
import com.example.neighbouradmin.model.Incident;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.HashMap;

public class IncidentController {

    private FirebaseFirestore db;
    private Context context;
    private Incident incident;
    private StorageReference storageReference;
    public IncidentController(Context context) {
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }

    public void setIncident(final Incident incident){
        this.incident = incident;
    }

    //get data
    public void getIncidents(final IncidentAdapter incidentAdapter){
        final HashMap<String, Incident> incidents = new HashMap<>();
//        IncidentDB localDB = new IncidentDB(context);
//        localDB.fnGetIncidents(IncidentDB.tblNameIncident, incidents);
//        localDB.fnGetIncidents(IncidentDB.tblNameIncidentToUpload, incidents);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Thread getIncidentThread = new Thread(){
            @Override
            public void run() {
                db.collection("incidents")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for( QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Incident incident = documentSnapshot.toObject(Incident.class);
                                    incident.setIncidentId(documentSnapshot.getId());
                                    System.out.println(incident.getIncidentId());
                                    System.out.println(incident.getIncidentName());
                                    incidents.put(incident.getIncidentId(), incident);

                                }
                                incidentAdapter.setIncidents(incidents);
                                incidentAdapter.notifyDataSetChanged();
                            }
                        });
            }
        };
        getIncidentThread.run();
    }

    //get map location
    public void getIncidentsLocation(final GoogleMap googleMap){
        final HashMap<String, Marker> markers = new HashMap<>();
//        final HashMap<String, Incident> incidents = new HashMap<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        IncidentDB localDB = new IncidentDB(context);
//        localDB.fnGetIncidents(IncidentDB.tblNameIncident, incidents);
//        localDB.fnGetIncidents(IncidentDB.tblNameIncidentToUpload, incidents);

//        for (Incident incident : incidents.values()){
//            Marker marker = googleMap.addMarker(getPlaceMarker(incident));
//            marker.setTag(incident.getIncidentId());
//            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//            markers.put(incident.getIncidentId(), marker);
//        }

        db.collection("incidents")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for( QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Incident incident = documentSnapshot.toObject(Incident.class);
                            incident.setIncidentId(documentSnapshot.getId());
                            Marker marker = googleMap.addMarker(getPlaceMarker(incident));
                            marker.setTag(incident.getIncidentId());
                            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                            markers.put(incident.getIncidentId(), marker);
                        }
                    }
    });
}


    public MarkerOptions getPlaceMarker(Incident incident){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(incident.getIncidentName());
        markerOptions.position(new LatLng(incident.getLocation().getLatitude(),incident.getLocation().getLongitude()));
        return markerOptions;
    }
    //to download image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            System.out.println("Getting image");
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Image retrieved");
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
