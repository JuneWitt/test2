package com.wittayapun.june.test2.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wittayapun.june.test2.R;

public class SetViewHolder extends RecyclerView.ViewHolder{

    public TextView txt_Exer_Name;

    public SetViewHolder(View itemView) {
        super(itemView);
        txt_Exer_Name = itemView.findViewById(R.id.txtShowNameExer);
    }
}
