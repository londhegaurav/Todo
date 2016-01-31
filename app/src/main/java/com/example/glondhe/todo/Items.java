package com.example.glondhe.todo;

/**
 * Created by glondhe on 10/1/15.
 */
public class Items {

    private int _id;
    private String _itemname;
    private String _date;
    private String _type;
    private String _color;


    public Items(  String itemname, String date, String type, String color) {
       // this._id = id;
        this._itemname = itemname;
        this._date = date;
        this._type = type;
        this._color = color;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public String get_itemname() {
        return _itemname;
    }

    public void set_itemname(String _itemname) {
        this._itemname = _itemname;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
}
