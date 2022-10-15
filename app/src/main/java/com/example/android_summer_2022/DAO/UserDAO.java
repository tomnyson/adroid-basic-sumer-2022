package com.example.android_summer_2022.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_summer_2022.DTO.Product;
import com.example.android_summer_2022.DTO.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase db;

    public  static String TABLE_USER="user";
    public  static String USERNAME="username";
    public  static String PASSWORD="password";
    public  static String GIOI_TINH="sex";
    public  static String EMAIL="email";
    public  static String BIRTHDAY="birthday";
    public static String NAME="name";

    public UserDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }

//    @SuppressLint("Range")
//    public List<Product> getList (String ...selectArgs) {
//        List<Product> list = new ArrayList<>();
//        String queryString = "SELECT * FROM product";
//        Cursor cursor = db.rawQuery(queryString, selectArgs);
//        while (cursor.moveToNext()) {
//            Product product = new Product();
//            product.setId(cursor.getInt(cursor.getColumnIndex("id")));
//            product.setName(cursor.getString(cursor.getColumnIndex("name")));
//            product.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
//            product.setDes(cursor.getString(cursor.getColumnIndex("description")));
//            list.add(product);
//        }
//        return list;
//    }

    // new String[]{id}
//    @SuppressLint("Range")
//    public Product getDetail (String ...selectArgs) {
//        String queryString = "SELECT * FROM product where id=?";
//        Cursor cursor = db.rawQuery(queryString, selectArgs);
//        cursor.moveToFirst();
//        Product product = new Product();
//        product.setId(cursor.getInt(cursor.getColumnIndex("id")));
//        product.setName(cursor.getString(cursor.getColumnIndex("name")));
//        product.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
//        product.setDes(cursor.getString(cursor.getColumnIndex("description")));
//        return  product;
//    }
//    public  boolean create(Product product) {
//        try{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(NAME, product.getName());
//            contentValues.put(PRICE, product.getPrice());
//            contentValues.put(DES, product.getDes());
//            db.insert(TABLE_PRODUCT, null, contentValues);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public  boolean update(Product product) {
//        try{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(NAME, product.getName());
//            contentValues.put(PRICE, product.getPrice());
//            contentValues.put(DES, product.getDes());
//            db.update(TABLE_PRODUCT, contentValues, "id=?",
//                    new String[] {String.valueOf(product.getId())});
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    public  boolean delete(int id) {
//        try{
//            db.delete(TABLE_PRODUCT, "id=?", new String[] { String.valueOf(id) });
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

        public  boolean create(User user) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(USERNAME, user.getUsername());
            contentValues.put(PASSWORD, user.getPassword());
            contentValues.put(NAME, user.getName());
            contentValues.put(GIOI_TINH, user.getGioitinh());
            contentValues.put(EMAIL, user.getEmail());
            contentValues.put(BIRTHDAY, user.getBirthday().toString());
            db.insert(TABLE_USER, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkExistUsername(String username) {
        try{
            String queryString = "SELECT * FROM user where username=?";
        Cursor cursor = db.rawQuery(queryString, new String[] {username});
        if(cursor.moveToNext()) {
            return true;
        }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean checkLogin(String username, String password) {
        try{
            String queryString = "SELECT * FROM user where username=? and password=?";
            Cursor cursor = db.rawQuery(queryString, new String[] {username, password});
            if(cursor.moveToNext()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
