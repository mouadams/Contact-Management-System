package com.example.contact.helpers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contact.model.Contact;

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
        super(context, "Contact.db", null, 2);

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

    public void addContact(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_PHONE, phone);
        db.insert(TABLE_CONTACT, null, values);
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COL_ID + ", " + COL_NAME + ", " + COL_PHONE + " FROM " + TABLE_CONTACT,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_ID)
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME)
                );

                String phone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PHONE)
                );

                contacts.add(new Contact(id, name, phone));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return contacts;
    }
    public Contact getContactById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_CONTACT,
                new String[]{COL_ID, COL_NAME, COL_PHONE},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        Contact contact = null;
        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contact = new Contact(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE))
                );
            }
            cursor.close();
        }
        return contact;
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

        return rows > 0;
    }


}