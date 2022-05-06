package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.webproject.isara.Controller.SendToDatabase;
import com.webproject.isara.R;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText emailText;
    EditText passwordText;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        emailText = findViewById(R.id.editTextemail);
        passwordText = findViewById(R.id.editTextpassword);
        mAuth = FirebaseAuth.getInstance();

    }

    public void Login(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        SendToDatabase sendToDatabase = new SendToDatabase(this,mAuth);
        sendToDatabase.Login(email,password);

    }

    public void GoSignActivity(View view){
        Intent intent = new Intent(getApplicationContext(),SignActivity.class);
        startActivity(intent);
    }
}