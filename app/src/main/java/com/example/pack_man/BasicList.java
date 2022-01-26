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
    static public boolean wasReseted = false;
    static public boolean wasSaved = false;
    static public boolean wasChanged = false;
    static public boolean wasUpdated = false;

    public static List<String> getListDataHeader(){
        if (!listAlreadySet) {
            listAlreadySet = true;
            listDataHeader = new ArrayList<String>();
            listDataHeader.add("Clothes");
            listDataHeader.add("Documents");
            listDataHeader.add("Accessories");
            listDataHeader.add("Electronic devices");
            listDataHeader.add("First aid kit");
            listDataHeader.add("Hygiene");
            listDataHeader.add("Other");
        }
        return listDataHeader;
    }

    public static HashMap<String, List<MyPair>> getListDataChild(int tripLength, String season){
        if (!mapAlreadySet) {
            mapAlreadySet = true;
            listDataChild = new HashMap<String, List<MyPair>>();
            List<MyPair> clothes = new ArrayList<MyPair>();
            List<MyPair> documents = new ArrayList<MyPair>();
            List<MyPair> accessories = new ArrayList<MyPair>();
            List<MyPair> electDevices = new ArrayList<MyPair>();
            List<MyPair> medKit = new ArrayList<MyPair>();
            List<MyPair> hygiene = new ArrayList<MyPair>();
            List<MyPair> other = new ArrayList<MyPair>();

            switch(season){

                case "Summer":
                    clothes.add(new MyPair("T-shirts", "" + (tripLength+1)));
                    clothes.add(new MyPair("Shirt", "" + (0)));
                    clothes.add(new MyPair("Wool sweater", "" + (0)));
                    clothes.add(new MyPair("Cotton sweater", "" + (0)));
                    clothes.add(new MyPair("Hoodie", "" + (tripLength > 10 ? 2 : 1)));
                    clothes.add(new MyPair("Trousers", "" + (int)(tripLength > 5 ? Math.ceil(tripLength/3.0)-1 : 1)));
                    clothes.add(new MyPair("Shorts", "" + (int)(tripLength > 3 ? Math.ceil(tripLength/3.0) : 1)));
                    clothes.add(new MyPair("Swimsuit", "" + (1)));
                    clothes.add(new MyPair("Winter jacket", "" + (0)));
                    clothes.add(new MyPair("Light jacket", "" + (0)));
                    clothes.add(new MyPair("Coat", "" + (0)));
                    clothes.add(new MyPair("Socks", "" + (tripLength)));
                    clothes.add(new MyPair("Underwear", "" + (tripLength)));
                    clothes.add(new MyPair("Boots", "" + (tripLength > 10 ? 2 : 1)));
                    clothes.add(new MyPair("Add new element", "+"));

                    documents.add(new MyPair("ID card", "" + (1)));
                    documents.add(new MyPair("Driving licence", "" + (1)));
                    documents.add(new MyPair("Passport", "" + (1)));
                    documents.add(new MyPair("Bank card", "" + (1)));
                    documents.add(new MyPair("Add new element", "+"));

                    accessories.add(new MyPair("Watch", "" + (0)));
                    accessories.add(new MyPair("Sunglasses", "" + (1)));
                    accessories.add(new MyPair("Scarf", "" + (0)));
                    accessories.add(new MyPair("Hat", "" + (0)));
                    accessories.add(new MyPair("Gloves", "" + (0)));
                    accessories.add(new MyPair("Belt", "" + (1)));
                    accessories.add(new MyPair("Hairbrush", "" + (1)));
                    accessories.add(new MyPair("Add new element", "+"));

                    electDevices.add(new MyPair("Earphones", "" + (1)));
                    electDevices.add(new MyPair("Phone charger", "" + (1)));
                    electDevices.add(new MyPair("Phone", "" + (1)));
                    electDevices.add(new MyPair("Camera", "" + (0)));
                    electDevices.add(new MyPair("Laptop", "" + (0)));
                    electDevices.add(new MyPair("Add new element", "+"));

                    medKit.add(new MyPair("Bandage", "" + (1)));
                    medKit.add(new MyPair("Protective mask", "" + (1)));
                    medKit.add(new MyPair("Painkillers", "" + (0)));
                    medKit.add(new MyPair("Disinfectant liquid", "" + (1)));
                    medKit.add(new MyPair("Add new element", "+"));

                    hygiene.add(new MyPair("Shampoo", "" + (1)));
                    hygiene.add(new MyPair("Soap", "" + (1)));
                    hygiene.add(new MyPair("Towel", "" + (1)));
                    hygiene.add(new MyPair("Toothbrush", "" + (1)));
                    hygiene.add(new MyPair("Toothpaste", "" + (1)));
                    hygiene.add(new MyPair("Add new element", "+"));

                    other.add(new MyPair("Tissues", "" + (2)));
                    other.add(new MyPair("Add new element", "+"));

                    break;

                case "Fall":
                    clothes.add(new MyPair("T-shirts", "" + (tripLength+1)));
                    clothes.add(new MyPair("Shirt", "" + (int)(Math.ceil(tripLength/4.0))));
                    clothes.add(new MyPair("Wool sweater", "" + (1)));
                    clothes.add(new MyPair("Cotton sweater", "" + (0)));
                    clothes.add(new MyPair("Hoodie", "" + (int)(Math.ceil(tripLength/4.0))));
                    clothes.add(new MyPair("Trousers", "" + (int)(tripLength > 3 ? Math.ceil(tripLength/3.0) : 1)));
                    clothes.add(new MyPair("Shorts", "" + (0)));
                    clothes.add(new MyPair("Swimsuit", "" + (0)));
                    clothes.add(new MyPair("Winter jacket", "" + (0)));
                    clothes.add(new MyPair("Light jacket", "" + (0)));
                    clothes.add(new MyPair("Coat", "" + (1)));
                    clothes.add(new MyPair("Socks", "" + (tripLength)));
                    clothes.add(new MyPair("Underwear", "" + (tripLength)));
                    clothes.add(new MyPair("Boots", "" + (tripLength > 10 ? 2 : 1)));
                    clothes.add(new MyPair("Add new element", "+"));

                    documents.add(new MyPair("ID card", "" + (1)));
                    documents.add(new MyPair("Driving licence", "" + (1)));
                    documents.add(new MyPair("Passport", "" + (1)));
                    documents.add(new MyPair("Bank card", "" + (1)));
                    documents.add(new MyPair("Add new element", "+"));

                    accessories.add(new MyPair("Watch", "" + (0)));
                    accessories.add(new MyPair("Sunglasses", "" + (0)));
                    accessories.add(new MyPair("Scarf", "" + (1)));
                    accessories.add(new MyPair("Hat", "" + (1)));
                    accessories.add(new MyPair("Gloves", "" + (0)));
                    accessories.add(new MyPair("Belt", "" + (1)));
                    accessories.add(new MyPair("Hairbrush", "" + (1)));
                    accessories.add(new MyPair("Add new element", "+"));

                    electDevices.add(new MyPair("Earphones", "" + (1)));
                    electDevices.add(new MyPair("Phone charger", "" + (1)));
                    electDevices.add(new MyPair("Phone", "" + (1)));
                    electDevices.add(new MyPair("Camera", "" + (0)));
                    electDevices.add(new MyPair("Laptop", "" + (0)));
                    electDevices.add(new MyPair("Add new element", "+"));

                    medKit.add(new MyPair("Bandage", "" + (1)));
                    medKit.add(new MyPair("Protective mask", "" + (1)));
                    medKit.add(new MyPair("Painkillers", "" + (0)));
                    medKit.add(new MyPair("Disinfectant liquid", "" + (1)));
                    medKit.add(new MyPair("Add new element", "+"));

                    hygiene.add(new MyPair("Shampoo", "" + (1)));
                    hygiene.add(new MyPair("Soap", "" + (1)));
                    hygiene.add(new MyPair("Towel", "" + (1)));
                    hygiene.add(new MyPair("Toothbrush", "" + (1)));
                    hygiene.add(new MyPair("Toothpaste", "" + (1)));
                    hygiene.add(new MyPair("Add new element", "+"));

                    other.add(new MyPair("Tissues", "" + (2)));
                    other.add(new MyPair("Add new element", "+"));

                    break;

                case "Winter":
                    clothes.add(new MyPair("T-shirts", "" + (tripLength+1)));
                    clothes.add(new MyPair("Shirt", "" + (0)));
                    clothes.add(new MyPair("Wool sweater", "" + (int)(Math.ceil(tripLength/3.0))));
                    clothes.add(new MyPair("Cotton sweater", "" + (0)));
                    clothes.add(new MyPair("Hoodie", "" + (int)(Math.ceil(tripLength/5.0))));
                    clothes.add(new MyPair("Trousers", "" + (int)(tripLength > 3 ? Math.ceil(tripLength/3.0) : 1)));
                    clothes.add(new MyPair("Shorts", "" + (0)));
                    clothes.add(new MyPair("Swimsuit", "" + (0)));
                    clothes.add(new MyPair("Winter jacket", "" + (1)));
                    clothes.add(new MyPair("Light jacket", "" + (0)));
                    clothes.add(new MyPair("Coat", "" + (0)));
                    clothes.add(new MyPair("Socks", "" + (tripLength)));
                    clothes.add(new MyPair("Underwear", "" + (tripLength)));
                    clothes.add(new MyPair("Boots", "" + (tripLength > 10 ? 2 : 1)));
                    clothes.add(new MyPair("Add new element", "+"));

                    documents.add(new MyPair("ID card", "" + (1)));
                    documents.add(new MyPair("Driving licence", "" + (1)));
                    documents.add(new MyPair("Passport", "" + (1)));
                    documents.add(new MyPair("Bank card", "" + (1)));
                    documents.add(new MyPair("Add new element", "+"));

                    accessories.add(new MyPair("Watch", "" + (0)));
                    accessories.add(new MyPair("Sunglasses", "" + (0)));
                    accessories.add(new MyPair("Scarf", "" + (1)));
                    accessories.add(new MyPair("Hat", "" + (1)));
                    accessories.add(new MyPair("Gloves", "" + (1)));
                    accessories.add(new MyPair("Belt", "" + (1)));
                    accessories.add(new MyPair("Hairbrush", "" + (1)));
                    accessories.add(new MyPair("Add new element", "+"));

                    electDevices.add(new MyPair("Earphones", "" + (1)));
                    electDevices.add(new MyPair("Phone charger", "" + (1)));
                    electDevices.add(new MyPair("Phone", "" + (1)));
                    electDevices.add(new MyPair("Camera", "" + (0)));
                    electDevices.add(new MyPair("Laptop", "" + (0)));
                    electDevices.add(new MyPair("Add new element", "+"));

                    medKit.add(new MyPair("Bandage", "" + (1)));
                    medKit.add(new MyPair("Protective mask", "" + (1)));
                    medKit.add(new MyPair("Painkillers", "" + (0)));
                    medKit.add(new MyPair("Disinfectant liquid", "" + (1)));
                    medKit.add(new MyPair("Add new element", "+"));

                    hygiene.add(new MyPair("Shampoo", "" + (1)));
                    hygiene.add(new MyPair("Soap", "" + (1)));
                    hygiene.add(new MyPair("Towel", "" + (1)));
                    hygiene.add(new MyPair("Toothbrush", "" + (1)));
                    hygiene.add(new MyPair("Toothpaste", "" + (1)));
                    hygiene.add(new MyPair("Add new element", "+"));

                    other.add(new MyPair("Tissues", "" + (2)));
                    other.add(new MyPair("Add new element", "+"));

                    break;

                default:
                    clothes.add(new MyPair("T-shirts", "" + (tripLength+1)));
                    clothes.add(new MyPair("Shirt", "" + (0)));
                    clothes.add(new MyPair("Wool sweater", "" + (0)));
                    clothes.add(new MyPair("Cotton sweater", "" + (0)));
                    clothes.add(new MyPair("Hoodie", "" + (tripLength > 10 ? 2 : 1)));
                    clothes.add(new MyPair("Trousers", "" + (int)(tripLength > 3 ? Math.ceil(tripLength/3.0) : 1)));
                    clothes.add(new MyPair("Shorts", "" + (int)(tripLength > 5 ? Math.ceil(tripLength/3.0)-1 : 1)));
                    clothes.add(new MyPair("Swimsuit", "" + (1)));
                    clothes.add(new MyPair("Winter jacket", "" + (0)));
                    clothes.add(new MyPair("Light jacket", "" + (1)));
                    clothes.add(new MyPair("Coat", "" + (0)));
                    clothes.add(new MyPair("Socks", "" + (tripLength)));
                    clothes.add(new MyPair("Underwear", "" + (tripLength)));
                    clothes.add(new MyPair("Boots", "" + (tripLength > 10 ? 2 : 1)));
                    clothes.add(new MyPair("Add new element", "+"));

                    documents.add(new MyPair("ID card", "" + (1)));
                    documents.add(new MyPair("Driving licence", "" + (1)));
                    documents.add(new MyPair("Passport", "" + (1)));
                    documents.add(new MyPair("Bank card", "" + (1)));
                    documents.add(new MyPair("Add new element", "+"));

                    accessories.add(new MyPair("Watch", "" + (0)));
                    accessories.add(new MyPair("Sunglasses", "" + (0)));
                    accessories.add(new MyPair("Scarf", "" + (0)));
                    accessories.add(new MyPair("Hat", "" + (1)));
                    accessories.add(new MyPair("Gloves", "" + (0)));
                    accessories.add(new MyPair("Belt", "" + (1)));
                    accessories.add(new MyPair("Hairbrush", "" + (1)));
                    accessories.add(new MyPair("Add new element", "+"));

                    electDevices.add(new MyPair("Earphones", "" + (1)));
                    electDevices.add(new MyPair("Phone charger", "" + (1)));
                    electDevices.add(new MyPair("Phone", "" + (1)));
                    electDevices.add(new MyPair("Camera", "" + (0)));
                    electDevices.add(new MyPair("Laptop", "" + (0)));
                    electDevices.add(new MyPair("Add new element", "+"));

                    medKit.add(new MyPair("Bandage", "" + (1)));
                    medKit.add(new MyPair("Protective mask", "" + (1)));
                    medKit.add(new MyPair("Painkillers", "" + (0)));
                    medKit.add(new MyPair("Disinfectant liquid", "" + (1)));
                    medKit.add(new MyPair("Add new element", "+"));

                    hygiene.add(new MyPair("Shampoo", "" + (1)));
                    hygiene.add(new MyPair("Soap", "" + (1)));
                    hygiene.add(new MyPair("Towel", "" + (1)));
                    hygiene.add(new MyPair("Toothbrush", "" + (1)));
                    hygiene.add(new MyPair("Toothpaste", "" + (1)));
                    hygiene.add(new MyPair("Add new element", "+"));

                    other.add(new MyPair("Tissues", "" + (2)));
                    other.add(new MyPair("Add new element", "+"));

                    break;
            }

//            ubrania.add(new MyPair("T-shirts", "" + (tripLength)));
//            ubrania.add(new MyPair("Trousers", "" + (tripLength)));
//            ubrania.add(new MyPair("Add new element", "+"));
//
//            List<MyPair> sprzet_elekt = new ArrayList<MyPair>();
//            sprzet_elekt.add(new MyPair("Power Bank", "1"));
//            sprzet_elekt.add(new MyPair("Phone charger", "1"));
//            sprzet_elekt.add(new MyPair("Add new element", "+"));
//
//            List<MyPair> akcesoria = new ArrayList<MyPair>();
//            akcesoria.add(new MyPair("Toothbrush", "1"));
//            akcesoria.add(new MyPair("Hair dryer", "1"));
//            akcesoria.add(new MyPair("Add new element", "+"));

            listDataChild.put(listDataHeader.get(0), clothes); // Header, Child data
            listDataChild.put(listDataHeader.get(1), documents);
            listDataChild.put(listDataHeader.get(2), accessories);
            listDataChild.put(listDataHeader.get(3), electDevices);
            listDataChild.put(listDataHeader.get(4), medKit);
            listDataChild.put(listDataHeader.get(5), hygiene);
            listDataChild.put(listDataHeader.get(6), other);
        }
        return listDataChild;
    }

    public static List<MyPair> getGroupList(int position) {
        return listDataChild.get(listDataHeader.get(position));
    }

    public static void updateListData(ArrayList<String[]> specifications){
        if(!wasUpdated){
            wasUpdated = true;
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
}
