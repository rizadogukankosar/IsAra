package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.webproject.isara.Controller.SendToDatabase;
import com.webproject.isara.Model.User;
import com.webproject.isara.R;

public class SignActivity extends AppCompatActivity {

    EditText nameText;
    EditText surnameText;
    EditText emailText;
    EditText passwordText;
    EditText phoneText;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        nameText = findViewById(R.id.editTextnameSignActivity);
        surnameText = findViewById(R.id.editTextsurnameSignActivity);
        emailText = findViewById(R.id.editTextemailSignActivity);
        passwordText = findViewById(R.id.editTextpasswordSignActivity);
        phoneText = findViewById(R.id.editTextphoneSignActivity);
    }
    public void Sign(View view){
        String name = nameText.getText().toString().trim();
        String surname = surnameText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString();
        String phone = phoneText.getText().toString().trim();
        if (name.equals("") || surname.equals("") || email.equals("") || password.equals("") || phone.equals("")){
            Toast.makeText(this, "Lütfen tüm boşlukları doldurunuz", Toast.LENGTH_LONG).show();
        }else{
            User user = new User(name,surname,phone,email);
            SendToDatabase sendToDatabase = new SendToDatabase(this,mAuth,database);
            sendToDatabase.CreateUser(user,password);
        }

    }
}