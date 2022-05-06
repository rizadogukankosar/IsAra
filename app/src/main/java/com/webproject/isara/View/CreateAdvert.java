package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Controller.Cities;
import com.webproject.isara.Controller.SendToDatabase;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.Model.User;
import com.webproject.isara.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.UUID;

public class CreateAdvert extends AppCompatActivity {
    EditText advertTitleText;
    EditText advertDescriptionText;
    EditText advertWageText;
    Spinner citiesSpinner;
    ProgressBar progressBar;
    ArrayAdapter<String> adapter;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    Button shareAdvertButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);
        getSupportActionBar().hide();
        advertTitleText = findViewById(R.id.advertTitleTextCreateAdvertActivity);
        advertDescriptionText = findViewById(R.id.descriptionTextCreateAdvertActivity);
        advertWageText = findViewById(R.id.wageTextCreateAdvertActivity);
        citiesSpinner = findViewById(R.id.citiesSpinnerCreateAdvertActivity);
        progressBar = findViewById(R.id.progressBarCreateAdvertActivity);
        shareAdvertButton = findViewById(R.id.shareAdvertButton);
        Cities cities = new Cities(this);
        ArrayList<String> citiesList = cities.getCitiesFromJson(false);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, citiesList);
        citiesSpinner.setAdapter(adapter);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    public void ShareAdvertButton(View view){
       // String uuid = UUID.randomUUID().toString();

        String title = advertTitleText.getText().toString().trim();
        String description  = advertDescriptionText.getText().toString().trim();
        String wage = advertWageText.getText().toString().trim();
        String city = (String) citiesSpinner.getSelectedItem();
        if (!title.equals("") && !description.equals("") && !wage.equals("") && !city.equals("")){
            progressBar.setVisibility(View.VISIBLE);
            shareAdvertButton.setClickable(false);
            Advert advert = new Advert(title,description,city,wage,mAuth.getCurrentUser().getUid());
            String key = database.getReference().child("adverts").push().getKey();
            SendToDatabase sendToDatabase = new SendToDatabase(this,database);
            sendToDatabase.SendAdvert(key,advert,shareAdvertButton,progressBar);
        }else{
            Toast.makeText(this, "Lütfen tüm bilgileri doldurun!", Toast.LENGTH_SHORT).show();
        }

    }


}