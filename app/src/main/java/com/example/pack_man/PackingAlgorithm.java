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
    private String season;
    private List<String> categories;
    private HashMap<String, List<MyPair>> items;

    public static Map<String, Double> itemsSizes;

    public PackingAlgorithm(){
        realSuitcaseList = new ArrayList<>();
        suitcaseIndexes = new ArrayList<>();
        allIndexesCombinations = new ArrayList<>();
        suitcasesCapacity = new ArrayList<>();
        itemsSizes = new LinkedHashMap<String, Double>();
        itemsSizes.put("T-shirts", 0.8D);
        itemsSizes.put("Shirt", 1.3D);
        itemsSizes.put("Wool sweater", 6D);
        itemsSizes.put("Cotton sweater", 5D);
        itemsSizes.put("Hoodie", 5D);
        itemsSizes.put("Trousers", 2.5D);
        itemsSizes.put("Shorts", 1.5D);
        itemsSizes.put("Swimsuit", 0.8D);
        itemsSizes.put("Winter jacket", 14D);
        itemsSizes.put("Light jacket", 9D);
        itemsSizes.put("Coat", 12D);
        itemsSizes.put("Socks", 0.2D);
        itemsSizes.put("Underwear", 0.2D);
        itemsSizes.put("Boots", 3D);

        itemsSizes.put("ID card", 0.015D);
        itemsSizes.put("Driving licence", 0.015D);
        itemsSizes.put("Passport", 0.015D);
        itemsSizes.put("Bank card", 0.015D);

        itemsSizes.put("Watch", 0.1D);
        itemsSizes.put("Sunglasses", 0.2D);
        itemsSizes.put("Scarf", 1D);
        itemsSizes.put("Hat", 0.5D);
        itemsSizes.put("Gloves", 0.2D);
        itemsSizes.put("Belt", 0.2D);
        itemsSizes.put("Hairbrush", 0.15D);

        itemsSizes.put("Earphones", 0.05D);
        itemsSizes.put("Phone charger", 0.1D);
        itemsSizes.put("Phone", 0.3D);
        itemsSizes.put("Camera", 0.8D);
        itemsSizes.put("Laptop", 3.5D);

        itemsSizes.put("Bandage", 0.1D);
        itemsSizes.put("Protective mask", 0.05D);
        itemsSizes.put("Painkillers", 0.1D);
        itemsSizes.put("Disinfectant liquid", 0.05D);

        itemsSizes.put("Shampoo", 0.2D);
        itemsSizes.put("Soap", 0.2D);
        itemsSizes.put("Towel", 4D);
        itemsSizes.put("Toothbrush", 0.05D);
        itemsSizes.put("Toothpaste", 0.07D);

        itemsSizes.put("Tissues", 0.1D);
    }

    public String calculateNeedeedSpace(){
        categories = BasicList.getListDataHeader();
        items = BasicList.getListDataChild(tripLength, season);

        suitcaseList = SuitcaseList.getSuitCaseList();

        Double userItemsSpace = 0D;

        for (Map.Entry<String, List<MyPair>> entry1 : items.entrySet()){
            for(MyPair pair: entry1.getValue()){
                if(pair.getName().equals("Add new element")){
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
            if (pair.getName().equals("Add new element")) {
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
            return "Not enough space";
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
                if(i == valueSplited.length - 1){
                    bestSuitcases = bestSuitcases + suitcase.getName();
                }
                else{
                    bestSuitcases = bestSuitcases + suitcase.getName() + ", ";
                }
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
