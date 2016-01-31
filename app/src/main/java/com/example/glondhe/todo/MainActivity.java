package com.example.glondhe.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POSITION";
    ArrayList<String> items;
    ArrayList<String> items2;
    customAdapter itemsAdapter;
    ListDataAdapter listDataAapter;
    ListView lvItems;
    MyDBHandler dbHandler;
    static String old_itemname = null;
    static String old_date = null;
    static String old_type = null;
    static String old_color = null;
    static String setColor = "yellow";
    static String type = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("Table created", "");
        setContentView(R.layout.activity_main);
        dbHandler = new MyDBHandler(this, null, null, 1);
        //dbHandler.deleteAll();
        lvItems = (ListView) findViewById(R.id.IvItems);
        itemsAdapter = new customAdapter(getApplicationContext(), R.layout.custom_row);
        lvItems.setAdapter(itemsAdapter);
        populateListView();
        setupListViewListner();
        setupEditListViewListner();
    }

    public void setupListViewListner() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item Deleted!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 20) {

            String calStatus = null;

            String editText = data.getExtras().getString("editKeyText");
            int editTextPostion = data.getExtras().getInt("editKeyPosition");
            String date = data.getExtras().getString("date");
            String type = data.getExtras().getString("type");
            setColor = data.getExtras().getString("color");
            itemsAdapter.color = setColor;

            if (editTextPostion == -100) {
                Items items_db = new Items(data.getExtras().getString("editKeyText").toString(), date, type, setColor);

                try {
                    dbHandler.addItem(items_db);
                    Log.d("New row is added ", items_db.get_itemname());
                    Items it = new Items(editText, date, type, setColor);
                    Log.v("it1.get_itemname()", it.get_itemname());
                    itemsAdapter.add(it);
                    populateListView();
                    Toast.makeText(MainActivity.this, "Item added!!", Toast.LENGTH_LONG).show();
                } catch (SQLiteConstraintException e) {
                    Log.d("Contraint voilation: ", items_db.get_itemname());
                    Toast.makeText(MainActivity.this, "NOT ADDED." + items_db.get_itemname() + " - Item already exists!!", Toast.LENGTH_LONG).show();
                }
            } else {
                Items items_db = new Items(editText, date, type, setColor);
                Items items_db_old = new Items(old_itemname, old_date, old_type, old_color);
//                Log.v("editText", editText);
//                Log.v("date", date);
//                Log.v("type", type);
//                Log.v("color", setColor);
//                Log.v("editTextPostion_", String.valueOf(editTextPostion));
//                Log.v("old_editText", old_itemname);
//                Log.v("old_date", old_date);
//                Log.v("old_type", old_type);
//                Log.v("old_color", old_color);

                dbHandler.updateItem(items_db, items_db_old);
                populateListView();
                Toast.makeText(MainActivity.this, "Item Updated!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setupEditListViewListner() {

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Items its = (Items) parent.getAdapter().getItem(position);
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("editKeyText", its.get_itemname());
                i.putExtra("editKeyPosition", position);
                Log.v("editKeyPosition", String.valueOf(position));
                startActivityForResult(i, 20);
                old_itemname = its.get_itemname();
                old_date = its.get_date();
                old_type = its.get_type();
                old_color = its.get_color();
                return true;
            }
        });
    }

    public void onAddItem(View v) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("editKeyText", "");
        i.putExtra("editKeyPosition", -100);
        startActivityForResult(i, 20);
    }

    private void populateListView() {

        String TableName = MyDBHandler.TABLE_ITEMS;
        String query = "Select * from " + TableName + " order by " + MyDBHandler.COLUMN_TYPE + ";";
        dbHandler.executeQuery(query);
        Cursor c1 = dbHandler.executeQuery(query);
        itemsAdapter.clear();
        if (c1.getCount() > 0) {
            Log.v(" c1.getCount()", String.valueOf(c1.getCount()));
            if (c1.moveToFirst()) ;
            {
                do {
                    String itemname, date;
                    itemname = c1.getString(1);
                    date = c1.getString(2);
                    type = c1.getString(3);
                    setColor = c1.getString(4);
                    Items it = new Items(itemname, date, type, setColor);
                    Log.v("it.get_itemname()", it.get_itemname());
                    itemsAdapter.add(it);
                } while (c1.moveToNext());
            }
        }
    }
}