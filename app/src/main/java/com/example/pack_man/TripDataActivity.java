package com.example.pack_man;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class TripDataActivity extends AppCompatActivity {

    TextView textDate;
    Button selectDate;
    Long start;
    Long end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_data);

        textDate = findViewById(R.id.tripDateID);
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
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        //textDate.setText(datePicker.getHeaderText());
                        Pair<Long, Long> pair = (Pair<Long,Long>)selection;
                        start = pair.first;
                        end = pair.second;
                        String str = start.toString() + " " + end.toString();
                        textDate.setText(str);
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
