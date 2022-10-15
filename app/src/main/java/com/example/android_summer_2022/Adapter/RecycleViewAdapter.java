package com.example.android_summer_2022.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_summer_2022.DTO.Product;
import com.example.android_summer_2022.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHoder> {
    List<Product> products = new ArrayList<>();
    Context context;

    public RecycleViewAdapter(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_product_cardview, parent, false);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Product current = products.get(position);
        Bitmap bmp = BitmapFactory.decodeByteArray(current.getImage(), 0, current.getImage().length);
        holder.spImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200,200, false));
        holder.txtTenSP.setText(current.getName());
        holder.txtGiaSP.setText(String.valueOf(current.getPrice()));
        holder.txtDanhMuc.setText(String.valueOf("loại: " + current.getCategory().getName()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView spImage;
        TextView txtTenSP, txtDanhMuc, txtGiaSP;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            spImage = itemView.findViewById(R.id.item_imgSP);
            txtTenSP = itemView.findViewById(R.id.item_tenSP);
            txtGiaSP = itemView.findViewById(R.id.item_giaSP);
            txtDanhMuc = itemView.findViewById(R.id.item_danhmucSP);
        }
    }
}
