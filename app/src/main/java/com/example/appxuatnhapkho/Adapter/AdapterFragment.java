package com.example.appxuatnhapkho.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appxuatnhapkho.Fragment.Fragment1;
import com.example.appxuatnhapkho.Fragment.Fragment2;
import com.example.appxuatnhapkho.Fragment.Fragment3;
import com.example.appxuatnhapkho.Fragment.Fragment4;
import com.example.appxuatnhapkho.Fragment.Fragment5;

import org.jetbrains.annotations.NotNull;

public class AdapterFragment extends FragmentStatePagerAdapter {
    public AdapterFragment(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
            case 4:
                return new Fragment5();
            default:
                return new Fragment1();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
