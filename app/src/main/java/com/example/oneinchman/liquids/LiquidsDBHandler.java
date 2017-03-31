package com.example.oneinchman.liquids;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class LiquidsDBHandler extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "liquids.db";
    public final static String DATABASE_TABLE = "Liquids";
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_COMPONENTS = "components";
    public final static String COLUMN_PROPORTIONS = "proportions";
    public final static String COLUMN_LIQUIDNAME = "liquidName";

    public LiquidsDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE +
                " ( " + COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_COMPONENTS + " text, " +
                COLUMN_PROPORTIONS + " text, " +
                COLUMN_LIQUIDNAME + " text );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteTableFromDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "drop table if exists " + DATABASE_TABLE + ";";
        db.execSQL(query);
        db.close();
    }

    public void addLiquid(Liquids liquid) {
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
        ContentValues content = new ContentValues();
        content.put(COLUMN_COMPONENTS, liquid.get_components().replace(", ", "\n"));
        content.put(COLUMN_PROPORTIONS, liquid.get_proportions().replace(", ", "\n"));
        content.put(COLUMN_LIQUIDNAME, liquid.get_liquidName());
        db.insert(DATABASE_TABLE, null, content);
        db.close();
    }

    public ArrayList<ArrayList<String>> getAllEntries() {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();
        entries.add(new ArrayList<String>());
        entries.add(new ArrayList<String>());
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            entries.get(0).add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPONENTS)));
            entries.get(1).add(cursor.getString(cursor.getColumnIndex(COLUMN_PROPORTIONS)));
            cursor.moveToNext();
        }
        db.close();
        return entries;
    }

    public ArrayList<String> getLiquidByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = null;
        try {
            query = "select * from " + DATABASE_TABLE + " where " + COLUMN_LIQUIDNAME + " = " + "\""+name+"\";";
        } catch (RuntimeException e) {
            Log.e("SQL Exception", e.toString());
        }
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> dbRow = new ArrayList<String>();

        while(!cursor.isAfterLast()) {
            dbRow.add(cursor.getString(cursor.getColumnIndex(COLUMN_LIQUIDNAME)));
            dbRow.add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPONENTS)));
            dbRow.add(cursor.getString(cursor.getColumnIndex(COLUMN_PROPORTIONS)));
            cursor.moveToNext();
        }
        db.close();
        return dbRow;
    }

    public ArrayList<String> getLiquidNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> dbRes = new ArrayList<String>();

        while(!cursor.isAfterLast()) {
            dbRes.add(cursor.getString(cursor.getColumnIndex(COLUMN_LIQUIDNAME)));
            cursor.moveToNext();
        }
        db.close();
        return dbRes;
    }
}
