package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Controller.GetUserInfosFromDatabase;
import com.webproject.isara.Model.User;
import com.webproject.isara.R;

import java.io.Serializable;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    TextView nameText;
    TextView phoneText;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        nameText = findViewById(R.id.nameTextProfileActivity);
        phoneText = findViewById(R.id.phoneTextProfileActivity);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        GetUserInfosFromDatabase getUserInfosFromDatabase = new GetUserInfosFromDatabase(this,database);
        getUserInfosFromDatabase.getUserInfos(mAuth.getCurrentUser().getUid(),nameText,phoneText);
    }

    public void NewAdvert(View view){
        Intent intent = new Intent(getApplicationContext(), CreateAdvert.class);
        startActivity(intent);

    }
    public void MyAdverts(View view){
        Intent intent = new Intent(getApplicationContext(),MyAdverts.class);
        startActivity(intent);
    }

    public void SignOut(View view){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Çıkış yaptınız", Toast.LENGTH_SHORT).show();
    }
}