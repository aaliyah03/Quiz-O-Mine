package com.example.android.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    static int DB_VERSION=1;
    private static final String DB_NAME = "PlayersDB";
    private static final String TABLE_NAME = "Players";

    //declaring the column names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "Name";
    private static final String KEY_SCORE = "Score";

    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT DEFAULT 'Anonymous', "
                + KEY_SCORE + " INTEGER DEFAULT 0" + ")";

        //Create table query executed in sqlite
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addPlayer(PlayerDB player)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Content values use KEY-VALUE pair concept
        ContentValues values = new ContentValues();
        values.put(KEY_ID, player.getID());
        values.put(KEY_NAME, player.getName());
        values.put(KEY_SCORE, player.getScore());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<PlayerDB> getPlayers()
    {
        List<PlayerDB> playerList = new ArrayList<PlayerDB>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+ " LIMIT 3;",null);//first parameter of this method will be query
        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext())
            {
                PlayerDB p = new PlayerDB(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2));

                playerList.add(p);
            } while (cursor.moveToNext());
        }
        return playerList;
    }
}



    

