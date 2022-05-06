package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Controller.GetUserInfosFromDatabase;
import com.webproject.isara.Controller.RemoveItemFromDatabase;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.Model.User;
import com.webproject.isara.R;

public class JobDetailsActivity extends AppCompatActivity {
    TextView titleText;
    TextView wageText;
    TextView cityText;
    TextView descriptionText;
    TextView userNameText;
    TextView userPhoneText;
    FirebaseDatabase database;
    Button deleteAdvertButton;
    FirebaseAuth mAuth;
    Boolean isMyAdvert;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        titleText = findViewById(R.id.titleTextJobDetailsActivity);
        wageText = findViewById(R.id.wageTextJobDetailsActivity);
        cityText = findViewById(R.id.cityTextJobDetailsActivity);
        descriptionText = findViewById(R.id.descriptionTextJobDetailsActivity);
        userNameText = findViewById(R.id.userNameTextJobDetailsActivity);
        userPhoneText = findViewById(R.id.userPhoneTextJobDetailsActivity);
        deleteAdvertButton = findViewById(R.id.deleteAdvertButton);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Advert advert = (Advert) getIntent().getSerializableExtra("Advert");
        titleText.setText(advert.getTitle());
        descriptionText.setText(advert.getDescription());
        wageText.setText("Ücret: " + advert.getWage() + " TL");
        String[] citySplit = advert.getCity().split(" - ");
        cityText.setText(citySplit[1]);
        GetUserInfosFromDatabase getUserInfosFromDatabase = new GetUserInfosFromDatabase(this,database);
        getUserInfosFromDatabase.getUserInfos(advert.getUserID(),userNameText,userPhoneText);
        deleteAdvertButton.setClickable(false);
        deleteAdvertButton.setVisibility(View.INVISIBLE);
        isMyAdvert = getIntent().getBooleanExtra("MyAdvert",false);
        if (mAuth.getCurrentUser() != null && advert.getUserID().equals(mAuth.getCurrentUser().getUid()) && isMyAdvert){
            deleteAdvertButton.setClickable(true);
            deleteAdvertButton.setVisibility(View.VISIBLE);
            deleteAdvertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RemoveItemFromDatabase removeItemFromDatabase = new RemoveItemFromDatabase(JobDetailsActivity.this,database);
                    removeItemFromDatabase.removeAdvert(advert);
                }
            });
        }


    }


}