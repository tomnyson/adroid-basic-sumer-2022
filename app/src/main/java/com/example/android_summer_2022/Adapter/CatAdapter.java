package com.example.android_summer_2022.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_summer_2022.DTO.Category;
import com.example.android_summer_2022.DTO.Product;
import com.example.android_summer_2022.R;

import java.util.List;

public class CatAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categories;

    public CatAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        if(i>0 && i <= categories.size()) {
            return categories.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            // view = LayoutInflater.from(context).inflate(R.layout.layout_nhanvien_item, null);
            view = LayoutInflater.from(context).inflate(R.layout.simple_listview_item, null);
        }
        //anh xa
        TextView ten = view.findViewById(R.id.tvName);
        Category cat = categories.get(i);
        if(cat!= null) {
            ten.setText(cat.getName());
            Log.i("TEST", cat.toString());
            return view;
        }
     return  null;
    }
}
