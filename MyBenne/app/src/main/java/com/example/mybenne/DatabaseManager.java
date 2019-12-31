package com.example.mybenne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
                    USER_PRENOM + " TEXT, " +
                    USER_TEL + " TEXT, " +
                    USER_FONCTION + " TEXT);";

    public static final String BENNE_KEY = "id";
    public static final String BENNE_QR_CODE = "qrcode";
    public static final String BENNE_POSITION = "position";

    public static final String TABLE_BENNE = "benne";

    public static final String TABLE_CREATE_BENNE =
            "CREATE TABLE " + TABLE_BENNE + " (" +
                    BENNE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BENNE_QR_CODE + " VARCHAR, " +
                    BENNE_POSITION + " VARCHAR);";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_BENNE);
    }

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    public static final String TABLE_DROP_BENNE = "DROP TABLE IF EXISTS " + TABLE_BENNE + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        db.execSQL(TABLE_DROP_BENNE);
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

    public boolean insertDataBenne(String id_benne,String qr_code, String position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(BENNE_KEY, id_benne);
        contentValues.put(BENNE_QR_CODE, qr_code);
        contentValues.put(BENNE_POSITION, position);
        long result= (db.insert(TABLE_BENNE, null, contentValues));
        if(result== -1)
            return false;
        else
            return true;
    }

    public Cursor getDataNom(String fonction_recu) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultNom=db.rawQuery("SELECT nom, prenom, telephone FROM " + TABLE_NAME + " WHERE " + USER_FONCTION + "=" + "\'" + fonction_recu + "\'" + " ORDER BY " + USER_KEY + " DESC LIMIT 1;", null);
        return resultNom;
    }
    public String[] getDataBenne(String fonction_recu) {

        ArrayList<String> columnArray1 = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor resultBenne=db.rawQuery("SELECT position FROM " + TABLE_BENNE + " WHERE " + BENNE_KEY + "=" + fonction_recu , null);

        columnArray1.add(resultBenne.getString(1));

        String[] colStrArr1 = columnArray1.toArray(new String[columnArray1.size()]);
        return colStrArr1;
    }
}