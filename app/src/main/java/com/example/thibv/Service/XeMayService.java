package com.example.thibv.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.thibv.DAO.XeMayDAO;
import com.example.thibv.Model.XeMay;

import java.util.ArrayList;

public class XeMayService extends Service {
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
        getListXeMay();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service đã dừng", Toast.LENGTH_SHORT).show();
    }

    private void getListXeMay(){
        list.clear();
        list = xeMayDAO.getAllXM();
        Toast.makeText(this.getApplicationContext(), "Lấy danh sách thành công!", Toast.LENGTH_SHORT).show();
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("UPDATE_UI_ACTION");
        broadcastIntent.putParcelableArrayListExtra("item_list", list);
        sendBroadcast(broadcastIntent);
    }

}
