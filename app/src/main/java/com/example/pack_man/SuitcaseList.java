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

    public static void updateSuitcaseData(ArrayList<String[]> specifications){
        suitCaseList.remove(suitCaseList.size()-1);

        for(String[] suitcaseSpecification: specifications){
            String suitcaseInList = suitcaseSpecification[0];
            String capacityInList = suitcaseSpecification[1];
            for(int i=0; i<suitCaseList.size(); i++){
                if(suitCaseList.get(i).getName().equals(suitcaseInList)){
                    suitCaseList.get(i).setCount(capacityInList);
                    break;
                }
                if( i == suitCaseList.size()-1){
                    suitCaseList.add(new MyPair(suitcaseInList, capacityInList));
                }
            }
        }
    }
}
