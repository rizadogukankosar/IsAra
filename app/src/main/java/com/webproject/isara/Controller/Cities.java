package com.webproject.isara.Controller;

import android.content.Context;

import com.webproject.isara.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Cities {

    private Context context;

    public Cities(Context context) {
        this.context = context;
    }

    public ArrayList<String> getCitiesFromJson(Boolean allCities){
        String json = loadFileFromAsset();
        ArrayList<String> cities = new ArrayList<>();
        if (allCities)
            cities.add("Tüm Şehirler");
        try {
            JSONObject obj = new JSONObject(json);
            for (int i = 1; i <= 81 ; i++) {
                String city = String.valueOf(i) + " - " + obj.getString(String.valueOf(i));
                cities.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cities;
    }

    private String loadFileFromAsset() {
        String jsonfileContent = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.cities);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            jsonfileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonfileContent;
    }
}
