package com.example.thibv.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private String sql = "";

    public DBHelper(@Nullable Context context) {
        super(context, "ThiCuoiMon.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sql = "CREATE TABLE XEMAY (MAXE INTEGER PRIMARY KEY AUTOINCREMENT, TENXE TEXT, GIA FLOAT, HANGXE TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO XEMAY(TENXE, GIA, HANGXE) VALUES('SH', 9000, 'Honda')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sql = "DROP TABLE IF EXISTS XEMAY";
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }
}
