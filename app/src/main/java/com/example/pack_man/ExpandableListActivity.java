package com.example.pack_man;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.pack_man.BasicList;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ExpandableListActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    int tripLength;
    List<String> listDataHeader;
    HashMap<String, List<MyPair>> listDataChild;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        if(BasicList.wasSaved && SuitcaseList.wasSaved){
            TextView text1 = findViewById(R.id.itemsListUnsavedID);
            text1.setVisibility(View.INVISIBLE);
        }
        if(BasicList.wasChanged && SuitcaseList.wasChanged){
            TextView text1 = findViewById(R.id.itemsListUnsavedID);
            text1.setVisibility(View.VISIBLE);
        }


        tripLength = Integer.parseInt(getPreference("DLUGOSC_WYJAZDU"));
        listDataHeader = BasicList.getListDataHeader();



        String nowy_wyjazd = getPreference("NOWY_WYJAZD");
        if(nowy_wyjazd.equals("TAK") || BasicList.wasReseted) {
            listDataChild = BasicList.getListDataChild(tripLength);
            BasicList.wasReseted = false;
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
                            if(isNumeric(newCount) && newCount.length()<=3 && !newName.equals("")){
                                child.setCount(newCount);
                                count.setText(child.getCount());
                                child.setName(newName);
                                name.setText(child.getName());
                                popupWindow.dismiss();
                                BasicList.getGroupList(groupPosition).add(newElement);

                                TextView text1 = findViewById(R.id.itemsListUnsavedID);
                                text1.setVisibility(View.VISIBLE);
                                BasicList.wasSaved = false;
                                BasicList.wasChanged = true;
                                SuitcaseList.wasSaved = false;
                                SuitcaseList.wasChanged = true;

                                recreate();
                            }
                            else{
                                if(!isNumeric(newCount) || newCount.length()>3){
                                    inputline1.setError("You have entered\nwrong value!");
                                }
                                if(newName.equals("") || newName == null){
                                    inputline2.setError("You have entered\nwrong value!");
                                }
                            }
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
                            if(isNumeric(newCount) && newCount.length()<=3){
                                child.setCount(newCount);
                                count.setText(child.getCount());

                                TextView text1 = findViewById(R.id.itemsListUnsavedID);
                                text1.setVisibility(View.VISIBLE);
                                BasicList.wasSaved = false;
                                BasicList.wasChanged = true;
                                SuitcaseList.wasSaved = false;
                                SuitcaseList.wasChanged = true;

                                popupWindow.dismiss();
                            }
                            else{
                                inputline.setError("You have entered\nwrong value!");
                            }
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

    public void resetExpandableList(){
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_on_reset_click, null);
//        //int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        //int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int width = 600;
//        int height = 800;
//        boolean focusable = true;
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//        popupWindow.setElevation(50);
//        popupWindow.showAtLocation(findViewById(R.id.content).getRootView(), Gravity.CENTER, 0, 0);
//        Button popupButtonNo = popupView.findViewById(R.id.resetNoID);
//        popupButtonNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        Button popupButtonYes = popupView.findViewById(R.id.resetYesID);
//        popupButtonYes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BasicList.listAlreadySet = false;
//                BasicList.mapAlreadySet = false;
//                listDataHeader = BasicList.getListDataHeader();
//                listDataChild = BasicList.getListDataChild(tripLength);
//
//                expListView = (ExpandableListView) findViewById(R.id.lvExp);
//                listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
//                expListView.setAdapter(listAdapter);
//                BasicList.wasReseted = true;
//
//                TextView text1 = findViewById(R.id.itemsListUnsavedID);
//                text1.setVisibility(View.VISIBLE);
//                BasicList.wasSaved = false;
//                BasicList.wasChanged = true;
//                SuitcaseList.wasSaved = false;
//                SuitcaseList.wasChanged = true;
//                popupWindow.dismiss();
//                recreate();
//            }
//        });



        BasicList.listAlreadySet = false;
        BasicList.mapAlreadySet = false;
        listDataHeader = BasicList.getListDataHeader();
        listDataChild = BasicList.getListDataChild(tripLength);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        BasicList.wasReseted = true;

        TextView text1 = findViewById(R.id.itemsListUnsavedID);
        text1.setVisibility(View.VISIBLE);
        BasicList.wasSaved = false;
        BasicList.wasChanged = true;
        SuitcaseList.wasSaved = false;
        SuitcaseList.wasChanged = true;
        recreate();
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

    public void testFileSaving(){
        File itemsFile= null;
        File suitcaseFile= null;

        FileOutputStream fileOutputStream = null;
        try {
            itemsFile = getFilesDir();
            fileOutputStream = openFileOutput("UserList.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(TripDataParser.parseItemsDataToString().getBytes());

            suitcaseFile = getFilesDir();
            fileOutputStream = openFileOutput("SuitcaseList.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(TripDataParser.parseSuitcaseDataToString().getBytes());

            //Toast.makeText(this, "Your trip data has been saved!", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Your trip data has been saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.itemsList:
                return true;
            case R.id.suitcasesList:
                Intent intent1 = new Intent(this, UserLuggageActivity.class);
                startActivity(intent1);
                return true;
            case R.id.save:
                testFileSaving();
                BasicList.wasSaved = true;
                BasicList.wasChanged = false;
                SuitcaseList.wasSaved = true;
                SuitcaseList.wasChanged = false;
                TextView text1 = findViewById(R.id.itemsListUnsavedID);
                text1.setVisibility(View.INVISIBLE);
                return true;
            case R.id.reset:
                resetExpandableList();
                return true;
            case R.id.home:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}