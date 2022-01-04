package com.example.pack_man;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TripDataParser {
    private static HashMap<String, List<MyPair>> items = BasicList.getListDataChild(0);

    public static String parseItemsDataToString(){
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, List<MyPair>> entry : items.entrySet()) {
            String category = entry.getKey();
            for(MyPair pair: entry.getValue()){
//                if(pair.getName().equals("Dodaj nowy element")){
//                    continue;
//                }
                String line = category + "\t" + pair.getName() + "\t" + pair.getCount() + "\n";
                data.append(line);
            }
        }

        return data.toString();
    }


    public static ArrayList<String[]> parseItemsFromFile(String wholeFile){
        ArrayList<String[]> itemsSpecifications = new ArrayList<>();
        String[] lines = wholeFile.split("\n");
        for(String str: lines){
            itemsSpecifications.add(str.split("\t"));
        }
        return itemsSpecifications;
    }

}
