package com.example.glondhe.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();
        lvItems = (ListView) findViewById(R.id.IvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");
        setupListViewListner();
        setupEditListViewListner();


    }


    public void setupListViewListner(){
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item Deleted!!", Toast.LENGTH_LONG).show();
                        return true;
                    }

                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 20){

            String editText = data.getExtras().getString("editKeyText");
            int editTextPostion = data.getExtras().getInt("editKeyPosition");
            items.set(editTextPostion, editText);
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,"Item Updated!",Toast.LENGTH_LONG).show();
        }
    }

    private void readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e){
            items = new ArrayList<String>();
        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setupEditListViewListner(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("editKeyText",items.get(position));
                i.putExtra("editKeyPosition", position);
                startActivityForResult(i, 20);

            }
        });
    }

    public void onAddItem(View v){
        EditText etNewText = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewText.getText().toString();
        itemsAdapter.add(itemText);
        etNewText.setText("");
        writeItems();
    }
}
