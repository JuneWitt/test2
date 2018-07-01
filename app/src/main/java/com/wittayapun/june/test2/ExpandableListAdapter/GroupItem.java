package com.wittayapun.june.test2.ExpandableListAdapter;

import java.util.ArrayList;

public class GroupItem {
    public String text;
    public ArrayList<ChildItem> childItems;

    public GroupItem(String name, ArrayList<ChildItem> childItems) {
        this.text = name;
        this.childItems = childItems;
    }
}
