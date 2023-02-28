package com.example.thibv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.thibv.Helper.DBHelper;
import com.example.thibv.Model.XeMay;

import java.util.ArrayList;

public class XeMayDAO {
    private DBHelper helper;
    private SQLiteDatabase db;
    public XeMayDAO(Context context) {
        this.helper = new DBHelper(context);
    }
    //Lay danh sach xe may
    public ArrayList<XeMay> getAllXM(){
        ArrayList<XeMay> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM XEMAY", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maXM = cursor.getInt(0);
                String tenXM = cursor.getString(1);
                float gia = cursor.getFloat(2);
                String hangXe = cursor.getString(3);
                XeMay xeMay = new XeMay(maXM, tenXM, gia, hangXe);
                list.add(xeMay);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    //Them xe moi
    public void insertXM(XeMay xeMay) {
        ContentValues values = new ContentValues();
        db = helper.getWritableDatabase();
        values.put("TENXE", xeMay.getTenXe());
        values.put("GIA", xeMay.getGia());
        values.put("HANGXE", xeMay.getHangXe());
        db.insert("XEMAY", null, values);
        db.close();
    }
}
