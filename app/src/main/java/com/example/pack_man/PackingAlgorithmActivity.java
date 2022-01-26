package com.example.pack_man;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackingAlgorithmActivity extends AppCompatActivity {

//    int tripLength;
//    List<String> listDataHeader;
//    HashMap<String, List<MyPair>> listDataChild;
//    private ArrayList<MyPair> suitcaseList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_algorithm);

        PackingAlgorithm packingAlgorithm = new PackingAlgorithm();
        String userItemsSpace = packingAlgorithm.calculateNeedeedSpace();

        if(userItemsSpace.equals("Not enough space")){
            findViewById(R.id.successImageID).setVisibility(View.GONE);
            findViewById(R.id.successText1ID).setVisibility(View.GONE);
            findViewById(R.id.successText2ID).setVisibility(View.GONE);
            findViewById(R.id.successText3ID).setVisibility(View.GONE);

            findViewById(R.id.failureImageID).setVisibility(View.VISIBLE);
            findViewById(R.id.failureText1ID).setVisibility(View.VISIBLE);
            findViewById(R.id.failureText2ID).setVisibility(View.VISIBLE);
            findViewById(R.id.failureText3ID).setVisibility(View.VISIBLE);
        }
        else{
            findViewById(R.id.failureImageID).setVisibility(View.GONE);
            findViewById(R.id.failureText1ID).setVisibility(View.GONE);
            findViewById(R.id.failureText2ID).setVisibility(View.GONE);
            findViewById(R.id.failureText3ID).setVisibility(View.GONE);

            findViewById(R.id.successImageID).setVisibility(View.VISIBLE);
            findViewById(R.id.successText1ID).setVisibility(View.VISIBLE);
            findViewById(R.id.successText2ID).setVisibility(View.VISIBLE);
            findViewById(R.id.successText3ID).setVisibility(View.VISIBLE);

            TextView text = findViewById(R.id.successText3ID);
            text.setText(userItemsSpace);
        }
    }

    private String getPreference(String key) {
        SharedPreferences sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public void testFileSaving(){
        File itemsFile= null;
        File suitcaseFile= null;

        FileOutputStream fileOutputStream = null;
        try {
            itemsFile = getFilesDir();
            fileOutputStream = openFileOutput("UserList.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(TripDataParser.parseItemsDataToString().getBytes());

            suitcaseFile = getFilesDir();
            fileOutputStream = openFileOutput("SuitcaseList.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(TripDataParser.parseSuitcaseDataToString().getBytes());

            Toast.makeText(this, "Your trip data has been saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.packing_algorithm_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.itemsList:
                Intent intent1 = new Intent(this, ExpandableListActivity.class);
                startActivity(intent1);
                return true;
            case R.id.suitcasesList:
                Intent intent2 = new Intent(this, UserLuggageActivity.class);
                startActivity(intent2);
                return true;
            case R.id.save:
                testFileSaving();
                return true;
            case R.id.home:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}