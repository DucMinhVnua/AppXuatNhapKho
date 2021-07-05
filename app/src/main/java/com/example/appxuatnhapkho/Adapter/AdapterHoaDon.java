package com.example.appxuatnhapkho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appxuatnhapkho.Object.ObjItemHoaDon;
import com.example.appxuatnhapkho.R;

import java.util.List;

public class AdapterHoaDon extends RecyclerView.Adapter<AdapterHoaDon.HoaDonViewHolder>{

    Context mContext;
    List<ObjItemHoaDon> mList;

    public AdapterHoaDon(Context mContext, List<ObjItemHoaDon> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_hoadon, parent, false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        ObjItemHoaDon objItemHoaDon = mList.get(position);

        if (objItemHoaDon == null) {
            return;
        }

        holder.tvTenSP.setText(objItemHoaDon.getTenSP());
        holder.tvTenKH.setText(objItemHoaDon.getTenKH());
        holder.tvSDT.setText(String.valueOf(objItemHoaDon.getSoDienThoai()));
        holder.tvSoLuong.setText(String.valueOf(objItemHoaDon.getSoLuong()));
        holder.tvNgayThang.setText(objItemHoaDon.getNgayThang());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSP, tvTenKH, tvSDT, tvSoLuong, tvNgayThang;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenSP = itemView.findViewById(R.id.tv_item_tensp);
            tvTenKH = itemView.findViewById(R.id.tv_item_tenkh);
            tvSDT = itemView.findViewById(R.id.tv_item_sdt);
            tvSoLuong = itemView.findViewById(R.id.tv_item_soluong);
            tvNgayThang = itemView.findViewById(R.id.tv_item_ngaythang);
        }
    }
}
