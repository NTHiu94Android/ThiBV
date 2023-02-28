package com.example.thibv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import com.example.thibv.Adapter.XeMayAdapter;
import com.example.thibv.BroadcastReceiver.MyBroadcastReceiver;
import com.example.thibv.Model.XeMay;
import com.example.thibv.Service.XeMayService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static RecyclerView rcv;
    private Button btn;
    public static XeMayAdapter adapter;
    public static ArrayList<XeMay> list;
    public static MyBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.rcv);
        btn = findViewById(R.id.btn);
        adapter = new XeMayAdapter();
        list = new ArrayList<>();
        mBroadcastReceiver = new MyBroadcastReceiver(adapter, rcv, list, MainActivity.this);

        onStartService();

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ThemMoiActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("myRecyclerView", rcv.getLayoutManager().onSaveInstanceState());
            bundle.putParcelableArrayList("listMain", list);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        });
    }

    private void onStartService() {
        // Đăng ký Broadcast Receiver để nhận dữ liệu từ service
        IntentFilter filter = new IntentFilter();
        filter.addAction("UPDATE_UI_ACTION");
        registerReceiver(mBroadcastReceiver, filter);

        Intent intent = new Intent(this, XeMayService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy đăng ký Broadcast Receiver
        unregisterReceiver(mBroadcastReceiver);
    }
}