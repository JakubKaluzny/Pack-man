package com.example.pack_man;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class TripDataActivity extends AppCompatActivity {

    TextView seasonText;
    Button selectDate;
    String season;
    Long start;
    Long end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_data);

        seasonText = findViewById(R.id.seasonID);
        selectDate = findViewById(R.id.choseDateButtonID);

        MaterialDatePicker datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()))
                .setTitleText("Choose trip date")
                .build();

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "Material_Range");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        //textDate.setText(datePicker.getHeaderText());
                        Pair<Long, Long> pair = (Pair<Long,Long>)selection;
                        start = pair.first;
                        end = pair.second;
                        LocalDate ld = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate le = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();

                        season = DateAnalizer.getSeason(ld);
                        //String str = season + " Duration " + DateAnalizer.getDurationInDays(ld,le)+" days";
                        TextView tripLengthText = findViewById(R.id.tripLengthID);
                        tripLengthText.setText(String.valueOf(DateAnalizer.getDurationInDays(ld,le)) + " days");
                        tripLengthText.setTextColor(Color.parseColor("#660000"));
                        tripLengthText.setTextSize(24);
                        seasonText.setText(season);
                        seasonText.setTextColor(Color.parseColor("#660000"));
                        seasonText.setTextSize(24);
                        TextView tripLen = findViewById(R.id.tripLengthID);
                        tripLen.setError(null);
                        seasonText.setError(null);
                    }
                });
            }
        });
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
        TextView tripLengthText = findViewById(R.id.tripLengthID);
        String str = tripLengthText.getText().toString();
        String[] days = str.split(" ");
        if(!tripLengthText.getText().toString().equals("trip length will be shown here") && !seasonText.getText().toString().equals("season will be shown here")){
            setPreference("DLUGOSC_WYJAZDU", days[0]);
            setPreference("PORA_ROKU",season);
            startActivity(intent);
        }
        else{
            tripLengthText.setError("You haven't entered date!");
            tripLengthText.setText("You haven't entered date!");
            tripLengthText.setTextColor(Color.RED);
            seasonText.setError("You haven't entered data!");
            seasonText.setText("You haven't entered date!");
            seasonText.setTextColor(Color.RED);
        }
    }

}
