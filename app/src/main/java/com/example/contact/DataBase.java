package com.example.contact;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    // public static ArrayList<String> ContactArray = new ArrayList<>();

    private static DataBase instance;

    public static final String TABLE_CONTACT = "Contact";


    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";


    public static synchronized DataBase getInstance(Context context){
        if (instance == null){
            instance = new DataBase(context.getApplicationContext());
        }
        return instance;
    }

    public DataBase(Context context) {
        super(context, "Contact.db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db ) {
        String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACT + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " TEXT NOT NULL, " +
                        COL_PHONE + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contact");
        onCreate(db);
    }

    public ArrayList<String> getAllContacts() {
        ArrayList<String> contacts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " +  "  " + COL_NAME + ", " + COL_PHONE + " FROM " + TABLE_CONTACT,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                //int id = cursor.getInt(0);
                String name = String.valueOf(cursor.getColumnIndex("name"));
                String phone = String.valueOf(cursor.getColumnIndex("phone"));
                contacts.add(": " + name + " - " + phone);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return contacts;
    }

    public boolean updateContact(int id, String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_PHONE, phone);

        int rows = db.update(
                TABLE_CONTACT,
                values,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        return rows > 0;
    }

    // DELETE contact by ID
    public boolean deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rows = db.delete(
                TABLE_CONTACT,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        return rows > 0;
    }


}
