package com.example.andresarango.memeit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andresarango.memeit.modello.MemeURI;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by leighdouglas on 1/21/17.
 */

public class MemeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userMemeDatabase.db";
    private static final int DATABASE_VERSION = 1;
    public static MemeDatabaseHelper instance;

    private MemeDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static synchronized MemeDatabaseHelper getInstance(Context context) {

        if (instance == null) {
            instance = new MemeDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    static {
        cupboard().register(MemeURI.class);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }

    public void addMeme(MemeURI meme, SQLiteDatabase db) {
        cupboard().withDatabase(db).put(meme);
    }

    public SQLiteDatabase getSQLiteDatabase(MemeDatabaseHelper db){
        return db.getWritableDatabase();

    }
}
