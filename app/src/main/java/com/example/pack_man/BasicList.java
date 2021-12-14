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
            listDataHeader.add("Ubrania");
            listDataHeader.add("Sprzęt elektroniczny");
            listDataHeader.add("Akcesoria");
        }
        return listDataHeader;
    }

    public static HashMap<String, List<MyPair>> getListDataChild(int tripLength){
        if (!mapAlreadySet) {
            mapAlreadySet = true;
            listDataChild = new HashMap<String, List<MyPair>>();
            List<MyPair> ubrania = new ArrayList<MyPair>();
            ubrania.add(new MyPair("Koszulki", "" + (tripLength)));
            ubrania.add(new MyPair("Spodnie", "" + (tripLength)));
            ubrania.add(new MyPair("Dodaj nowy element", "+"));

            List<MyPair> sprzet_elekt = new ArrayList<MyPair>();
            sprzet_elekt.add(new MyPair("Power Bank", "1"));
            sprzet_elekt.add(new MyPair("ładowarka do telefonu", "1"));
            sprzet_elekt.add(new MyPair("Dodaj nowy element", "+"));

            List<MyPair> akcesoria = new ArrayList<MyPair>();
            akcesoria.add(new MyPair("Szczoteczka do zębów", "1"));
            akcesoria.add(new MyPair("Suszarka do włosów", "1"));
            akcesoria.add(new MyPair("Dodaj nowy element", "+"));

            listDataChild.put(listDataHeader.get(0), ubrania); // Header, Child data
            listDataChild.put(listDataHeader.get(1), sprzet_elekt);
            listDataChild.put(listDataHeader.get(2), akcesoria);
        }
        return listDataChild;
    }

    public static List<MyPair> getGroupList(int position) {
        return listDataChild.get(listDataHeader.get(position));
    }
}
