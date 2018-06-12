package com.wittayapun.june.test2;

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

import com.wittayapun.june.test2.item.Adapter.AbsAdapter;
import com.wittayapun.june.test2.item.Item;

import java.io.InputStream;
import java.util.ArrayList;

public class FragmentAbs  extends Fragment implements OnTapListener{
    View view;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ArrayList<Item> absList = new ArrayList<Item>();
    private Cursor cursor;
    private AbsAdapter adapter;


    public FragmentAbs() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.abs_fragment,container,false);
        recyclerView = view.findViewById(R.id.abs_recyclerview);
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
            cursor = databaseHelper.QueryData("select * from Exer_list where M_Group = 'Abs'");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Item item = new Item();

                        //item.setIcon(cursor.getString(2));
                        item.setExer_name(cursor.getString(1));
                        item.setId(cursor.getInt(0));

                        absList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new AbsAdapter(getActivity(), absList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnTapView(View view, int position) {
        Intent absIntent = new Intent(getActivity(),ChestDetailActivity.class);
        absIntent.putExtra("ID",absList.get(position).getId()+"");
        startActivity(absIntent);
    }
}
