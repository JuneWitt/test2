package com.wittayapun.june.test2.ExpandableListAdapter;

import android.view.View;
import android.widget.TextView;

import com.wittayapun.june.test2.R;

public class ChildViewHolder {
    public TextView textView;

    public ChildViewHolder(View view) {
       textView = view.findViewById(R.id.tvChild);
    }
}
