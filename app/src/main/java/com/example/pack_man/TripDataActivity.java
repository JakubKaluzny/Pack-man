package com.example.pack_man;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TripDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_data);
    }


    public void setPreference(String key, String value){
        SharedPreferences sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE);
        sharedPref
                .edit()
                .putString(key, value)
                .apply();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void goToExpandableListActivity(View view){
        Intent intent = new Intent(this, ExpandableListActivity.class);
        EditText inputline = findViewById(R.id.dlugoscWyjazduID);
        String strInputline = inputline.getText().toString();
        if(isNumeric(strInputline) && strInputline.length() <= 2){
            setPreference("DLUGOSC_WYJAZDU", inputline.getText().toString());
            startActivity(intent);
        }
        else{
            inputline.setError("You have entered wrong value!");
        }
    }

}
