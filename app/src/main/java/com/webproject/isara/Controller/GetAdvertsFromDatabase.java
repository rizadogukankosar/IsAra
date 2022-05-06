package com.webproject.isara.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.R;
import com.webproject.isara.View.HomeActivity;
import com.webproject.isara.View.JobDetailsActivity;
import com.webproject.isara.View.MyAdverts;

import java.io.Serializable;

public class GetAdvertsFromDatabase {

    private Context context;
    private FirebaseDatabase database;
    private LinearLayout linearLayout;
    private SwipeRefreshLayout refreshLayout;

    public GetAdvertsFromDatabase(Context context, FirebaseDatabase database, LinearLayout linearLayout) {
        this.context = context;
        this.database = database;
        this.linearLayout = linearLayout;
    }

    public GetAdvertsFromDatabase(){

    }

    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public void getAdvertsWithCity(String city){
        database.getReference().child("adverts").orderByChild("city").equalTo(city).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Advert advert = dataSnapshot.getValue(Advert.class);
                    advert.setAdvertID(dataSnapshot.getKey());
                    View layout = LayoutInflater.from(context).inflate(R.layout.advert_item, linearLayout, false);
                    TextView titleText = layout.findViewById(R.id.titleTextAdvertItem);
                    TextView descriptionText = layout.findViewById(R.id.descriptionTextAdvertItem);
                    TextView wageText = layout.findViewById(R.id.wageTextAdvertItem);
                    TextView cityText = layout.findViewById(R.id.cityTextAdvertItem);
                    String[] citySplit = advert.getCity().split(" - ");
                    titleText.setText(advert.getTitle());
                    descriptionText.setText(advert.getDescription());
                    wageText.setText("Ücret: " + advert.getWage() + " TL");
                    cityText.setText(citySplit[1]);

                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, JobDetailsActivity.class);
                            intent.putExtra("Advert", (Serializable) advert);
                            context.startActivity(intent);
                        }
                    });
                    linearLayout.addView(layout,0);
                    if (refreshLayout != null && refreshLayout.isRefreshing()){
                        refreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllAdverts(){
        database.getReference().child("adverts").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Advert advert = dataSnapshot.getValue(Advert.class);
                    advert.setAdvertID(dataSnapshot.getKey());
                    View layout = LayoutInflater.from(context).inflate(R.layout.advert_item, linearLayout, false);
                    TextView titleText = layout.findViewById(R.id.titleTextAdvertItem);
                    TextView descriptionText = layout.findViewById(R.id.descriptionTextAdvertItem);
                    TextView wageText = layout.findViewById(R.id.wageTextAdvertItem);
                    TextView cityText = layout.findViewById(R.id.cityTextAdvertItem);
                    String[] citySplit = advert.getCity().split(" - ");
                    titleText.setText(advert.getTitle());
                    descriptionText.setText(advert.getDescription());
                    wageText.setText("Ücret: " + advert.getWage() + " TL");
                    cityText.setText(citySplit[1]);

                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context,JobDetailsActivity.class);
                            intent.putExtra("Advert", (Serializable) advert);
                            context.startActivity(intent);
                        }
                    });
                    linearLayout.addView(layout,0);
                    if (refreshLayout != null && refreshLayout.isRefreshing()){
                        refreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMyAdverts(String userID){
        database.getReference().child("adverts").orderByChild("userID").equalTo(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Advert advert = dataSnapshot.getValue(Advert.class);
                    advert.setAdvertID(dataSnapshot.getKey());
                    View layout = LayoutInflater.from(context).inflate(R.layout.advert_item, linearLayout, false);
                    TextView titleText = layout.findViewById(R.id.titleTextAdvertItem);
                    TextView descriptionText = layout.findViewById(R.id.descriptionTextAdvertItem);
                    TextView wageText = layout.findViewById(R.id.wageTextAdvertItem);
                    TextView cityText = layout.findViewById(R.id.cityTextAdvertItem);
                    String[] citySplit = advert.getCity().split(" - ");
                    titleText.setText(advert.getTitle());
                    descriptionText.setText(advert.getDescription());
                    wageText.setText("Ücret: " + advert.getWage() + " TL");
                    cityText.setText(citySplit[1]);

                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context,JobDetailsActivity.class);
                            intent.putExtra("Advert", (Serializable) advert);
                            intent.putExtra("MyAdvert", true);
                            context.startActivity(intent);
                        }
                    });
                    linearLayout.addView(layout,0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
