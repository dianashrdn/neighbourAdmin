package com.example.neighbouradmin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.neighbouradmin.Controller.ResidentController;
import com.example.neighbouradmin.model.Resident;
import com.google.firebase.firestore.auth.User;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentAdapter.ViewHolder> {
    private ArrayList<Resident> residents;
    private Context context;

    public ResidentAdapter(ArrayList<? extends Resident> mResidentList) {
    }

    public ResidentAdapter(Context context) {
        residents = new ArrayList<>();
        this.context = context;
    }

    public void setResidents(HashMap<String, Resident> residents){
        this.residents = new ArrayList<>(residents.values());
    }

    @Override
    public ResidentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resident_list_card, parent, false);
        return new ResidentAdapter.ViewHolder(itemView);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(final ResidentAdapter.ViewHolder holder, int position) {
        final Resident resident = residents.get(position);
        ResidentController residentController = new ResidentController(context);
        holder.progressBar.setVisibility(View.VISIBLE);
        holder.username.setText(resident.getUsername());
        holder.email.setText(resident.getEmail());
        holder.address.setText(resident.getAddress());
        holder.phoneNumber.setText(resident.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return residents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username, address, email, phoneNumber, verify;
        public ProgressBar progressBar;
        private CardView userCard;
        private CheckBox checkBox;
        private Resident resident;


        public ViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBarUser);
            username = itemView.findViewById(R.id.cardResidentName);
            email = itemView.findViewById(R.id.cardResidentEmail);
            address = itemView.findViewById(R.id.cardResidentAddress);
            phoneNumber = itemView.findViewById(R.id.cardResidentPhone);
            verify = itemView.findViewById(R.id.userVerify);
            userCard = itemView.findViewById(R.id.userCard);
            checkBox = itemView.findViewById(R.id.checkBox);

            /*checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkBox.isChecked()){
                        resident.setVerify(1);
                    }else
                        resident.setVerify(0);
                }
            });*/

        }
    }


}
