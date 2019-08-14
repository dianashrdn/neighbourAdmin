package com.example.neighbouradmin.Controller;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.neighbouradmin.LoginActivity;
import com.example.neighbouradmin.model.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AdminController {
    private FirebaseFirestore db;
    private Context context;
    private Admin admin;
    private StorageReference storageReference;
    public AdminController(Context context){
        storageReference = FirebaseStorage.getInstance().getReference();
        this.context=context;
        db = FirebaseFirestore.getInstance();
    }

    public void setAdmin(final Admin admin){
        this.admin= admin;
    }

    //get data for login
    public void getAdmin(String email, final LoginActivity activity){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("admin").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        Admin retrievedAdmin = documentSnapshot.toObject(Admin.class);
                        activity.setAdmin(retrievedAdmin);
                    }
                    else
                        activity.setAdmin(null);
                }
            }
        });
    }
}
