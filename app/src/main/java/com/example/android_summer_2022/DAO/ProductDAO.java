package com.example.android_summer_2022.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android_summer_2022.DTO.Category;
import com.example.android_summer_2022.DTO.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ProductDAO {
    private SQLiteDatabase db;

    public  static String ID="id";
    public  static String TABLE_PRODUCT="product";
    public static String NAME="name";
    public static String PRICE="price";
    public static String DES="description";
    public static String DISCOUNT="discount";
    public static String SALE="sale";
    public static String IMAGE="image";
    public static String DATE="expiry_date";
    public static String CAT_ID="catId";

    public ProductDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }

    @SuppressLint("Range")
    public List<Product> getList (String ...selectArgs) {
        List<Product> list = new ArrayList<>();
        String queryString = "SELECT *, p.id as productId, p.name as productName, c.name as catName, c.id as catId FROM product p INNER JOIN category c on p.catId = c.id";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndex("productId")));
            product.setName(cursor.getString(cursor.getColumnIndex("productName")));
            product.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
            product.setDes(cursor.getString(cursor.getColumnIndex("description")));
            product.setSale(cursor.getInt(cursor.getColumnIndex("sale")));
            product.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            product.setDiscount(cursor.getFloat(cursor.getColumnIndex("discount")));
            String tenCat = cursor.getString(cursor.getColumnIndex("catName"));
            SimpleDateFormat GMT_format = new SimpleDateFormat("yyyy.MM.dd");
            GMT_format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date1 = cursor.getString(cursor.getColumnIndex("expiry_date"));
            String localTime = cursor.getString(cursor.getColumnIndex("expiry_date"));
            Date date = null;
            try {
                date = (Date) GMT_format.parse(GMT_format.format(date1));
            } catch (Exception e) {
                Log.i("TEST", "loi date");
                e.printStackTrace();
            }
            product.setExpiry_date(date);
            int IdCat = cursor.getInt(cursor.getColumnIndex("catId"));
            Category cat =  new Category();
            cat.setId(IdCat);
            cat.setName(tenCat);
            product.setCategory(cat);
            Log.i("TEST", product.toString());
            list.add(product);
        }
        return list;
    }

    // new String[]{id}
    @SuppressLint("Range")
    public Product getDetail (String ...selectArgs) {
        String queryString = "SELECT * FROM product where id=?";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        cursor.moveToFirst();
        Product product = new Product();
        product.setId(cursor.getInt(cursor.getColumnIndex("p.id")));
        product.setName(cursor.getString(cursor.getColumnIndex("p.name")));
        product.setPrice(cursor.getFloat(cursor.getColumnIndex("p.price")));
        product.setDes(cursor.getString(cursor.getColumnIndex("p.description")));
        product.setSale(cursor.getInt(cursor.getColumnIndex("p.sale")));
        product.setImage(cursor.getBlob(cursor.getColumnIndex("p.image")));
        product.setDiscount(cursor.getFloat(cursor.getColumnIndex("p.discount")));
        String tenCat = cursor.getString(cursor.getColumnIndex("c.name"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
        DateFormat GMT_format = new SimpleDateFormat("dd/MM/yyyy" + " " + " HH:mm:ss");
        String localTime = cursor.getString(cursor.getColumnIndex("expiry_date"));
        GMT_format.setTimeZone(TimeZone.getTimeZone("GMT"));
        Log.i("TEST","\nThe local time is: " + localTime);
        Log.i("TEST","The time in Gmt is: " + GMT_format.format(localTime));
        Date date = null;
        try {
            date = simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex("expiry_date")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setExpiry_date(date);
        int IdCat = cursor.getInt(cursor.getColumnIndex("c.id"));
        Category cat =  new Category();
        cat.setId(IdCat);
        cat.setName(tenCat);
        product.setCategory(cat);
        return  product;
    }
    public  boolean create(Product product) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, product.getName());
            contentValues.put(PRICE, product.getPrice());
            contentValues.put(DES, product.getDes());
            contentValues.put(DISCOUNT, product.getDiscount());
            contentValues.put(CAT_ID, product.getCategory().getId());
            contentValues.put(SALE, product.getSale());
            contentValues.put(DATE, String.valueOf(product.getExpiry_date()));
            contentValues.put(IMAGE, product.getImage());
            db.insert(TABLE_PRODUCT, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  boolean update(Product product) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, product.getName());
            contentValues.put(PRICE, product.getPrice());
            contentValues.put(DES, product.getDes());
            contentValues.put(DISCOUNT, product.getDiscount());
            contentValues.put(SALE, product.getSale());
            contentValues.put(DATE, product.getExpiry_date().toString());
            contentValues.put(IMAGE, product.getImage());
            db.update(TABLE_PRODUCT, contentValues, "id=?",
                    new String[] {String.valueOf(product.getId())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public  boolean delete(int id) {
        try{
            db.delete(TABLE_PRODUCT, "id=?", new String[] { String.valueOf(id) });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
