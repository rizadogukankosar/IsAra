package com.webproject.isara.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.webproject.isara.Controller.Cities;
import com.webproject.isara.Controller.GetAdvertsFromDatabase;
import com.webproject.isara.Model.Advert;
import com.webproject.isara.Model.User;
import com.webproject.isara.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ArrayAdapter<String> adapter;
    Spinner citiesSpinner;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearLayout = findViewById(R.id.linear_homeActivity);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        citiesSpinner = findViewById(R.id.citiesSpinnerHomeActivity);
        refreshLayout = findViewById(R.id.refreshLayoutHomeActivity);
        Cities cities = new Cities(this);
        ArrayList<String> citiesList = cities.getCitiesFromJson(true);
        GetAdvertsFromDatabase getAdvertsFromDatabase = new GetAdvertsFromDatabase(this,database,linearLayout);
        getAdvertsFromDatabase.setRefreshLayout(refreshLayout);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, citiesList);
        citiesSpinner.setAdapter(adapter);
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayout.removeAllViews();
                if (i!= 0)
                    getAdvertsFromDatabase.getAdvertsWithCity((String) citiesSpinner.getSelectedItem());
                else
                    getAdvertsFromDatabase.getAllAdverts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                linearLayout.removeAllViews();
                if (citiesSpinner.getSelectedItemPosition() == 0){
                    getAdvertsFromDatabase.getAllAdverts();
                }else{
                    getAdvertsFromDatabase.getAdvertsWithCity((String) citiesSpinner.getSelectedItem());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                if (mAuth.getCurrentUser() == null){
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}