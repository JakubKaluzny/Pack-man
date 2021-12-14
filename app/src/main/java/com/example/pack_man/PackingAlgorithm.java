package com.example.pack_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PackingAlgorithm {
    private ArrayList<MyPair> suitcaseList;
    private ArrayList<MyPair> realSuitcaseList;
    private List<Integer> suitcaseIndexes;
    private List<String> allIndexesCombinations;
    private List<Double> suitcasesCapacity;

    private int tripLength;
    private List<String> categories;
    private HashMap<String, List<MyPair>> items;

    public static Map<String, Double> itemsSizes;

    public PackingAlgorithm(){
        realSuitcaseList = new ArrayList<>();
        suitcaseIndexes = new ArrayList<>();
        allIndexesCombinations = new ArrayList<>();
        suitcasesCapacity = new ArrayList<>();
        itemsSizes = new LinkedHashMap<String, Double>();
        itemsSizes.put("Koszulki", 1D);
        itemsSizes.put("Spodnie", 2D);
        itemsSizes.put("Power Bank", 0.5);
        itemsSizes.put("ładowarka do telefonu", 0.2);
        itemsSizes.put("Szczoteczka do zębów", 0.1);
        itemsSizes.put("Suszarka do włosów", 1D);
    }

    public String calculateNeedeedSpace(){
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
            Double suitcaseSize = Double.parseDouble(removeLastChar(pair.getCount()));
            suitcaseSize = suitcaseSize * 1.1;
            realSuitcaseList.add(new MyPair(pair.getName(), suitcaseSize.toString()));
        }

        for(int i=0;i<realSuitcaseList.size();i++){
            suitcaseIndexes.add(i);
        }

        for(int i=0 ; i<=suitcaseIndexes.size(); i++){
            combinations(i,0, new int[i]);
        }

        for(String value: allIndexesCombinations){
            value = value.trim();
            Double res = 0D;
            if(value.length()==1){
                int index = Integer.parseInt(value);
                MyPair suitcase = realSuitcaseList.get(index);
                String sizeInString = suitcase.getCount();
                Double size = Double.parseDouble(sizeInString);
                res += size;
            }
            else{
                String[] valueSplited = value.split("");
                for(int i=0 ;i<valueSplited.length;i++){
                    int index = Integer.parseInt(valueSplited[i]);
                    MyPair suitcase = realSuitcaseList.get(index);
                    String sizeInString = suitcase.getCount();
                    Double size = Double.parseDouble(sizeInString);
                    res += size;
                }
            }
            suitcasesCapacity.add(res);
        }

        Double distance = 5000D;
        Double bestDistance = 5000D;
        int indexOfBestDistance = 0;
        for(int i=0; i<suitcasesCapacity.size();i++){
            distance = suitcasesCapacity.get(i) - userItemsSpace;
            if(distance < 0){
                distance = 5000D;
                continue;
            }
            if(distance < bestDistance){
                bestDistance = distance;
                indexOfBestDistance = i;
            }
        }
        if(bestDistance == 5000D){
            return "Kup se torbe gosciu";
        }
        String bestIndexes = allIndexesCombinations.get(indexOfBestDistance);
        String bestSuitcases = "";
        if(bestIndexes.length()==1){
            int index = Integer.parseInt(bestIndexes);
            MyPair suitcase = suitcaseList.get(index);
            bestSuitcases = suitcase.getName();
        }
        else{
            String[] valueSplited = bestIndexes.split("");
            for(int i=0 ;i<valueSplited.length;i++){
                int index = Integer.parseInt(valueSplited[i]);
                MyPair suitcase = realSuitcaseList.get(index);
                bestSuitcases = bestSuitcases + suitcase.getName() + " ";
            }
        }

        return bestSuitcases;
    }

    void combinations(int len, int startPosition, int[] result){
        if (len == 0){
            String str = "";
            for(int card : result)
            {
                str += card + "";
            }
            if(!str.equals("")){
                allIndexesCombinations.add(str);
            }
            return;
        }
        for (int i = startPosition; i <= suitcaseIndexes.size()-len; i++){
            result[result.length - len] = suitcaseIndexes.get(i);
            combinations(len-1, i+1, result);
        }
    }

    private String removeLastChar(String s)
    {
        return s.substring(0, s.length() - 1);
    }
}
