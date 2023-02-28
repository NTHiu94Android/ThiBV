package com.example.thibv.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibv.Adapter.XeMayAdapter;
import com.example.thibv.Model.XeMay;

import java.util.ArrayList;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private XeMayAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<XeMay> mlist;

    public MyBroadcastReceiver() {
    }
    public MyBroadcastReceiver(XeMayAdapter adapter, RecyclerView recyclerView, ArrayList<XeMay> list, Context context) {
        mAdapter = adapter;
        mRecyclerView = recyclerView;
        mlist = list;
        mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("UPDATE_UI_ACTION")) {
            mlist = (ArrayList<XeMay>) intent.getSerializableExtra("item_list");
            // Cập nhật dữ liệu của mAdapter và hiển thị danh sách trên mRecyclerView
            mAdapter.getData(mlist);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter.notifyDataSetChanged();
        }
        if (intent.getAction().equals("INSERT_UI_ACTION")) {
            mlist = (ArrayList<XeMay>) intent.getSerializableExtra("item_list_new");
            // Cập nhật dữ liệu của mAdapter và hiển thị danh sách trên mRecyclerView
            mAdapter.getData( mlist);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter.notifyDataSetChanged();
        }
    }
}
