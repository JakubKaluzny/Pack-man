package com.example.pack_man;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    //private HashMap<String, List<String>> _listDataChild;
    private HashMap<String, List<MyPair>> _listDataChild;
    //private String _getPreference;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<MyPair>> _listDataChild
                                 /*HashMap<String, List<String>> listChildData, String getPreference*/) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = _listDataChild;
        //this._getPreference = getPreference;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

//    public static final Set<Pair<Long, Long>> mCheckedItems = new HashSet<Pair<Long, Long>>();

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        MyPair child = (MyPair) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        txtListChild.setText(child.getName());

        TextView count = (TextView) convertView.findViewById(R.id.ListItemCount);
        count.setText(child.getCount());

        if(getChildrenCount(groupPosition)-1 == childPosition){
            txtListChild.setTextColor(Color.parseColor("#FF3333"));
            count.setTextColor(Color.parseColor("#FF3333"));
            count.setTextSize(20);
        }
        else{
            txtListChild.setTextColor(Color.parseColor("#808080"));
            count.setTextColor(Color.parseColor("#808080"));
            count.setTextSize(14);
        }

        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.your_checkbox_id);
        // add tag to remember groupId/childId
        final Pair<Long, Long> tag = new Pair<Long, Long>(
                getGroupId(groupPosition),
                getChildId(groupPosition, childPosition));
        cb.setTag(tag);
        // set checked if groupId/childId in checked items

        if(count.getText().toString().equals("0")){
            //cb.setVisibility(View.GONE);
            BasicList.mCheckedItems.remove(tag);
            cb.setChecked(false);
            cb.setEnabled(false);
            cb.setAlpha(0.4f);
        }
        else{
            cb.setEnabled(true);
            cb.setAlpha(1.0f);
            cb.setChecked(BasicList.mCheckedItems.contains(tag));
        }


        //cb.setChecked(mCheckedItems.contains(tag));
        // set OnClickListener to handle checked switches
        cb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final CheckBox cb = (CheckBox) v;
                final Pair<Long, Long> tag = (Pair<Long, Long>) v.getTag();
                if (cb.isChecked()) {
                    BasicList.mCheckedItems.add(tag);
                }
                else {
                    BasicList.mCheckedItems.remove(tag);
                }
                if(count.getText().toString().equals("0")){
                    BasicList.mCheckedItems.remove(tag);
                }
            }
        });

        if(childPosition == getChildrenCount(groupPosition)-1){
            cb.setVisibility(View.GONE);
        }
        else{
            cb.setVisibility(View.VISIBLE);
        }

        return convertView;
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
