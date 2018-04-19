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
import android.widget.Toast;

import com.wittayapun.june.test2.ChestDetailActivity;
import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.OnTapListener;
import com.wittayapun.june.test2.R;
import com.wittayapun.june.test2.item.Item;
import com.wittayapun.june.test2.item.SetViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChestAdapter extends RecyclerView.Adapter<SetViewHolder> {

    private Activity activity;
    List<Item> items = Collections.emptyList();
    private DatabaseHelper databaseHelper;
    private ArrayList<Item> chestList = new ArrayList<Item>();
    private Context context;

    private OnTapListener onTapListener;

    public ChestAdapter(Activity activity, List<Item> items,Context context, OnTapListener onTapListener) {
        this.activity = activity;
        this.items = items;
        this.context = context;
        this.onTapListener = onTapListener;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTapListener.OnTapView(view,position);
            }
        });
        //holder.person_name.setText(list.get(position).getName());
        //holder.person_img.setImageResource(items.get(position).getPhoto_id()); getIcon // New Icon on list
        /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"position = "+ getItemId(position),Toast.LENGTH_SHORT).show();
                Toast.makeText(view.getContext(), (position),Toast.LENGTH_SHORT).show();

                Item clickItemRecycler = items.get(position);
                long itemsID = clickItemRecycler.getId();
                Intent chestIntent = new Intent(view.getContext(),ChestDetailActivity.class);
                chestIntent.putExtra("ID", view.getId());
                view.getContext().startActivities(new Intent[]{chestIntent});
            }
        });  */
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  void setOnTapListener(OnTapListener onTapListener){
        this.onTapListener = onTapListener;
    }

}