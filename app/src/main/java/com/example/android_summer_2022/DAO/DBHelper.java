package com.example.android_summer_2022.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public  static  String DB_NAME = "history";
    public  static int DB_VERSION = 4;
    public  static String ID="id";
    public  static String TABLE_HISTORY="history";
    public  static String MESSAGE="message";
    public  static String NUM_RANDOM="num_random";
    public  static String NUM_DUDOAN="num_dudoan";
    public  static String KETQUA="ketqua";
    public  static String THOIGIAN="thoigian";
    //---------------- product ------------------
    public  static String TABLE_PRODUCT="product";
    public static String NAME="name";
    public static String PRICE="price";
    public static String DES="description";
    public static String DISCOUNT="discount";
    public static String DATE="expiry_date";
    public static String CAT_ID="catId";
    public static String SALE="sale";
    public static String IMAGE="image";
    //--------------table user----------------
    public  static String TABLE_USER="user";
    public  static String USERNAME="username";
    public  static String PASSWORD="password";
    public  static String GIOI_TINH="sex";
    public  static String EMAIL="email";
    public  static String BIRTHDAY="birthday";
    //-------- danh mục sản phẩm
    public  static String TABLE_CATEGORY="category";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT," +
                        " %s INTEGER, %s INTEGER, %s INTEGER, %s DATETIME)",TABLE_HISTORY,ID,
                MESSAGE,NUM_RANDOM,NUM_DUDOAN,KETQUA,THOIGIAN, CAT_ID);
        sqLiteDatabase.execSQL(createTable);

        String createProductTable = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s TEXT," +
                        " %s REAL, %s REAL, %s INTEGER, %s DATETIME, %s BLOB, %s TEXT, %s INTEGER)",TABLE_PRODUCT,ID,
                NAME,PRICE, DISCOUNT, SALE, DATE, IMAGE, DES, CAT_ID);
        sqLiteDatabase.execSQL(createProductTable);

        String createUserTable = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY," +
                        " %s TEXT," +
                        " %s TEXT,%s TEXT, %s TEXT, %s DATETIME)",TABLE_USER,USERNAME,
                PASSWORD,NAME,EMAIL, GIOI_TINH, BIRTHDAY);
        sqLiteDatabase.execSQL(createUserTable);
        String createCat = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s TEXT)",TABLE_CATEGORY, ID, NAME);
        sqLiteDatabase.execSQL(createCat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = String.format("DROP TABLE IF EXISTS %s", TABLE_HISTORY);
        sqLiteDatabase.execSQL(dropTable);
        String dropProductTable = String.format("DROP TABLE IF EXISTS %s", TABLE_PRODUCT);
        sqLiteDatabase.execSQL(dropProductTable);
        String dropUserTable = String.format("DROP TABLE IF EXISTS %s", TABLE_USER);
        sqLiteDatabase.execSQL(dropUserTable);
        String dropCatTable = String.format("DROP TABLE IF EXISTS %s", TABLE_CATEGORY);
        sqLiteDatabase.execSQL(dropCatTable);
        onCreate(sqLiteDatabase);
    }
}
