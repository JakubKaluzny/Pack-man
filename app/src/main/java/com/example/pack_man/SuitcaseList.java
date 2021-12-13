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

            suitCaseList.add(new MyPair("Walizka 1", "40L"));
            suitCaseList.add(new MyPair("Walizka 2", "50L"));
            suitCaseList.add(new MyPair("Walizka 3", "30L"));
            suitCaseList.add(new MyPair("Dodaj nowy element", "+"));
        }
        return suitCaseList;
    }
}
