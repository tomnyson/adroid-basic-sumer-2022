package com.example.android_summer_2022;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_summer_2022.Adapter.ProductAdapter;
import com.example.android_summer_2022.DAO.ProductDAO;
import com.example.android_summer_2022.DTO.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    /**
     *  1: anh xa;
     *
     */
    private EditText edMaSP, edTenSP, edGia, edDes;
    private Button btnThem, btnXoa, btnCapNhat;
    private GridView gvProduct;
    List<Product> list = new ArrayList<>();
    private ProductDAO dao;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        dao = new ProductDAO(getBaseContext());
        edMaSP = findViewById(R.id.edMaSP);
        edTenSP = findViewById(R.id.edTenSP);
        edGia = findViewById(R.id.edprice);
        edDes = findViewById(R.id.edDes);
        btnThem = findViewById(R.id.btnThem);
        edMaSP.setEnabled(false);
        gvProduct = findViewById(R.id.gvProduct);
        //----------- đổ dữ liệu ra gridview -----------
        list = dao.getList();
        productAdapter = new ProductAdapter(getBaseContext(), list);
        gvProduct.setAdapter(productAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String tenSP = edTenSP.getText().toString();
              String giaSP = edGia.getText().toString();
              String des = edDes.getText().toString();
              if(tenSP.trim().equals("") || giaSP.trim().equals("")
                      || des.trim().equals("")) {
                  Toast.makeText(getBaseContext(), "Không  để trống dữ liệu", Toast.LENGTH_SHORT).show();

              } else {
                  // dao

                  Product product = new Product();
                  product.setName(tenSP);
                  product.setPrice(Float.valueOf(giaSP));
                  product.setDes(des);
                  boolean isCreated = dao.create(product);
                  if(isCreated) {
                      list = dao.getList();
                      ProductAdapter productAdapter = new ProductAdapter(getBaseContext(), list);
                      productAdapter.notifyDataSetChanged();
                      Toast.makeText(getBaseContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                  } else {
                      Toast.makeText(getBaseContext(), "Thêm sản phẩm lỗi", Toast.LENGTH_SHORT).show();
                  }
              }
            }
        });
        registerForContextMenu(gvProduct);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int vitri = info.position;
        Product detail = list.get(vitri);
        if(item.getItemId() == R.id.menu_delete) {
            boolean delete = dao.delete(detail.getId());
            list.remove(vitri);
            productAdapter.notifyDataSetChanged();

        }
        Toast.makeText(getBaseContext(), vitri+"", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
