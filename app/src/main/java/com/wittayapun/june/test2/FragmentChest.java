package com.wittayapun.june.test2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.wittayapun.june.test2.item.Adapter.ChestAdapter;
import com.wittayapun.june.test2.item.Item;

import java.util.ArrayList;

public class FragmentChest extends Fragment implements OnTapListener {
    View view;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ArrayList<Item> chestList = new ArrayList<Item>();
    private Cursor cursor;
    private ChestAdapter adapter;

    public FragmentChest() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chest_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.chest_recyclerview);
        loadDatabase();
        return view;
    }

    public void loadDatabase() {
            databaseHelper = new DatabaseHelper(getActivity());
            try {
                databaseHelper.checkAndCopyDatabase();
                databaseHelper.openDatabase();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }

            try {
                cursor = databaseHelper.QueryData("select * from Exer_list where M_Group = 'Chest'");
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            Item item = new Item();
                            item.setExer_name(cursor.getString(1));
                            item.setId(cursor.getInt(0));

                            chestList.add(item);
                        } while (cursor.moveToNext());
                    }
                }
            } catch (SQLiteException e) {
                e.printStackTrace();
            }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ChestAdapter(getActivity(), chestList, getContext(), this);
        adapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnTapView(View view, int position) {

        Intent chestIntent = new Intent(getActivity(),ChestDetailActivity.class);
        chestIntent.putExtra("ID",chestList.get(position).getId()+"");
        Toast.makeText(getActivity(), chestList.get(position).getId()+"",Toast.LENGTH_SHORT).show();
        startActivity(chestIntent);
    }
}
