package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{

    public DBHelper(Context context) {
        super(context, "TaskData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table tasks( _id INTEGER PRIMARY KEY AUTOINCREMENT ,heading TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop Table if exists tasks");
    }
    public boolean InsertTaskToList(String heading, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("heading", heading);
        contentValues.put("description",description);
        long result = db.insert("tasks", null, contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean deleteTask(String Heading, String Description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("tasks","heading = ? and description = ?",new String[]{Heading, Description});
        if(result == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<TaskData> getAll()
    {
        ArrayList<TaskData> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select heading, description from tasks",null);
        while(cursor.moveToNext())
        {
            String Heading = cursor.getString(0);
            String Description = cursor.getString(1);
            TaskData tk = new TaskData(Heading, Description);
            arrayList.add(tk);
        }
        cursor.close();
        return arrayList;
    }
}
