package com.example.appxuatnhapkho.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appxuatnhapkho.Database.databaseSP;
import com.example.appxuatnhapkho.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment5 extends Fragment {

    // Database
    databaseSP db;

    // var db
    String tenSPDB;
    int soLuongDB;

    // var UI
    EditText edtTenSP, edtTenKH, edtSdt, edtSoLuong;
    Button btnXuatKho;

    String tenSP, tenKH, soDT, soLuong;

    int flag = 0, sPConLai;

    SimpleDateFormat dateFormat;
    Date date;
    int day, month, year;
    String dayMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_5, container, false);

        // Database
        DataBaseSP();

        // Ánh xạ
        AnhXa (view);

        // Sự kiện onclick
        ClickXuatKho ();

        return  view;
    }

    private void DataBaseSP () {
        // Tạo database
        db = new databaseSP(getContext(), "SanPham.sqlite", null, 1);
    }

    private void AnhXa (View view) {
        edtTenSP = view.findViewById(R.id.edt_tensp);
        edtTenKH = view.findViewById(R.id.edt_tenkh);
        edtSdt = view.findViewById(R.id.edt_sdt);
        edtSoLuong = view.findViewById(R.id.edt_soluong);
        btnXuatKho = view.findViewById(R.id.btn_xuatkho);
    }

    private void ClickXuatKho () {
        btnXuatKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tenSP = edtTenSP.getText().toString().trim();
                tenKH = edtTenKH.getText().toString().trim();
                soDT = edtSdt.getText().toString().trim();
                soLuong = edtSoLuong.getText().toString().trim();

                if (!TextUtils.isEmpty(tenSP) && !TextUtils.isEmpty(tenKH) && !TextUtils.isEmpty(edtSdt.getText().toString().trim()) && !TextUtils.isEmpty(soDT)) {

                    if (Integer.parseInt(edtSoLuong.getText().toString().trim()) == 0) {
                        // Thông báo
                        Toast.makeText(getContext(), "Vui lòng nhập số lượng lớn hơn 0", Toast.LENGTH_SHORT).show();

                    } else {

                        // Select table SanPham
                        SelectSanPham ();
                    }

                } else {
                    // Thông báo nhập đầy đủ
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SelectSanPham () {

        Cursor cursor = db.getData("SELECT * FROM SanPham");

        while (cursor.moveToNext()) {
            tenSPDB = cursor.getString(1);
            soLuongDB = cursor.getInt(4);

            if (tenSP.equals(tenSPDB)) {
                flag = 1;
                break;
            } else {
                flag = 0;
            }
        }

        System.out.println(flag);

        switch (flag) {
            case 0:
                Toast.makeText(getContext(), "Sản phẩm không tồn tại trong kho", Toast.LENGTH_SHORT).show();
            break;
            case 1:
                UpdateSoLuong ();
            break;
        }

    }

    private void UpdateSoLuong () {
        System.out.println(soLuongDB);
        if (soLuongDB <= 0) {
            Toast.makeText(getContext(), "Sản phẩm này đã hết", Toast.LENGTH_SHORT).show();
        } else if (soLuongDB > 0 && Integer.parseInt(soLuong) > soLuongDB) {
            Toast.makeText(getContext(), "Số lượng sản phẩm còn lại trong kho không đủ", Toast.LENGTH_SHORT).show();
        } else {
            try {
                // sản phẩm còn lại
                sPConLai = soLuongDB - Integer.parseInt(soLuong);

                // Update table SanPham
                UpdateTableSP ();

            } catch (Exception e) {
                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void  UpdateTableSP () {
        try {
            db.querydata("UPDATE SanPham SET SoLuong = '" + sPConLai + "' WHERE TenSp = '"+ tenSP +"'");
            Toast.makeText(getContext(), "Xuất kho thành công", Toast.LENGTH_SHORT).show();

            edtTenSP.setText("");
            edtTenKH.setText("");
            edtSdt.setText("");
            edtSoLuong.setText("");

            // Insert hoá đơn vào table XuatKho
            InsertXuatKho ();

        } catch (Exception e) {
            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }

    }

    private void InsertXuatKho () {

        // get time
        GetTimer ();

        // Xử lý insert
        db.querydata("INSERT INTO XuatKho VALUES (null, '"+tenSP+"', '"+tenKH+"', '"+soDT+"', '"+soLuong+"', '"+dateFormat.format(date)+"')");
        System.out.println(dateFormat.format(date));
        System.out.println(tenSP);
        System.out.println(tenKH);
        System.out.println(soDT);
        System.out.println(soLuong);
    }

    private void GetTimer () {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);

        dayMonth = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

        dateFormat= new SimpleDateFormat("dd/MM/yyyy");

        try {
            date = dateFormat.parse(dayMonth);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}