package com.example.android_summer_2022.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android_summer_2022.DTO.Category;
import com.example.android_summer_2022.DTO.Product;

import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    private SQLiteDatabase db;

    public  static String ID="id";
    public  static String TABLE_CATEGORY="category";
    public static String NAME="name";

    public CatDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }

    @SuppressLint("Range")
    public List<Category> getList (String ...selectArgs) {
        List<Category> list = new ArrayList<>();
        String queryString = "SELECT * FROM category";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(cursor.getColumnIndex("id")));
            category.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(category);
        }
        return list;
    }

    // new String[]{id}
    @SuppressLint("Range")
    public Category getDetail (String ...selectArgs) {
        String queryString = "SELECT * FROM category where id=?";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        cursor.moveToFirst();
        Category cat = new Category();
        cat.setId(cursor.getInt(cursor.getColumnIndex("id")));
        cat.setName(cursor.getString(cursor.getColumnIndex("name")));
        return cat;
    }
    public  boolean create(Category cat) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, cat.getName());
            db.insert(TABLE_CATEGORY, null, contentValues);
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
            db.update(TABLE_CATEGORY, contentValues, "id=?",
                    new String[] {String.valueOf(product.getId())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public  boolean delete(int id) {
        try{
            db.delete(TABLE_CATEGORY, "id=?", new String[] { String.valueOf(id) });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
