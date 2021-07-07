package com.example.appxuatnhapkho.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appxuatnhapkho.Adapter.AdapterSP;
import com.example.appxuatnhapkho.Database.databaseSP;
import com.example.appxuatnhapkho.MainActivity;
import com.example.appxuatnhapkho.Object.ObjItemSP;
import com.example.appxuatnhapkho.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class Fragment1 extends Fragment {

    // Database
    databaseSP db;

    private AdapterSP adapterSP;
    private ArrayList<ObjItemSP> mArrayListSP;
    private RecyclerView rcv;
    private TextView tvTongTien;
    int TongTienDB = 0;

    // data db
    private int Id;
    private String tenSP;
    private String giaXuatSP;
    private String tien;
    private int soLuong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Database
        DataBaseSP();

        // Ánh xạ
        AnhXa(view);

        // ArrayList sản phẩm
        SPArrayList();

        // Adapter sản phẩm
        SPAdapter ();

        return view;
    }

    private void DataBaseSP () {
        // Tạo database
        db = new databaseSP(getContext(), "SanPham.sqlite", null, 1);
    }

    private void AnhXa (View view) {
        rcv = view.findViewById(R.id.rcv);
        tvTongTien = view.findViewById(R.id.tv_sotien);
    }

    private void SPArrayList () {
        // Khởi tạo ArrayList
        mArrayListSP = new ArrayList<>();

        // Add data arraylist
        AddDataArrayList ();
    }

    private void AddDataArrayList () {

        // Select data table
        SelectData();
    }

    private void SPAdapter () {
        // Khởi tạo adapter
        adapterSP = new AdapterSP((MainActivity) getActivity(),this, mArrayListSP);

        // Add adapter
        rcv.setAdapter(adapterSP);

        // Khởi tạo LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        // Set LinearLayoutManager
        rcv.setLayoutManager(linearLayoutManager);
    }

    private void SelectData () {
        // create cursor
        Cursor cursor = db.getData("SELECT * FROM SanPham");
        while (cursor.moveToNext()) {
            Id = cursor.getInt(0);
            tenSP = cursor.getString(1);
            giaXuatSP = cursor.getString(3);
            tien = cursor.getString(2);
            soLuong = cursor.getInt(4);

            System.out.println("tiền" + Float.parseFloat(tien));
            TongTienDB += Float.parseFloat(tien);

            mArrayListSP.add(new ObjItemSP(Id, tenSP, soLuong, currencyFormatter(tien) + " VND"));
        }

        tvTongTien.setText(currencyFormatter(String.valueOf(TongTienDB)) + " VND");
    }

    // Formatter currency
    public String currencyFormatter(String num) {
        double m = Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m);
    }

}