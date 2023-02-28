package com.example.thibv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;

import com.example.thibv.Adapter.XeMayAdapter;
import com.example.thibv.BroadcastReceiver.MyBroadcastReceiver;
import com.example.thibv.Model.XeMay;
import com.example.thibv.Service.ThemMoiService;

import java.util.ArrayList;

public class ThemMoiActivity extends AppCompatActivity {
    private EditText edtHangXe, edtTenXe, edtGiaXe;
    private Button btnThemXe;

    private MyBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);
        edtHangXe = findViewById(R.id.edtHangXe);
        edtTenXe = findViewById(R.id.edtTenXe);
        edtGiaXe = findViewById(R.id.edtGiaXe);
        btnThemXe = findViewById(R.id.btnThemXe);

        mBroadcastReceiver = MainActivity.mBroadcastReceiver;

        // Đăng ký Broadcast Receiver để nhận dữ liệu từ service
        IntentFilter filter = new IntentFilter();
        filter.addAction("INSERT_UI_ACTION");
        registerReceiver(mBroadcastReceiver, filter);

        btnThemXe.setOnClickListener(view -> onStartService());
    }

    private void onStartService() {
        Intent intent = new Intent(this, ThemMoiService.class);
        intent.putExtra("hangXe", edtHangXe.getText().toString());
        intent.putExtra("tenXe", edtTenXe.getText().toString());
        intent.putExtra("giaXe", edtGiaXe.getText().toString());
        //intent.putParcelableArrayListExtra("listMain", list);
        startService(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy đăng ký Broadcast Receiver
        unregisterReceiver(mBroadcastReceiver);
    }
}