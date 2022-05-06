package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Controller.GetAdvertsFromDatabase;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.R;

import java.io.Serializable;

public class MyAdverts extends AppCompatActivity {
    LinearLayout linearLayout;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adverts);
        linearLayout = findViewById(R.id.linearMyAdverts);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        GetAdvertsFromDatabase getAdvertsFromDatabase = new GetAdvertsFromDatabase(this,database,linearLayout);
        getAdvertsFromDatabase.getMyAdverts(mAuth.getCurrentUser().getUid());
    }


}