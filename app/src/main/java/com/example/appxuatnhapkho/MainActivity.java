package com.example.appxuatnhapkho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appxuatnhapkho.Adapter.AdapterFragment;
import com.example.appxuatnhapkho.Adapter.AdapterSP;
import com.example.appxuatnhapkho.Database.databaseSP;
import com.example.appxuatnhapkho.Object.ObjItemSP;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Database
    databaseSP db;

    ViewPager viewPager;
    BottomNavigationView bottom;
    AdapterFragment adapterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database
        DatabaseSP ();

        // ánh xạ
        AnhXa();

        // BottomNav
        BottomNav();

        // SetUpViewPager
        SetUpViewPager();
    }

    private void DatabaseSP () {
        // Tạo database
        db = new databaseSP(this, "SanPham.sqlite", null, 1);

        // Tạo table
        db.querydata("CREATE TABLE IF NOT EXISTS SanPham (Id INTEGER PRIMARY KEY AUTOINCREMENT, TenSp VARCHAR(200), GiaNhap VARCHAR(200), GiaXuat VARCHAR(200), SoLuong INTEGER, TenNhaCungCap VARCHAR(100), Sdt INTEGER, DiaChi VARCHAR(200))");
    }

    private void AnhXa () {
        viewPager = findViewById(R.id.view_pager);
        bottom = findViewById(R.id.bottom);
    }

    private void BottomNav () {
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.item_4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.item_5:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    private void SetUpViewPager () {
        adapterFragment = new AdapterFragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterFragment);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottom.getMenu().findItem(R.id.item_1).setChecked(true);
                        break;
                    case 1:
                        bottom.getMenu().findItem(R.id.item_2).setChecked(true);
                        break;
                    case 2:
                        bottom.getMenu().findItem(R.id.item_3).setChecked(true);
                        break;
                    case 3:
                        bottom.getMenu().findItem(R.id.item_4).setChecked(true);
                        break;
                    case 4:
                        bottom.getMenu().findItem(R.id.item_5).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}