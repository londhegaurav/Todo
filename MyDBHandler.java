package com.example.glondhe.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by glondhe on 10/1/15.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "todoItems.db";
    public static final String TABLE_ITEMS = "todoItems";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMNAME = "itemname";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_COLOR = "color";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_ITEMS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEMNAME + " TEXT UNIQUE, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_COLOR + " TEXT " +
                " );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateItem(Items items, Items items_old) {
        SQLiteDatabase db = getWritableDatabase();
        Log.v("Updating data", items_old.get_itemname() + " & " + items.get_itemname());
        db.execSQL("Update " + TABLE_ITEMS + " set " + COLUMN_ITEMNAME + "='" + items.get_itemname() + "' , " + COLUMN_DATE + "='" + items.get_date() + "' , " + COLUMN_TYPE + "='" + items.get_type() + "' , " + COLUMN_COLOR + "='" + items.get_color() + "' where " + COLUMN_ITEMNAME + "='" + items_old.get_itemname() + "' and " + COLUMN_DATE + "='" + items_old.get_date() + "';");
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM '" + TABLE_ITEMS + "';");
        Log.v("DELETED ALL", "");
    }

    public void deleteItem(Items items) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM '" + TABLE_ITEMS + "' where " + COLUMN_ITEMNAME + "='" + items.get_itemname() + "' and " + COLUMN_DATE + "='" + items.get_date() + "';");
        Log.v("DELETED itemname()", items.get_itemname());
        Log.v("DELETED date()", items.get_date());
    }

    public void dropItems() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE '" + TABLE_ITEMS + "';");
        Log.v("Drop table", TABLE_ITEMS);
    }

    public void addItem(Items items) {
        ContentValues values = new ContentValues();
        Log.v("items.get_itemname(): ", items.get_itemname());
        Log.v("items.get_date(): ", items.get_date());
        values.put(COLUMN_ITEMNAME, items.get_itemname());
        values.put(COLUMN_DATE, String.valueOf(items.get_date()));
        values.put(COLUMN_TYPE, items.get_type());
        values.put(COLUMN_COLOR, items.get_color());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEMS, null, values);
    }

    public Cursor executeQuery(String query) {
        Cursor c1 = null;
        try {
            Log.v("Executing select Query", "");
            SQLiteDatabase db = getWritableDatabase();
            c1 = db.rawQuery(query, null);
        } catch (Exception e) {
            System.out.println("DATABASE ERROR " + e);
        }
        return c1;
    }
}