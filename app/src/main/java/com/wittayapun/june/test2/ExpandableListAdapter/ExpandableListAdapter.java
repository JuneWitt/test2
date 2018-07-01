package com.wittayapun.june.test2.ExpandableListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.wittayapun.june.test2.R;

import java.util.ArrayList;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<GroupItem> mGroupItems;   // header titles
    // child data in format of header title, child title
    private GroupViewHolder mGroupHolder;
    private ChildViewHolder mChildHolder;

    public ExpandableListAdapter(Context context, ArrayList<GroupItem> items) {
        this.mContext = context;
        this.mGroupItems = items;
    }

    @Override
    public int getChildrenCount(int gPos) {
        return mGroupItems.get(gPos).childItems.size();
    }

    @Override
    public int getGroupCount() {
        return mGroupItems.size();
    }

    @Override
    public Object getGroup(int gPos) {
        return mGroupItems.get(gPos);
    }

    @Override
    public Object getChild(int gPos, int cPos) {
        return null;
    }

    @Override
    public long getGroupId(int gPos) {
        return gPos;
    }

    @Override
    public long getChildId(int gPos, int cPos) {
        return cPos;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int gPos, int cPos) {
        return true;
    }

    @Override
    public View getGroupView(int gPos, boolean isLast, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_group, viewGroup, false);
            mGroupHolder = new GroupViewHolder(view);
            view.setTag(mGroupHolder);
        } else {
            mGroupHolder = (GroupViewHolder) view.getTag();
        }
        GroupItem groupItemInfo = (GroupItem) getGroup(gPos);
        mGroupHolder.textView.setText(groupItemInfo.text);
        return view;
    }

    @Override
    public View getChildView(int gPos, int cPos, boolean isLast, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
            mChildHolder = new ChildViewHolder(view);
            view.setTag(mChildHolder);
        } else {
            mChildHolder = (ChildViewHolder) view.getTag();
        }
        ArrayList<ChildItem> childItems = mGroupItems.get(gPos).childItems;
        mChildHolder.textView.setText(childItems.get(cPos).text);
        return view;
    }
}
