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
import com.example.appxuatnhapkho.Object.ObjItemSP;
import com.example.appxuatnhapkho.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment4 extends Fragment {

    // Database
    databaseSP db;

    // UI
    EditText edtTenSP, edtTenNhaCungCap, edtSoDienThoai, edtDiaChi, edtSoLuong;
    Button btnThem;

    // variable
    String valueTenSP, valueTenNhaCungCap, valueDiaChi, valueSoLuong, valueSDT;

    // variable function SelectData
    String tenSP, tenNhaCungCap, diaChi, tenSPTrung;
    int soLuong, sDt, tongSoLuong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        // Databases
        DataBaseSP();

        // Ánh xạ
        AnhXa(view);

        // Xử lý lấy giá trị edittext
        GetValueEdt();

        return view;
    }

    private void DataBaseSP () {
        // Tạo database
        db = new databaseSP(getContext(), "SanPham.sqlite", null, 1);
    }

    private void AnhXa (View view) {
        edtTenSP = view.findViewById(R.id.edt_tensp);
        edtTenNhaCungCap = view.findViewById(R.id.edt_tenNhaCungCap);
        edtSoDienThoai = view.findViewById(R.id.edt_sdt);
        edtDiaChi = view.findViewById(R.id.edt_diachi);
        edtSoLuong = view.findViewById(R.id.edt_soluong);
        btnThem = view.findViewById(R.id.btn_nhapKho);
    }

    private void GetValueEdt () {
        // Sự kiện onclick btn
        SetOnclickBtn ();

    }

    private void SetOnclickBtn () {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenSPTrung = "";
                valueTenSP = edtTenSP.getText().toString().trim();
                valueTenNhaCungCap = edtTenNhaCungCap.getText().toString().trim();
                valueSDT = edtSoDienThoai.getText().toString().trim();
                valueDiaChi = edtDiaChi.getText().toString().trim();
                valueSoLuong = edtSoLuong.getText().toString().trim();

                if (!TextUtils.isEmpty(valueTenSP) && !TextUtils.isEmpty(valueTenNhaCungCap) && !TextUtils.isEmpty(valueSDT) && !TextUtils.isEmpty(valueDiaChi) && !TextUtils.isEmpty(valueSoLuong)) {
                    System.out.println(valueTenSP);
                    System.out.println(valueTenNhaCungCap);
                    System.out.println(valueSDT);
                    System.out.println(valueDiaChi);
                    System.out.println(valueSoLuong);

                    // Select data table
                    SelectData ();

                } else {
                    Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void SelectData () {
        // create cursor
        Cursor cursor = db.getData("SELECT * FROM SanPham WHERE tenSP = '"+valueTenSP+"'");
        while (cursor.moveToNext()) {
            tenSP = cursor.getString(1);
            tenNhaCungCap = cursor.getString(5);
            sDt = cursor.getInt(6);
            diaChi = cursor.getString(7);
            soLuong = cursor.getInt(4);

            // check trùng sp
            System.out.println("Tên sp trong db: " + tenSP);
            System.out.println("Tên sp nhập: " + valueTenSP);

            System.out.println(tenSP.equals(valueTenSP));

            if (tenSP.equals(valueTenSP)) {
                tenSPTrung = tenSP;
            }
        }

        if (tenSPTrung.equals("")) {
            Toast.makeText(getContext(), "Không có sản phẩm nào trong kho", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), tenSPTrung, Toast.LENGTH_SHORT).show();
            // xử lý update data
            UpdateData ();
        }
    }

    private void UpdateData () {
        try {

            // xử lý số lượng
            tongSoLuong = Integer.parseInt(valueSoLuong) + soLuong;

            // update
            db.querydata("UPDATE SanPham SET TenSP = '"+valueTenSP+"', TenNhaCungCap = '"+valueTenNhaCungCap+"', Sdt = '"+valueSDT+"', DiaChi = '"+valueDiaChi+"', SoLuong = '"+tongSoLuong+"' WHERE tenSP = '"+valueTenSP+"'");

            // thông báo
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

            // set string
            edtTenSP.setText("");
            edtTenNhaCungCap.setText("");
            edtSoDienThoai.setText("");
            edtDiaChi.setText("");
            edtSoLuong.setText("");

        } catch (Exception e) {
            Toast.makeText(getContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
        }

    }

}