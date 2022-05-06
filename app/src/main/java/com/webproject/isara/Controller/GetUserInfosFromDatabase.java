package com.webproject.isara.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Model.User;
import com.webproject.isara.View.JobDetailsActivity;

public class GetUserInfosFromDatabase {

    private Context context;
    private FirebaseDatabase database;

    public GetUserInfosFromDatabase(Context context, FirebaseDatabase database) {
        this.context = context;
        this.database = database;
    }

    public GetUserInfosFromDatabase(){

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

    public void getUserInfos(String userID, TextView userNameText,  TextView userPhoneText){
        database.getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                userNameText.setText(user.getName() + " " + user.getSurname());
                userPhoneText.setText("Telefon: " + user.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
