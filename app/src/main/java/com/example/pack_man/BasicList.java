package com.example.pack_man;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicList {
    static List<String> listDataHeader;
    static HashMap<String, List<MyPair>> listDataChild;
    static boolean listAlreadySet = false;
    static boolean mapAlreadySet = false;

    public static List<String> getListDataHeader(){
        if (!listAlreadySet) {
            listAlreadySet = true;
            listDataHeader = new ArrayList<String>();
            listDataHeader.add("Top 250");
            listDataHeader.add("Now Showing");
            listDataHeader.add("Coming Soon..");
        }
        return listDataHeader;
    }

    public static HashMap<String, List<MyPair>> getListDataChild(int tripLength){
        if (!mapAlreadySet) {
            mapAlreadySet = true;
            listDataChild = new HashMap<String, List<MyPair>>();
            List<MyPair> top250 = new ArrayList<MyPair>();
            top250.add(new MyPair("Twoja Stara", "" + (tripLength + 2)));
            top250.add(new MyPair("Chaniecki", "" + (tripLength * 3)));

            List<MyPair> nowShowing = new ArrayList<MyPair>();
            nowShowing.add(new MyPair("Duch", "" + (tripLength * 6)));
            nowShowing.add(new MyPair("Despicable Me 2", "4"));

            List<MyPair> comingSoon = new ArrayList<MyPair>();
            comingSoon.add(new MyPair("2 Guns", "5"));
            comingSoon.add(new MyPair("The Smurfs 2", "6"));

            listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
            listDataChild.put(listDataHeader.get(1), nowShowing);
            listDataChild.put(listDataHeader.get(2), comingSoon);
        }
        return listDataChild;
    }
}
