package com.example.appxuatnhapkho.Fragment;

import android.app.Activity;
import android.content.Intent;
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

import com.cottacush.android.currencyedittext.CurrencyEditText;
import com.example.appxuatnhapkho.Database.databaseSP;
import com.example.appxuatnhapkho.MainActivity;
import com.example.appxuatnhapkho.R;

import java.text.DecimalFormat;

public class Fragment2 extends Fragment {

    // Database
    databaseSP db;

    EditText edtTenSp, edtSoLuong;
    CurrencyEditText edtGiaNhap, edtGiaXuat;
    Button btnThemSP;

    // data db
    private int Id;
    private String tenSPDB;
    private String giaXuatSPDB;
    private int flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        // Database
        DataBaseSP();

        // ánh xạ
        AnhXa(view);

        // Sự kiện btn
        ClickBTN();

        return view;
    }

    private void DataBaseSP () {
        // Tạo database
        db = new databaseSP(getContext(), "SanPham.sqlite", null, 1);
    }

    private void AnhXa (View view) {
        edtGiaNhap = view.findViewById(R.id.edt_gianhap);
        edtGiaXuat = view.findViewById(R.id.edt_giaxuat);
        btnThemSP = view.findViewById(R.id.btn_ThemSp);
        edtTenSp = view.findViewById(R.id.edt_tensp);
        edtSoLuong = view.findViewById(R.id.edt_soluong);
    }

    private void ClickBTN () {
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giaXuat = String.valueOf(edtGiaXuat.getNumericValue());
                String tenSP = edtTenSp.getText().toString().trim();
                String giaNhap = String.valueOf(edtGiaNhap.getNumericValue());
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());

                if (!TextUtils.isEmpty(edtTenSp.getText().toString().trim())) {
                    try {
                        // select and check sp
                        SelectData (tenSP, giaXuat, giaNhap, soLuong);
                    } catch (Exception e)  {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void SelectData(String tenSP, String giaXuat, String giaNhap, int soLuong) {
        // create cursor
        Cursor cursor = db.getData("SELECT * FROM SanPham");
        while (cursor.moveToNext()) {
            flag = 0;
            Id = cursor.getInt(0);
            tenSPDB = cursor.getString(1);
            giaXuatSPDB = cursor.getString(3);

            System.out.println("Tên sp trong db " + tenSPDB);
            System.out.println("Tên sp mình nhập " + tenSP);

            if (tenSPDB.equals(tenSP)) {
                flag = 1;
                Toast.makeText(getContext(), "Sản phẩm này đã tồn tại trong kho", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (flag == 0) {
            db.querydata("INSERT INTO SanPham VALUES (null, '"+tenSP+"', '"+giaNhap+"', '"+giaXuat+"', '"+soLuong+"', null, null, null)");
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            (getActivity()).overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        }
    }
}