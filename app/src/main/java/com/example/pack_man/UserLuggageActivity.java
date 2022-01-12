package com.example.pack_man;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserLuggageActivity extends AppCompatActivity {

    private ArrayList suitcaseList;
    CustomListAdapter adapter;
    ListView suitcaseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_luggage);

        if(BasicList.wasSaved && SuitcaseList.wasSaved){
            TextView text1 = findViewById(R.id.suitcasesListUnsavedID);
            text1.setVisibility(View.INVISIBLE);
        }
        if(BasicList.wasChanged && SuitcaseList.wasChanged){
            TextView text1 = findViewById(R.id.suitcasesListUnsavedID);
            text1.setVisibility(View.VISIBLE);
        }

//        String nowy_wyjazd = getPreference("NOWY_WYJAZD");
//        if(nowy_wyjazd.equals("TAK") || SuitcaseList.wasReseted) {
//            suitcaseList = SuitcaseList.getSuitCaseList();
//            SuitcaseList.wasReseted = false;
//        }
//        else if(nowy_wyjazd.equals("NIE")){
//            suitcaseList = SuitcaseList.getSuitCaseList();
//            SuitcaseList.updateSuitcaseData(TripDataParser.parseSuitcasesFromFile(loadSuitcaseDataFromFile()));
//        }

        suitcaseList = SuitcaseList.getSuitCaseList();
        SuitcaseList.updateSuitcaseData(TripDataParser.parseSuitcasesFromFile(loadSuitcaseDataFromFile()));

        //suitcaseList = SuitcaseList.getSuitCaseList();

        /*CustomListAdapter*/ adapter = new CustomListAdapter(this, suitcaseList);
        /*ListView*/ suitcaseListView = findViewById(R.id.suitcaseListID);
        suitcaseListView.setAdapter(adapter);


        suitcaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 == suitcaseList.size()) {

                    MyPair newElement = new MyPair("Add new element", "+");
                    TextView count = view.findViewById(R.id.suitcaseCapacityID);
                    TextView name = view.findViewById(R.id.suitcaseNameID);
                    MyPair child = (MyPair) suitcaseList.get(position);

                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_on_last_suitcase_click, null);
                    EditText newCount = popupView.findViewById(R.id.newCountID);
                    newCount.setText(count.getText().toString());
                    //if (count.getText() == "+")
                    newCount.setText("");
                    //int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    //int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int width = 700;
                    int height = 1100;
                    boolean focusable = true;
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    popupWindow.setElevation(50);
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    Button popupButton = popupView.findViewById(R.id.applyChangesID);
                    popupButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText inputline1 = popupView.findViewById(R.id.newCountID);
                            String newCount = inputline1.getText().toString();
                            EditText inputline2 = popupView.findViewById(R.id.newNameID);
                            String newName = inputline2.getText().toString();
                            if(isNumeric(newCount) && newCount.length()<=3 && !newName.equals("")){
                                child.setCount(newCount + "L");
                                count.setText(child.getCount());
                                child.setName(newName);
                                name.setText(child.getName());
                                popupWindow.dismiss();
                                suitcaseList.add(newElement);

                                TextView text1 = findViewById(R.id.suitcasesListUnsavedID);
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
                }
            }
        });
    }



    public void resetSuitcaseList(){
//        SuitcaseList.listAlreadySet = false;
//        suitcaseList = SuitcaseList.getSuitCaseList();
//
//        /*CustomListAdapter*/ adapter = new CustomListAdapter(this, suitcaseList);
//        /*ListView*/ suitcaseListView = findViewById(R.id.suitcaseListID);
//        suitcaseListView.setAdapter(adapter);
//        SuitcaseList.wasReseted = true;
//        for(int i=0; i<SuitcaseList.suitCaseList.size(); i++){
//            SuitcaseList.suitCaseList.remove(i);
//        }
        SuitcaseList.getSuitCaseList().removeAll(SuitcaseList.suitCaseList);
        SuitcaseList.suitCaseList.add(new MyPair("Add new element", "+"));

        TextView text1 = findViewById(R.id.suitcasesListUnsavedID);
        text1.setVisibility(View.VISIBLE);
        BasicList.wasSaved = false;
        BasicList.wasChanged = true;
        SuitcaseList.wasSaved = false;
        SuitcaseList.wasChanged = true;

        recreate();
    }

    public void testFileSaving(){
        File itemsFile= null;
        File suitcaseFile= null;

        FileOutputStream fileOutputStream1 = null;
        FileOutputStream fileOutputStream2 = null;
        try {
            itemsFile = getFilesDir();
            fileOutputStream1 = openFileOutput("UserList.txt", Context.MODE_PRIVATE);
            fileOutputStream1.write(TripDataParser.parseItemsDataToString().getBytes());

            suitcaseFile = getFilesDir();
            fileOutputStream2 = openFileOutput("SuitcaseList.txt", Context.MODE_PRIVATE);
            fileOutputStream2.write(TripDataParser.parseSuitcaseDataToString().getBytes());

            Toast.makeText(this, "Your trip data has been saved!", Toast.LENGTH_SHORT).show();

            fileOutputStream1.close();
            fileOutputStream2.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream1.close();
                fileOutputStream2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadSuitcaseDataFromFile()
    {
        StringBuffer buffer = null;
        try {
            FileInputStream fileInputStream =  openFileInput("SuitcaseList.txt");
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

    private String getPreference(String key) {
        SharedPreferences sharedPref = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public void goToPackingAlgorithm(View view){
        Intent intent = new Intent(this, PackingAlgorithmActivity.class);
        startActivity(intent);
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
                Intent intent1 = new Intent(this, ExpandableListActivity.class);
                startActivity(intent1);
                return true;
            case R.id.suitcasesList:
                return true;
            case R.id.save:
                testFileSaving();
                BasicList.wasSaved = true;
                BasicList.wasChanged = false;
                SuitcaseList.wasSaved = true;
                SuitcaseList.wasChanged = false;
                TextView text1 = findViewById(R.id.suitcasesListUnsavedID);
                text1.setVisibility(View.INVISIBLE);
                return true;
            case R.id.reset:
                resetSuitcaseList();
                return true;
            case R.id.home:
                BasicList.mapAlreadySet = false;
                SuitcaseList.listAlreadySet = false;
                BasicList.wasUpdated = false;
                SuitcaseList.wasUpdated = false;
                BasicList.wasChanged = false;
                SuitcaseList.wasChanged = false;
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