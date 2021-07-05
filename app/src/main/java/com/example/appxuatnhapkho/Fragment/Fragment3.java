package com.example.appxuatnhapkho.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appxuatnhapkho.Adapter.AdapterHoaDon;
import com.example.appxuatnhapkho.Database.databaseSP;
import com.example.appxuatnhapkho.Object.ObjItemHoaDon;
import com.example.appxuatnhapkho.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment3 extends Fragment {

    // Database
    databaseSP db;

    // UI
    EditText dateSearch;
    String valueSearch;
    Button btnSearch;

    RecyclerView rcv;
    AdapterHoaDon adapterHoaDon;
    ArrayList<ObjItemHoaDon> mArrayList;

    // var db table SanPham
    int Id, soLuong;
    String tenSP, tenKH, sdt, ngayThang;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        // Databases
        DataBaseSP();

        // Ánh xạ UI
        AnhXa (view);

        // Arraylist
        InitArrayList();

        // Adapter
        InitAdapter();

        // Search
        Search ();

        // Sự kiện oclick
        OnclickBTN ();

        return view;
    }

    private void DataBaseSP () {
        // Tạo database
        db = new databaseSP(getContext(), "SanPham.sqlite", null, 1);
    }

    private void AnhXa (View view) {
        rcv = view.findViewById(R.id.rcv);
        dateSearch = view.findViewById(R.id.edt_search);
        btnSearch = view.findViewById(R.id.btn_search);
    }

    private void InitArrayList () {
        mArrayList = new ArrayList<>();

        // add arraylist
        AddDataArrayList ();
    }

    private void AddDataArrayList () {

        Cursor cursor = db.getData("SELECT * FROM XuatKho");

        while (cursor.moveToNext()) {
            Id = cursor.getInt(0);
            soLuong = cursor.getInt(4);
            tenSP = cursor.getString(1);
            tenKH = cursor.getString(2);
            sdt = cursor.getString(3);
            ngayThang = cursor.getString(5);

            mArrayList.add(new ObjItemHoaDon(Id, soLuong, tenSP, tenKH,ngayThang , sdt ));
        }

    }

    private void InitAdapter () {
        adapterHoaDon = new AdapterHoaDon(getContext(), mArrayList);

        // LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(linearLayoutManager);

        // set adapter
        rcv.setAdapter(adapterHoaDon);
    }

    private void Search () {
        FormatSearch ();
    }

    private void FormatSearch () {
        dateSearch.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    dateSearch.setText(current);
                    dateSearch.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void OnclickBTN () {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueSearch = dateSearch.getText().toString();

                if (!TextUtils.isEmpty(valueSearch)) {
                    System.out.println(valueSearch);

                    // find value search
                    FindValueSearch ();
                }
            }
        });
    }

    private void FindValueSearch () {

        mArrayList.clear();

        Cursor cursor = db.getData("SELECT * FROM XuatKho WHERE NgayThang = '"+valueSearch+"'");
        while (cursor.moveToNext()) {
            Id = cursor.getInt(0);
            soLuong = cursor.getInt(4);
            tenSP = cursor.getString(1);
            tenKH = cursor.getString(2);
            sdt = cursor.getString(3);
            ngayThang = cursor.getString(5);

            mArrayList.add(new ObjItemHoaDon(Id, soLuong, tenSP, tenKH,ngayThang , sdt ));
            adapterHoaDon.notifyDataSetChanged();
        }
    }
}