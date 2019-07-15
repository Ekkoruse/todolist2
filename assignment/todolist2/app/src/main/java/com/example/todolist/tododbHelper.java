package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class tododbHelper extends SQLiteOpenHelper {
    private  static  final String DB_NAME = "todo.db";
    private  static  final int DB_VERSION = 2;
    public static final String SQL_CREATE_NOTES =
            "CREATE TABLE " + "note"
                    + "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "date" + " INTEGER, "
                    + "state" + " INTEGER, "
                    + "content" + " TEXT, "
                    + "priority" + " INTEGER)";

    public  static  final  String SQL_ADD_PRIORITY_COLUMN=
            "ALTER TABLE" + " note"
                    +" ADD"
                    + " priority" +" INTEGER";

    public tododbHelper(Context context) {
        super(context,DB_NAME ,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1:
                    sqLiteDatabase.execSQL(SQL_ADD_PRIORITY_COLUMN);
                    break;
            }
        }
    }
}
