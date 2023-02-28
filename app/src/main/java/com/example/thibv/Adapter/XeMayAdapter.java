package com.example.thibv.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.thibv.Model.XeMay;
import com.example.thibv.R;

import java.util.ArrayList;

public class XeMayAdapter extends RecyclerView.Adapter<XeMayAdapter.XeMayViewHolder>{
    private ArrayList<XeMay> list;

    public void getData( ArrayList<XeMay> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XeMayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xemay, parent, false);
        return new XeMayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XeMayViewHolder holder, int position) {
        XeMay xeMay = list.get(position);
        if(xeMay == null){
            return;
        }

        holder.tvMaXe.setText(String.valueOf(xeMay.getMaXe()));
        holder.tvHangXe.setText(xeMay.getHangXe());
        holder.tvTenXe.setText(xeMay.getTenXe());
        holder.tvGiaXe.setText(String.valueOf(xeMay.getGia()));
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class XeMayViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMaXe, tvTenXe, tvHangXe, tvGiaXe;
        public XeMayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaXe = itemView.findViewById(R.id.txtMaXe);
            tvHangXe = itemView.findViewById(R.id.txtHangXe);
            tvTenXe = itemView.findViewById(R.id.txtTenXe);
            tvGiaXe = itemView.findViewById(R.id.txtGiaXe);
        }
    }
}
