package com.example.myapplication.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Helper.SQLiteHelper;

public class CustomerSQLite {
    private SQLiteHelper helper;

    public CustomerSQLite(Context context) {
        helper = new SQLiteHelper(context, "projects.sqlite", null, 1);
        helper.queryData("CREATE TABLE IF NOT EXISTS Customer (id INTEGER PRIMARY KEY, email TEXT, name TEXT, password TEXT, profilePicUrl TEXT, uID TEXT, token TEXT, idAdmin INTEGER)");
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM Customer WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean isPasswordCorrect(String email, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM Customer WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean correct = cursor.getCount() > 0;
        cursor.close();
        return correct;
    }

    public boolean isAdmin(String email) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM Customer WHERE email = ? AND idAdmin = 1";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean isAdmin = cursor.getCount() > 0;
        cursor.close();
        return isAdmin;
    }

    public void addCustomer(String email, String name, String password, String profilePicUrl, String uID, String token, int idAdmin) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("name", name);
        values.put("password", password);
        values.put("profilePicUrl", profilePicUrl);
        values.put("uID", uID);
        values.put("token", token);
        values.put("idAdmin", idAdmin);
        db.insert("Customer", null, values);
        db.close();
    }
}