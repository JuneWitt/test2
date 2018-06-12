package com.wittayapun.june.test2.item;

import android.widget.ImageView;

public class Item{
    private int id;
    private String Exer_name;

    //  getter
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExer_name() {
        return Exer_name;
    }

    //  setter
    public void setExer_name(String exer_name) {
        Exer_name = exer_name;
    }
}