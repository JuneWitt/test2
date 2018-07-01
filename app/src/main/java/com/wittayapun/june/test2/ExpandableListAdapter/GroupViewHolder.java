package com.wittayapun.june.test2.ExpandableListAdapter;

import android.view.View;
import android.widget.TextView;

import com.wittayapun.june.test2.R;

public class GroupViewHolder {
    public TextView textView;

    public GroupViewHolder(View view) {
        textView = view.findViewById(R.id.tvHeader);
    }
}
