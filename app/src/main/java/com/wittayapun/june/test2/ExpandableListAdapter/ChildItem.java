package com.wittayapun.june.test2.ExpandableListAdapter;

public class ChildItem {

    public int _id;
    public String text;
/*
    public ChildItem(String text) {
        this.text = text;
    }
    */

// Getter
    public int get_id() {
        return _id;
    }

    public String getText() {
        return text;
    }

//Setter
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
