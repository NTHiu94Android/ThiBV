package com.example.thibv.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class XeMay implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.maXe);
        dest.writeString(this.tenXe);
        dest.writeString(this.hangXe);
        dest.writeFloat(this.gia);
    }

    private int maXe;
    private String tenXe;
    private float gia;
    private String hangXe;

    public XeMay(int maXe, String tenXe, float gia, String hangXe) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.gia = gia;
        this.hangXe = hangXe;
    }

    public static final Creator<XeMay> CREATOR = new Creator<XeMay>() {
        @Override
        public XeMay createFromParcel(Parcel in) {
            return new XeMay(in);
        }

        @Override
        public XeMay[] newArray(int size) {
            return new XeMay[size];
        }
    };
    protected XeMay(Parcel in) {
        this.maXe = in.readInt();
        this.tenXe = in.readString();
        this.hangXe = in.readString();
        this.gia = in.readFloat();
    }

    public XeMay(String tenXe, float gia, String hangXe) {
        this.tenXe = tenXe;
        this.gia = gia;
        this.hangXe = hangXe;
    }

    public XeMay() {
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getHangXe() {
        return hangXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }
}
