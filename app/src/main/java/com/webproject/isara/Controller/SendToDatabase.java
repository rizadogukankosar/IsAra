package com.webproject.isara.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.Model.User;
import com.webproject.isara.View.HomeActivity;
import com.webproject.isara.View.LoginActivity;
import com.webproject.isara.View.SignActivity;

public class SendToDatabase {
    private Context context;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    public SendToDatabase(Context context, FirebaseDatabase database) {
        this.context = context;
        this.database = database;
    }

    public SendToDatabase(Context context, FirebaseAuth mAuth) {
        this.context = context;
        this.mAuth = mAuth;
    }
    public SendToDatabase(Context context, FirebaseAuth mAuth, FirebaseDatabase database) {
        this.context = context;
        this.mAuth = mAuth;
    }


    public void CreateUser(User user,String password){
        mAuth.createUserWithEmailAndPassword(user.getEmail(),password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                database.getReference().child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);
                Intent intent = new Intent(context,HomeActivity.class);
                context.startActivity(intent);
                ((Activity)(context)).finishAffinity();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Login(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,HomeActivity.class);
                context.startActivity(intent);
                ((Activity)(context)).finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void SendAdvert(String key, Advert advert, Button shareButton, ProgressBar progressBar){
        database.getReference().child("adverts").child(key).setValue(advert).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "İlan paylaşıldı", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
                ((Activity)(context)).finishAffinity();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                shareButton.setClickable(true);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
