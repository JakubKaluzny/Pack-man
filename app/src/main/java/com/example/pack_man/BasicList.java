package com.example.pack_man;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicList {
    static List<String> listDataHeader;
    static HashMap<String, List<MyPair>> listDataChild;
    static boolean listAlreadySet = false;
    static boolean mapAlreadySet = false;

    public static List<String> getListDataHeader(){
        if (!listAlreadySet) {
            listAlreadySet = true;
            listDataHeader = new ArrayList<String>();
            listDataHeader.add("Clothes");
            listDataHeader.add("Electronic devices");
            listDataHeader.add("Accesories");
        }
        return listDataHeader;
    }

    public static HashMap<String, List<MyPair>> getListDataChild(int tripLength){
        if (!mapAlreadySet) {
            mapAlreadySet = true;
            listDataChild = new HashMap<String, List<MyPair>>();
            List<MyPair> ubrania = new ArrayList<MyPair>();
            ubrania.add(new MyPair("T-shirts", "" + (tripLength)));
            ubrania.add(new MyPair("Trousers", "" + (tripLength)));
            ubrania.add(new MyPair("Add new element", "+"));

            List<MyPair> sprzet_elekt = new ArrayList<MyPair>();
            sprzet_elekt.add(new MyPair("Power Bank", "1"));
            sprzet_elekt.add(new MyPair("Phone charger", "1"));
            sprzet_elekt.add(new MyPair("Add new element", "+"));

            List<MyPair> akcesoria = new ArrayList<MyPair>();
            akcesoria.add(new MyPair("Toothbrush", "1"));
            akcesoria.add(new MyPair("Hair dryer", "1"));
            akcesoria.add(new MyPair("Add new element", "+"));

            listDataChild.put(listDataHeader.get(0), ubrania); // Header, Child data
            listDataChild.put(listDataHeader.get(1), sprzet_elekt);
            listDataChild.put(listDataHeader.get(2), akcesoria);
        }
        return listDataChild;
    }

    public static List<MyPair> getGroupList(int position) {
        return listDataChild.get(listDataHeader.get(position));
    }

    public static void updateListData(ArrayList<String[]> specifications){
        for (Map.Entry<String, List<MyPair>> entry : listDataChild.entrySet()) {
            entry.getValue().remove(entry.getValue().size()-1);
        }
        for(String[] itemsSpecifications: specifications){
            String categoryInList = itemsSpecifications[0];
            String itemInList = itemsSpecifications[1];
            String countInList = itemsSpecifications[2];
            System.out.println("\n\n\n\n\n\n" + categoryInList + "\n\n\n\n\n\n\n\n");
            List<MyPair> items = listDataChild.get(categoryInList);
            for(int i=0; i<items.size(); i++){
                if(items.get(i).getName().equals(itemInList)){
                    items.get(i).setCount(countInList);
                    break;
                }
                if(i == items.size()-1){
                    items.add(new MyPair(itemInList, countInList));
                }
            }

        }
    }
}
