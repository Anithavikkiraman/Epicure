package com.example.anithavikkiraman.db_trial;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Anitha Vikkiraman on 8/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Fooditems.db";
    public static final String TABLE_NAME = "menu_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FOODNAME";
    public static final String COL_3 = "DESC";
    public static final String COL_4 = "PRICE";
    public static final String NEW_TABLE_NAME = "order_table";
    public static final String COL1 = "SNO";
    public static final String COL2 = "FOODNAME";
    public static final String COL3 = "QUANTITY";
    public static final String COL4 = "PRICE";
    public static final String COL5 = "AMOUNT";

    String[] columns ={COL2,COL3};


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FOODNAME TEXT,DESC TEXT,PRICE INTEGER)");
        db.execSQL("create table " + NEW_TABLE_NAME + " (SNO INTEGER PRIMARY KEY AUTOINCREMENT,FOODNAME TEXT,QUANTITY INTEGER,PRICE INTEGER,AMOUNT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NEW_TABLE_NAME);

        onCreate(db);
    }

    public boolean insertData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, "abc");
        contentValues.put(COL_3, "xyz");
        contentValues.put(COL_4, "30");

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


   /* public boolean insertorder(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, surname);
        contentValues.put(COL4, marks);
        contentValues.put(COL5, marks);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }*/
    public boolean newinsertData(String fname, int quants, int price, int amt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fname);
        contentValues.put(COL3, quants);
        contentValues.put(COL3, price);
        contentValues.put(COL3, amt);

        long result = db.insert(NEW_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


   /* public Cursor newgetAllData(String regno) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + NEW_TABLE_NAME + " WHERE " + COL2 + "="+regno,null);
        return res;
       /* System.out.println(regno);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + NEW_TABLE_NAME + " WHERE " + COL2 + "="+ regno,null);
        return res;
    }

    public Cursor getprice(String fname) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.rawQuery("SELECT * FROM tbl1 WHERE name = '"+name+"'", null);

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + COL2 + "=\'"+fname+"\'",null);
        return res;
    }*/


   public Cursor newgetAllData(String key) {
       System.out.println(key);

       SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("select * from "+NEW_TABLE_NAME,null);
      Cursor res = db.rawQuery("SELECT * FROM " + NEW_TABLE_NAME + " WHERE " + COL2 + "=\'"+key+"\'",null);
      // Cursor res = db.query(NEW_TABLE_NAME,columns , "ENAME=\'"+key+"\'", null, null, null, null);
       //Cursor res = db.query(NEW_TABLE_NAME,columns , COL2+ "=\'"+key+"\'", null, null, null, null);



       return res;
    }

}