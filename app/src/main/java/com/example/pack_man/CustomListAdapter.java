package com.example.pack_man;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<MyPair> {
    ArrayList <MyPair> arrayList;


    public CustomListAdapter(@NonNull Context context, ArrayList<MyPair> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.suitcase_list_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        //NumbersView currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same

        TextView name = currentItemView.findViewById(R.id.suitcaseNameID);
        name.setText(arrayList.get(position).getName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView capacity = currentItemView.findViewById(R.id.suitcaseCapacityID);
        capacity.setText(arrayList.get(position).getCount());

        if(position == arrayList.size()-1){
            name.setTextColor(Color.parseColor("#FF3333"));
            capacity.setTextColor(Color.parseColor("#FF3333"));
            name.setTypeface(Typeface.DEFAULT);
            capacity.setTypeface(Typeface.DEFAULT);
            capacity.setTextSize(24);
        }
        else{
            name.setTextColor(Color.parseColor("#660000"));
            capacity.setTextColor(Color.parseColor("#660000"));
            name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            capacity.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            capacity.setTextSize(14);
        }

        // then return the recyclable view
        return currentItemView;
    }
}