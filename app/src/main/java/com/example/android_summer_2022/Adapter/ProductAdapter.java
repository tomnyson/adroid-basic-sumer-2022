package com.example.android_summer_2022.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_summer_2022.DAO.HistoryDAO;
import com.example.android_summer_2022.DTO.History;
import com.example.android_summer_2022.DTO.Product;
import com.example.android_summer_2022.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        if(i>0 && i <= products.size()) {
            return products.get(i);
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
            view = LayoutInflater.from(context).inflate(R.layout.product_item, null);
        }
        //anh xa
        ImageView hinhSP = view.findViewById(R.id.gv_product_img);
        TextView tenSp = view.findViewById(R.id.gv_product_name);
        TextView giaSp = view.findViewById(R.id.gv_product_price);
        TextView moTaSp = view.findViewById(R.id.gv_product_des);
        Product product = products.get(i);
        if(product!= null) {
            hinhSP.setImageResource(R.drawable.hinh1);
            tenSp.setText(product.getName());
            giaSp.setText(String.valueOf(product.getPrice()));
            moTaSp.setText(product.getDes());
            Log.i("TEST", product.toString());
            return view;
        }

     return  null;
    }
}
