package com.salemwika.andrade.salemwika;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Onyok on 2/12/2017.
 */

public class Database extends SQLiteOpenHelper {

    public static final String dbName = "salemWikaDB";
    public static final String faveTbl = "faveTBL";
    public static final String recentTbl = "recTBL";
    public static final String faveCol3 = "id";
    public static final String faveCol1 = "fave1";
    public static final String faveCol2 = "fave2";
    public static final String recCol1 = "rec1";
    public static final String recCol2 = "rec2";
    public static final String recCol3 = "id";

    public Database(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+faveTbl+" (id INTEGER PRIMARY KEY, fave1 TEXT, fave2 TEXT)");
        db.execSQL("create table "+recentTbl+" (id INTEGER PRIMARY KEY, rec1 TEXT, rec2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+faveTbl);
        db.execSQL("DROP TABLE IF EXISTS "+recentTbl);
        onCreate(db);
    }

    public boolean insertFave(String one, String two){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put(faveCol1, one);
        cont.put(faveCol2, two);
        long res = db.insert(faveTbl, null, cont);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllFave(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+faveTbl+" ORDER BY id DESC", null);
        return res;
    }

    public boolean insertRecent(String one, String two){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put(recCol1, one);
        cont.put(recCol2, two);
        long res = db.insert(recentTbl, null, cont);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllRecent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+recentTbl+" ORDER BY id DESC", null);
        return res;
    }


}
