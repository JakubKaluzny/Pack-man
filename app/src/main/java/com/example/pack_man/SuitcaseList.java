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

            suitCaseList.add(new MyPair("Walizka 1", "1L"));
            suitCaseList.add(new MyPair("Plecak 1", "1L"));
            suitCaseList.add(new MyPair("Plecak 2", "10L"));
            suitCaseList.add(new MyPair("Dodaj nowy element", "+"));
        }
        return suitCaseList;
    }
}
