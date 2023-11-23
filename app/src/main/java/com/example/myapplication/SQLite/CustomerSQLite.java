package com.example.myapplication.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Helper.SQLiteHelper;
import com.example.myapplication.Model.Customer;

public class CustomerSQLite {
    private SQLiteHelper helper;

    public CustomerSQLite(Context context) {
        helper = new SQLiteHelper(context, "projects.sqlite", null, 1);
        helper.queryData("CREATE TABLE IF NOT EXISTS Customer (id INTEGER PRIMARY KEY, email TEXT, name TEXT, password TEXT, profilePicUrl TEXT, uID TEXT, token TEXT, idAdmin INTEGER)");
        addCustomerTest();
        helper.queryData("CREATE TABLE IF NOT EXISTS CustomerAct (id INTEGER PRIMARY KEY, email TEXT, name TEXT, password TEXT, profilePicUrl TEXT, uID TEXT, token TEXT, idAdmin INTEGER)");
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM Customer WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Customer isPasswordCorrect(String email1, String password1) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM Customer WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email1, password1});
        Customer customer = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex("id");
            int emailIndex = cursor.getColumnIndex("email");
            int nameIndex = cursor.getColumnIndex("name");
            int passwordIndex = cursor.getColumnIndex("password");
            int profilePicUrlIndex = cursor.getColumnIndex("profilePicUrl");
            int uIDIndex = cursor.getColumnIndex("uID");
            int tokenIndex = cursor.getColumnIndex("token");
            int idAdminIndex = cursor.getColumnIndex("idAdmin");

            int id = cursor.getInt(idIndex);
            String email = cursor.getString(emailIndex);
            String name = cursor.getString(nameIndex);
            String password = cursor.getString(passwordIndex);
            String profilePicUrl = cursor.getString(profilePicUrlIndex);
            String uID = cursor.getString(uIDIndex);
            String token = cursor.getString(tokenIndex);
            int idAdmin = cursor.getInt(idAdminIndex);

            customer = new Customer(id, email, name, password, profilePicUrl, uID, token, idAdmin);
        }

        cursor.close();
        return customer;
    }
    public boolean isAdmin(String email) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM Customer WHERE email = ? AND idAdmin = 1";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean isAdmin = cursor.getCount() > 0;
        cursor.close();
        return isAdmin;
    }

    public void addCustomer(Customer customer) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // Xóa hết dữ liệu trong bảng "CustomerAct"
        db.delete("CustomerAct", null, null);

        ContentValues values = new ContentValues();
        values.put("email", customer.getEmail());
        values.put("name", customer.getName());
        values.put("password", customer.getPassword());
        values.put("profilePicUrl", customer.getProfilePicUrl());
        values.put("uID", customer.getuID());
        values.put("token", customer.getToken());
        values.put("idAdmin", customer.getIdAdmin());

        db.insert("CustomerAct", null, values);
        db.close();
    }
    public void deleteUserWasLogin() {
        SQLiteDatabase db = helper.getWritableDatabase();

        // Xóa hết dữ liệu trong bảng "CustomerAct"
        db.delete("CustomerAct", null, null);
    }
    public void addCustomerTest() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("Customer", null, null);
        ContentValues values = new ContentValues();

        values.put("email", "4tiensau@gmail.com");
        values.put("name", "John Doe");
        values.put("password", "1234");
        values.put("profilePicUrl", "https://example.com/profile-pic-1.jpg");
        values.put("uID", "123456789");
        values.put("token", "abcdefg");
        values.put("idAdmin", 0);
        db.insert("Customer", null, values);

        values.clear();
        values.put("email", "example2@example.com");
        values.put("name", "Jane Smith");
        values.put("password", "password456");
        values.put("profilePicUrl", "https://example.com/profile-pic-2.jpg");
        values.put("uID", "987654321");
        values.put("token", "hijklmn");
        values.put("idAdmin", 1);
        db.insert("Customer", null, values);

        db.close();
    }


    public Customer customerWasLogin() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM CustomerAct ";
        Cursor cursor = db.rawQuery(query, null);
        Customer customer = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int idIndex = cursor.getColumnIndex("id");
            int emailIndex = cursor.getColumnIndex("email");
            int nameIndex = cursor.getColumnIndex("name");
            int passwordIndex = cursor.getColumnIndex("password");
            int profilePicUrlIndex = cursor.getColumnIndex("profilePicUrl");
            int uIDIndex = cursor.getColumnIndex("uID");
            int tokenIndex = cursor.getColumnIndex("token");
            int idAdminIndex = cursor.getColumnIndex("idAdmin");

            int id = cursor.getInt(idIndex);
            String email = cursor.getString(emailIndex);
            String name = cursor.getString(nameIndex);
            String password = cursor.getString(passwordIndex);
            String profilePicUrl = cursor.getString(profilePicUrlIndex);
            String uID = cursor.getString(uIDIndex);
            String token = cursor.getString(tokenIndex);
            int idAdmin = cursor.getInt(idAdminIndex);

            customer = new Customer(id, email, name, password, profilePicUrl, uID, token, idAdmin);
        }

        cursor.close();
        return customer;
    }
}