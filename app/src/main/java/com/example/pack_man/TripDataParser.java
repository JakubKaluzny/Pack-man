package com.example.pack_man;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TripDataParser {
//    private static HashMap<String, List<MyPair>> items = BasicList.getListDataChild(0);
//    private static ArrayList<MyPair> suitcases = SuitcaseList.getSuitCaseList();

    public static String parseItemsDataToString(){
        HashMap<String, List<MyPair>> items = BasicList.getListDataChild(0, "");
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

    public static String parseCheckBoxesToString(){
        StringBuilder data = new StringBuilder();
        for(Pair pair: BasicList.mCheckedItems){
            String group = "" + (Long)pair.first;
            String position = "" + (Long)pair.second;
            data.append(group).append("\t").append(position).append("\n");
        }
        return data.toString();
    }

    public static String parseSuitcaseDataToString(){
        ArrayList<MyPair> suitcases = SuitcaseList.getSuitCaseList();
        StringBuilder data = new StringBuilder();
        for(MyPair pair: suitcases){
            String suitcase = pair.getName();
            String capacity = pair.getCount();
            String line = suitcase + "\t" + capacity + "\n";
            data.append(line);
        }
        return data.toString();
    }

    public static ArrayList<String[]> parseCheckboxesFromFile(String wholeFile){
        ArrayList<String[]> checkboxes = new ArrayList<>();
        String[] lines = wholeFile.split("\n");
        for(String str: lines){
            checkboxes.add(str.split("\t"));
        }
        return checkboxes;
    }

    public static ArrayList<String[]> parseItemsFromFile(String wholeFile){
        ArrayList<String[]> itemsSpecifications = new ArrayList<>();
        String[] lines = wholeFile.split("\n");
        for(String str: lines){
            itemsSpecifications.add(str.split("\t"));
        }
        return itemsSpecifications;
    }

    public static ArrayList<String[]> parseSuitcasesFromFile(String wholeFile){
        ArrayList<String[]> suitcaseSpecifications = new ArrayList<>();
        if(wholeFile.equals("")){
            return null;
        }
        String[] lines = wholeFile.split("\n");
        for(String str: lines){
            suitcaseSpecifications.add(str.split("\t"));
        }
        return suitcaseSpecifications;
    }

}
