package com.example.pack_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PackingAlgorithm {
    private ArrayList<MyPair> suitcaseList;
    private ArrayList<MyPair> realSuitcaseList;

    private int tripLength;
    private List<String> categories;
    private HashMap<String, List<MyPair>> items;

    public static Map<String, Double> itemsSizes;

    public PackingAlgorithm(){
        realSuitcaseList = new ArrayList<>();
        itemsSizes = new LinkedHashMap<String, Double>();
        itemsSizes.put("Koszulki", 1D);
        itemsSizes.put("Spodnie", 2D);
        itemsSizes.put("Power Bank", 0.5);
        itemsSizes.put("ładowarka do telefonu", 0.2);
        itemsSizes.put("Szczoteczka do zębów", 0.1);
        itemsSizes.put("Suszarka do włosów", 1D);
    }

    public Double calculateNeedeedSpace(){
        categories = BasicList.getListDataHeader();
        items = BasicList.getListDataChild(tripLength);

        suitcaseList = SuitcaseList.getSuitCaseList();

        Double userItemsSpace = 0D;

        for (Map.Entry<String, List<MyPair>> entry1 : items.entrySet()){
            for(MyPair pair: entry1.getValue()){
                if(pair.getName().equals("Dodaj nowy element")){
                    continue;
                }
                String itemName = pair.getName();
                String itemAmountInString = pair.getCount();
                Integer itemAmount = Integer.parseInt(itemAmountInString);
                for(Map.Entry<String, Double> entry2 : itemsSizes.entrySet()){
                    if(itemName.equals(entry2.getKey())){
                        userItemsSpace = userItemsSpace + itemAmount * entry2.getValue();
                    }
                }
            }
        }

        for(MyPair pair: suitcaseList) {
            if (pair.getName().equals("Dodaj nowy element")) {
                continue;
            }
            realSuitcaseList.add(new MyPair(pair.getName(), "" + pair.getCount()));
        }

        int listSize = realSuitcaseList.size();
        for(int i=1; i<=listSize; i++){
            int[] arr = { 1,2,3,4,5 };
        }
//        for(MyPair pair: realSuitcaseList){
//
//        }

        return userItemsSpace;
    }

    private String combinations(int[] arr, int len, int startPosition, int[] result){
        if (len == 0){
            String str = "";
            for(int suitcaseIndex : result)
            {
                str += suitcaseIndex + " ";
            }
            return str;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
            result[result.length - len] = arr[i];
            combinations(arr, len-1, i+1, result);
        }
        return null;
    }
}
