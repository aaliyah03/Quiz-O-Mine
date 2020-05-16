package com.example.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Player.db";
    public static final String TABLE_NAME = "Player";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SCORE";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    // creating a table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+ "(ID integer primary key autoincrement," +
                "NAME varchar(30), SCORE integer);");
    }

    // rremoving a previous version of a table if already existing
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME+";");
        onCreate(db);
    }

    public boolean insertPlayer(String name, String score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //adding data as per columns
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, score);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updatePlayer(String name, String score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,score);
        db.update(TABLE_NAME, contentValues, "NAME = ?",new String[] {name});
        return true;
    }

    // following functions retrieve data from the DB

    public Cursor getPlayerScore(String playerName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+ " where "+
                COL_2+ "=" + "\""+playerName+ "\""+";", null);
        return res;
    }

    public Cursor getTopScores()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+ " order by "+
                COL_3+" desc limit 5;", null);
        return res;
    }

    public List<String> getNames()
    {
        List<String> names= new ArrayList<String>();
        String selectQuery= "select * from "+TABLE_NAME+";";
        SQLiteDatabase db = this.getReadableDatabase();

        //using cursor to iterate over each row
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do {
                names.add(cursor.getString(1));
            }
            while(cursor.moveToNext());
        }

        return names;
    }
}
