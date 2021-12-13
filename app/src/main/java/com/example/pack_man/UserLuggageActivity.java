package com.example.pack_man;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.List;

public class UserLuggageActivity extends AppCompatActivity {

    private ArrayList suitcaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_luggage);

        suitcaseList = SuitcaseList.getSuitCaseList();

        CustomListAdapter adapter = new CustomListAdapter(this, suitcaseList);
        ListView suitcaseListView = findViewById(R.id.suitcaseListID);
        suitcaseListView.setAdapter(adapter);


        suitcaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 == suitcaseList.size()) {

                    MyPair newElement = new MyPair("Dodaj nowy element", "+");
                    TextView count = view.findViewById(R.id.suitcaseCapacityID);
                    TextView name = view.findViewById(R.id.suitcaseNameID);
                    MyPair child = (MyPair) suitcaseList.get(position);

                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_on_last_list_click, null);
                    EditText newCount = popupView.findViewById(R.id.newCountID);
                    newCount.setText(count.getText().toString());
                    if (count.getText() == "+")
                        newCount.setText("");
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
                            child.setCount(newCount + "L");
                            count.setText(child.getCount());
                            child.setName(newName);
                            name.setText(child.getName());
                            popupWindow.dismiss();
                            suitcaseList.add(newElement);
                            recreate();
                        }
                    });
                }
            }
        });
    }
}