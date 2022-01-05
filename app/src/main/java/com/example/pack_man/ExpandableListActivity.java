package com.example.pack_man;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.pack_man.BasicList;


import android.content.Context;

public class ExpandableListActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    int tripLength;
    List<String> listDataHeader;
    HashMap<String, List<MyPair>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        tripLength = Integer.parseInt(getPreference("DLUGOSC_WYJAZDU"));
        listDataHeader = BasicList.getListDataHeader();



        String nowy_wyjazd = getPreference("NOWY_WYJAZD");
        if(nowy_wyjazd.equals("TAK")) {
            listDataChild = BasicList.getListDataChild(tripLength);
        }
        else if(nowy_wyjazd.equals("NIE")){
            listDataChild = BasicList.getListDataChild(0);
            BasicList.updateListData(TripDataParser.parseItemsFromFile(loadListDataFromFile()));
        }


        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild/*, "5"*/);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                TextView count = v.findViewById(R.id.ListItemCount);
                TextView name = v.findViewById(R.id.lblListItem);
                MyPair child = (MyPair) listAdapter.getChild(groupPosition, childPosition);

                if (listAdapter.getChildId(groupPosition, childPosition) + 1 == listAdapter.getChildrenCount(groupPosition)) {
                //if (childPosition + 1 == listAdapter.getChildrenCount(groupPosition)) {
                    MyPair newElement = new MyPair("Add new element", "+");

                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_on_last_list_click, null);
                    EditText newCount = popupView.findViewById(R.id.newCountID);
                    newCount.setText("");
                    EditText newName = popupView.findViewById(R.id.newNameID);
                    newName.setText("");
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    //int width = 600;
                    //int height = 800;
                    boolean focusable = true;
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    popupWindow.setElevation(50);
                    popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                    Button popupButton = popupView.findViewById(R.id.applyChangesID);
                    popupButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText inputline1 = popupView.findViewById(R.id.newCountID);
                            String newCount = inputline1.getText().toString();
                            EditText inputline2 = popupView.findViewById(R.id.newNameID);
                            String newName = inputline2.getText().toString();
                            child.setCount(newCount);
                            count.setText(child.getCount());
                            child.setName(newName);
                            name.setText(child.getName());
                            popupWindow.dismiss();
                            BasicList.getGroupList(groupPosition).add(newElement);
                            recreate();
                        }
                    });
//                    BasicList.getGroupList(groupPosition).add(newElement);
//                    recreate();
                }
                else {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_on_list_click, null);
                    EditText newCount = popupView.findViewById(R.id.newCountID);
                    newCount.setText(count.getText().toString());
                    //int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    //int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int width = 600;
                    int height = 800;
                    boolean focusable = true;
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    popupWindow.setElevation(50);
                    popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                    Button popupButton = popupView.findViewById(R.id.applyChangesID);
                    popupButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText inputline = popupView.findViewById(R.id.newCountID);
                            String newCount = inputline.getText().toString();
                            child.setCount(newCount);
                            count.setText(child.getCount());
                            popupWindow.dismiss();
                        }
                    });
                }
                return false;
            }
        });
    }

    private String getPreference(String key) {
        SharedPreferences sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public void resetExpandableList(View view){
        BasicList.listAlreadySet = false;
        BasicList.mapAlreadySet = false;
        recreate();
//        listDataHeader = BasicList.getListDataHeader();
//        listDataChild = BasicList.getListDataChild(tripLength);
//
//        expListView = (ExpandableListView) findViewById(R.id.lvExp);
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//        expListView.setAdapter(listAdapter);
    }

    public void goToUserLuggage(View view){
        Intent intent = new Intent(this, UserLuggageActivity.class);
        startActivity(intent);
    }

    public String loadListDataFromFile()
    {
        StringBuffer buffer = null;
        try {
            FileInputStream fileInputStream =  openFileInput("UserList.txt");
            int read = -1;
            buffer = new StringBuffer();
            while((read =fileInputStream.read())!= -1){
                buffer.append((char)read);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}