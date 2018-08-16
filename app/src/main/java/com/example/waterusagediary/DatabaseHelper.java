package com.example.waterusagediary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ApplicationDatabaseDB.db";

    public static final String TABLE_NAME = "DiaryEntries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACTIVITYNAME = "activityname";
    public static final String COLUMN_LITRES = "amount_in_Litres";
    public static final String COLUMN_DATE = "date";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        String q ="CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ACTIVITYNAME + " TEXT, " +
                COLUMN_LITRES + " DOUBLE, " +
                COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");";


        db.execSQL(q);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
        //dro
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //onCreate(db);
    }

    public void addEntry(Entry a){
        String activityName =a.getName();
        double amountInLitres=a.getAmount();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITYNAME,activityName);
        values.put(COLUMN_LITRES,amountInLitres);


        SQLiteDatabase db = getWritableDatabase(); //get my database
        db.insert(TABLE_NAME, null, values); //INSERTING MY VALUES USING A WEIRD HASHMAP
        db.close(); //close database

    }

    public ArrayList<Entry> getEntries(){
        ArrayList<Entry> entries = new ArrayList<>();
        String q = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        Cursor results = db.rawQuery(q, null);
        results.moveToFirst();
        while(!results.isAfterLast()){
            if (results.getString(results.getColumnIndex(COLUMN_ACTIVITYNAME)) != null) {
                String activity = results.getString(results.getColumnIndex(COLUMN_ACTIVITYNAME));
                double litres = results.getDouble(results.getColumnIndex(COLUMN_LITRES));
                String date = results.getString(results.getColumnIndex(COLUMN_DATE));


                entries.add(new Entry(activity,litres, date.split(" ")[0]));
            }
            results.moveToNext();
        }

        return entries;
    }
}
