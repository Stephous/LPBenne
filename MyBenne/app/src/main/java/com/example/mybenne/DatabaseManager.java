package com.example.mybenne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Benne.db";

    public static final String USER_KEY = "id";
    public static final String USER_NOM = "nom";
    public static final String USER_PRENOM = "prenom";
    public static final String USER_TEL = "telephone";
    public static final String USER_FONCTION = "fonction";

    public static final String TABLE_NAME = "utilisateur";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_NOM + " TEXT, " +
                    USER_PRENOM + "TEXT, " +
                    USER_TEL + "TEXT, " +
                    USER_FONCTION + "TEXT);";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }

    public boolean insertData(String nom, String prenom, String telephone, String fonction)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(USER_NOM, nom);
        contentValues.put(USER_PRENOM, prenom);
        contentValues.put(USER_TEL, telephone);
        contentValues.put(USER_FONCTION, fonction);
        long result= (db.insert(TABLE_NAME, null, contentValues));
        if(result== -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String fonction_recu) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result=db.rawQuery("SELECT nom, prenom, telephone FROM " + TABLE_NAME + " WHERE " + USER_FONCTION + "=" + fonction_recu + " ORDER BY " + USER_KEY + " DESC LIMIT 1;", null);
        return result;
    }

}