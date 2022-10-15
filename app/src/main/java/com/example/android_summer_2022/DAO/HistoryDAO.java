package com.example.android_summer_2022.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android_summer_2022.DTO.History;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDAO {
    private SQLiteDatabase db;

    public  static String ID="id";
    public  static String TABLE_HISTORY="history";
    public  static String MESSAGE="message";
    public  static String NUM_RANDOM="num_random";
    public  static String NUM_DUDOAN="num_dudoan";
    public  static String KETQUA="ketqua";
    public  static String THOIGIAN="thoigian";

    public HistoryDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }

    @SuppressLint("Range")
    public List<History> getList (String ...selectArgs) {
        List<History> list = new ArrayList<>();
        String queryString = "SELECT * FROM history";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        while (cursor.moveToNext()) {
            History history = new History();
            history.setId(cursor.getInt(cursor.getColumnIndex("id")));
            history.setHinh(cursor.getInt(cursor.getColumnIndex("num_random")));
            history.setSoDuDoan(cursor.getInt(cursor.getColumnIndex("num_dudoan")));
            history.setKetQuaCuoiCung(cursor.getInt(cursor.getColumnIndex("num_random")));
            int ketqua = cursor.getInt(cursor.getColumnIndex("num_random"));
            if(ketqua == 1) {
                history.setKetqua(true);
            } else  {
                history.setKetqua(false);
            }
//            history.setKetqua(cursor.getInt(cursor.getColumnIndex("num_random")) == 1 ? true : false );
            String thoigian = cursor.getString(cursor.getColumnIndex("thoigian"));
            Date date= null;
            try {
                date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(thoigian);
                history.setThoigian(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(history);
        }
        return list;
    }

    // new String[]{id}
    @SuppressLint("Range")
    public List<History> getDetail (String ...selectArgs) {
        List<History> list = new ArrayList<>();
        String queryString = "SELECT * FROM history where id=?";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        while (cursor.moveToNext()) {
            History history = new History();
            history.setId(cursor.getInt(cursor.getColumnIndex("id")));
            history.setHinh(cursor.getInt(cursor.getColumnIndex("num_random")));
            history.setSoDuDoan(cursor.getInt(cursor.getColumnIndex("num_dudoan")));
            history.setKetQuaCuoiCung(cursor.getInt(cursor.getColumnIndex("num_random")));
            int ketqua = cursor.getInt(cursor.getColumnIndex("num_random"));
            if(ketqua == 1) {
                history.setKetqua(true);
            } else  {
                history.setKetqua(false);
            }
//            history.setKetqua(cursor.getInt(cursor.getColumnIndex("num_random")) == 1 ? true : false );
            String thoigian = cursor.getString(cursor.getColumnIndex("thoigian"));
            Date date= null;
            try {
                date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(thoigian);
                history.setThoigian(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(history);
        }
        return list;
    }
    public  boolean createHistory(History history) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, history.getId());
//        contentValues.put(MESSAGE, history.get());
            contentValues.put(NUM_DUDOAN, history.getSoDuDoan());
            contentValues.put(NUM_RANDOM, history.getKetQuaCuoiCung());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(history.getThoigian());
            contentValues.put(THOIGIAN, strDate);
            contentValues.put(KETQUA, history.isKetqua());
            db.insert(TABLE_HISTORY, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  boolean update(History history) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(NUM_DUDOAN, history.getSoDuDoan());
            contentValues.put(NUM_RANDOM, history.getKetQuaCuoiCung());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(history.getThoigian());
            contentValues.put(THOIGIAN, strDate);
            contentValues.put(KETQUA, history.isKetqua());
            db.update(TABLE_HISTORY, contentValues, "id=?",
                    new String[] {String.valueOf(history.getId())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public  boolean delete(int id) {
        try{
            db.delete(TABLE_HISTORY, "id=?", new String[] { String.valueOf(id) });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
