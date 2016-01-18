package com.example.glondhe.todo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glondhe on 9/25/15.
 */
public class CustomAdapter extends ArrayAdapter {

    private static final String TAG = "Name";
    Context context;
    ArrayList<String> items;
    ArrayList<String> items2;
    MyDBHandler dbHandler;
    List list = new ArrayList<>();
    String color;

    public CustomAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        dbHandler = new MyDBHandler(context, null, null, 1);
    }

    static class LayoutHandler {
        TextView itemname, date;
    }

    @Override
    public void remove(Object Object) {
        super.remove(Object);
        list.remove(Object);
    }

    @Override
    public void clear() {
        super.clear();
        list.clear();
    }

    @Override
    public void add(Object Object) {
        super.add(Object);
        list.add(Object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null || row.getTag() == null) {
            Log.v(TAG, "getView");
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.custom_row, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.itemname = (TextView) row.findViewById(R.id.textViewID);
            layoutHandler.date = (TextView) row.findViewById(R.id.textView2ID);
            row.setTag(layoutHandler);
        } else {
            layoutHandler = (LayoutHandler) row.getTag();
            Items items = (Items) this.getItem(position);
            Log.v("In tag for itemname: ", items.get_itemname());
        }
        Log.v("position", String.valueOf(position));
        Log.v("this.getItem(position)", String.valueOf(this.getItem(position)));

        Items items = (Items) this.getItem(position);
        layoutHandler.itemname.setText(items.get_itemname());
        layoutHandler.date.setText(items.get_type() + items.get_date());
        row.setBackgroundColor(Color.parseColor(items.get_color()));

        return row;
    }
}
