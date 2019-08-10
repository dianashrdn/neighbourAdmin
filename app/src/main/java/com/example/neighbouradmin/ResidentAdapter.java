package com.example.neighbouradmin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

       /* if (resident.getPhoto() != null) {
            Glide.with(context).load(resident.getPhoto()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.INVISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.INVISIBLE);
                    return false;
                }
            }).into(holder.photo);
        }
        //            new UserAdapter.DownloadImageTask(holder.photo, holder.progressBar).execute(user.getPhoto());
        else {
            System.out.println("No picture");
            holder.progressBar.setVisibility(View.INVISIBLE);
            holder.photo.setImageResource(R.drawable.ic_home_black_24dp);
        }*/
    }

    @Override
    public int getItemCount() {
        return residents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username, address, email, phoneNumber;
        public ImageView photo;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBarH);
            username = itemView.findViewById(R.id.cardResidentName);
            email = itemView.findViewById(R.id.cardResidentEmail);
            address = itemView.findViewById(R.id.cardResidentAddress);
            phoneNumber = itemView.findViewById(R.id.cardResidentPhone);
        }
    }
}
