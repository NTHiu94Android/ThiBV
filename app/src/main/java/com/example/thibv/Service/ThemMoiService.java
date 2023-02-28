package com.example.thibv.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.thibv.DAO.XeMayDAO;
import com.example.thibv.Model.XeMay;

import java.util.ArrayList;

public class ThemMoiService extends Service {
    private XeMayDAO xeMayDAO;
    private ArrayList<XeMay> list;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<>();
        Toast.makeText(this, "Service đã được khởi tạo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Context context = this.getApplicationContext();
        xeMayDAO = new XeMayDAO(context);
        addNewXeMay(intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service đã dừng", Toast.LENGTH_SHORT).show();
    }

    private void addNewXeMay(Intent intent){
        String strHangXe = intent.getStringExtra("hangXe");
        String strTenXe = intent.getStringExtra("tenXe");
        String strGiaXe = intent.getStringExtra("giaXe");
        float giaXe = Float.parseFloat(strGiaXe);
        //list = intent.getParcelableArrayListExtra("listMain");

        XeMay xeMay = new XeMay(strTenXe, giaXe, strHangXe);

        xeMayDAO.insertXM(xeMay);
        list.clear();
        list = xeMayDAO.getAllXM();
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("INSERT_UI_ACTION");
        broadcastIntent.putParcelableArrayListExtra("item_list_new", list);
        sendBroadcast(broadcastIntent);
        Toast.makeText(this.getApplicationContext(), "Thêm mới thành công!", Toast.LENGTH_SHORT).show();
    }
}
