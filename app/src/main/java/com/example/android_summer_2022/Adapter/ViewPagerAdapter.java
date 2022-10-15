package com.example.android_summer_2022.Adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_summer_2022.ASM.Fragements.CategoryFragment;
import com.example.android_summer_2022.ASM.Fragements.HomeFragment;
import com.example.android_summer_2022.ASM.Fragements.ProductFragment;

public class ViewPagerAdapter extends FragmentStateAdapter  {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i("TEST", "Fragment"+position);
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ProductFragment();
            case 2:
                return new CategoryFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
