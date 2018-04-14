package com.wittayapun.june.test2.item.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.wittayapun.june.test2.ChestDetailActivity;
import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.OnTapListener;
import com.wittayapun.june.test2.R;
import com.wittayapun.june.test2.item.Item;
import com.wittayapun.june.test2.item.SetViewHolder;

import java.util.Collections;
import java.util.List;

public class ChestAdapter extends RecyclerView.Adapter<SetViewHolder> {
    private Activity activity;
    List<Item> items = Collections.emptyList();
    static DatabaseHelper databaseHelper;

    private OnTapListener onTapListener;

    public ChestAdapter(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
    }

    @NonNull
    @Override
    public SetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);
        return new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetViewHolder holder, final int position) {
        holder.txt_Exer_Name.setText(items.get(position).getExer_name());   //  Name list
        //holder.Icon.setImageResource(position); // New Icon on list
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (onTapListener != null){
                    onTapListener.OnTapView(position);
                }*/

                Context context = view.getContext();
                Intent chestintent = new Intent(context, ChestDetailActivity.class);
                context.startActivity(chestintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  void setOnTapListener(OnTapListener onTapListener){
        this.onTapListener = onTapListener;
    }
}