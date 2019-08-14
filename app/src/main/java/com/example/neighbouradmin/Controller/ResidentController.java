package com.example.neighbouradmin.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.neighbouradmin.ResidentAdapter;
import com.example.neighbouradmin.model.Resident;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class ResidentController {
    private FirebaseFirestore db;
    private Context context;
    private Resident resident;
    private StorageReference storageReference;
    public ResidentController(Context context)
    {
        storageReference = FirebaseStorage.getInstance().getReference();
        this.context=context;
        FirebaseApp.initializeApp(context);
        db = FirebaseFirestore.getInstance();
    }

    public void setResident(final Resident resident){
        this.resident=resident;
    }

    //get data resident(User)
    public void getResidents(final ResidentAdapter residentAdapter){
        final HashMap<String, Resident> residents = new HashMap<>();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Thread getResidentThread = new Thread(){
            @Override
            public void run(){
                db.collection("users")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    Resident resident = documentSnapshot.toObject(Resident.class);
                                    resident.setUserId(documentSnapshot.getId());
                                    residents.put(resident.getUserId(), resident);
                                }
                                residentAdapter.setResidents(residents);
                                residentAdapter.notifyDataSetChanged();
                            }
                        });
            }
        };
        getResidentThread.run();
    }

    /*public boolean isVerify(){
        final int VERIFIED
    }*/


}
