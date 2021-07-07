package com.example.appxuatnhapkho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appxuatnhapkho.Fragment.Fragment1;
import com.example.appxuatnhapkho.MainActivity;
import com.example.appxuatnhapkho.Object.ObjItemSP;
import com.example.appxuatnhapkho.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterSP extends RecyclerView.Adapter<AdapterSP.SanPhamViewHolder>{

    MainActivity mainActivity;
    Fragment1 mContext;
    List<ObjItemSP> mList;

    public AdapterSP(MainActivity mainActivity, Fragment1 mContext, List<ObjItemSP> mList) {
        this.mainActivity = mainActivity;
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_sp, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterSP.SanPhamViewHolder holder, int position) {
        ObjItemSP objItemSP = mList.get(position);
        if (objItemSP == null) {
            return;
        }
        holder.tenSp.setText(objItemSP.getTenSp());
        holder.giaSp.setText(objItemSP.getGiaSp());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.CustomDialog(objItemSP.getId(), objItemSP.getTenSp(), objItemSP.getSoLuong());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {

        TextView tenSp, giaSp;
        CardView layout;

        public SanPhamViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout_item);
            tenSp = itemView.findViewById(R.id.tv_sp);
            giaSp = itemView.findViewById(R.id.tv_giasp);
        }
    }
}
