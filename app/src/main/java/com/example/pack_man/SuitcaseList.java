package com.example.pack_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SuitcaseList {
    static ArrayList<MyPair> suitCaseList;
    static boolean listAlreadySet = false;

    public static ArrayList<MyPair> getSuitCaseList(){
        if (!listAlreadySet) {
            listAlreadySet = true;
            suitCaseList = new ArrayList<MyPair>();

            suitCaseList.add(new MyPair("Luggage 1", "1L"));
            suitCaseList.add(new MyPair("Bag 1", "1L"));
            suitCaseList.add(new MyPair("Bag 2", "10L"));
            suitCaseList.add(new MyPair("Add new element", "+"));
        }
        return suitCaseList;
    }
}
