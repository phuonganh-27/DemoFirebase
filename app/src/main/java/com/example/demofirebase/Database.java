package com.example.demofirebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";

    public Database(Context context) {super(context, DATABASE_NAME, null, 1);}

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table Users(id integer primary key AUTOINCREMENT, email text, password text)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Users   ");
        onCreate(db);
    }

    public void insert(){
        SQLiteDatabase db = this.getWritableDatabase();

    }

    public boolean insertUsers(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("Users", null, contentValues);
        return true;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Accounts where username=? and password=?", new String[]{username, password});
        if(res.getCount()>0)
            return true;
        else return false;
    }

    public void updatePassword(String email, String newPasswordHash) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password_hash", newPasswordHash);

        // Cập nhật dòng có email tương ứng
        db.update("users", values, "email = ?", new String[]{email});

        // Đóng kết nối
        db.close();
    }

}
