package com.webproject.isara.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.View.HomeActivity;
import com.webproject.isara.View.JobDetailsActivity;

public class RemoveItemFromDatabase {
    private Context context;
    private FirebaseDatabase database;

    public RemoveItemFromDatabase(Context context, FirebaseDatabase database) {
        this.context = context;
        this.database = database;
    }

    public void removeAdvert(Advert advert){
        database.getReference().child("adverts").child(advert.getAdvertID()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "İlan Silindi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, HomeActivity.class);
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
}
