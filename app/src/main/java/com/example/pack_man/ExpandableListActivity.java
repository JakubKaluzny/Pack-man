package com.example.pack_man;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.example.pack_man.BasicList;


import android.content.Context;

public class ExpandableListActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    //int tripLength = Integer.parseInt(getPreference("DLUGOSC_WYJAZDU"));
    List<String> listDataHeader = BasicList.getListDataHeader();
    HashMap<String, List<MyPair>> listDataChild = BasicList.getListDataChild(3);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        //prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild/*, "5"*/);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                TextView text = v.findViewById(R.id.ListItemCount);
                MyPair child = (MyPair)listAdapter.getChild(groupPosition,childPosition);
                child.setCount("" + 5);
                text.setText(child.getCount());
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
//    private void prepareListData() {
//        tripLength = Integer.parseInt(getPreference("DLUGOSC_WYJAZDU"));
//
//        listDataHeader = new ArrayList<String>();
//        //List<MyPair>>
//        listDataChild = new HashMap<String, List<MyPair>>();
//
//        // Adding child data
//        listDataHeader.add("Top 250");
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");
//
//        // Adding child data
//        //Pair<String, Integer> top250Pair = new MyPair("asd", 2);
//        List<MyPair> top250 = new ArrayList<MyPair>();
//        top250.add(new MyPair("Twoja Stara", "" + (tripLength + 2)));
//        top250.add(new MyPair("Chaniecki", "" + (tripLength * 3)));
//
//        List<MyPair> nowShowing = new ArrayList<MyPair>();
//        nowShowing.add(new MyPair("Duch", "" + (tripLength * 6)));
//        nowShowing.add(new MyPair("Despicable Me 2", "4"));
//
//        List<MyPair> comingSoon = new ArrayList<MyPair>();
//        comingSoon.add(new MyPair("2 Guns", "5"));
//        comingSoon.add(new MyPair("The Smurfs 2", "6"));
//
//
//        //HashMap<String, List<MyPair>> listDataChild = null;
//
//        //comingSoon.add(getPreference("DLUGOSC_WYJAZDU"));
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
//
//
//        //System.out.println("START");
//        //System.out.println(getPreference("DLUGOSC_WYJAZDU"));
//        //countView.setText(getPreference("DLUGOSC_WYJAZDU"));
//    }

    private String getPreference(String key) {
        SharedPreferences sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public void goToListTesting(View view){
        Intent intent = new Intent(this, ListTesting.class);
        startActivity(intent);
    }
}