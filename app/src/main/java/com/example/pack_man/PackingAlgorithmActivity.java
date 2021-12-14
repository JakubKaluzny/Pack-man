package com.example.pack_man;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

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

//        tripLength = Integer.parseInt(getPreference("DLUGOSC_WYJAZDU"));
//        listDataHeader = BasicList.getListDataHeader();
//        listDataChild = BasicList.getListDataChild(tripLength);
//
//        suitcaseList = SuitcaseList.getSuitCaseList();

//        String wholeList = "";

//        for (Map.Entry<String, List<MyPair>> entry : listDataChild.entrySet()){
//            for(MyPair pair: entry.getValue()){
//                if(pair.getName().equals("Dodaj nowy element")){
//                    continue;
//                }
//                wholeList = wholeList + entry.getKey() + " " + pair.getName() + " " + pair.getCount() + "\n";
//            }
//        }

//        for(MyPair pair: suitcaseList) {
//            if (pair.getName().equals("Dodaj nowy element")) {
//                continue;
//            }
//            wholeList = wholeList + pair.getName() + " " + pair.getCount() + "\n";
//        }

        PackingAlgorithm packingAlgorithm = new PackingAlgorithm();
        Double userItemsSpace = packingAlgorithm.calculateNeedeedSpace();

        TextView text = findViewById(R.id.textListTestID);
        text.setText(userItemsSpace.toString());
    }

    private String getPreference(String key) {
        SharedPreferences sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }
}