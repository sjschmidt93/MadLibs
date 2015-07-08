package com.sjschmd2.madlibs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MadLibDB {

    public static final String ROWID = "_id";
    public static final String NAME_GIVEN = "madlib_name_given";
    public static final String NAME = "madlib_name";
    public static final String STORY = "madlib_story";

    private static final String DATABASE_NAME = "MadLibDB";
    private static final String DATABASE_TABLE = "savedMadLibs";
    private static final int DATABASE_VERSION = 1;

    private DBHelper myDBHelper;
    private final Context myContext;
    private SQLiteDatabase myDB;

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                "CREATE TABLE " + DATABASE_TABLE + "(" +
                    ROWID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_GIVEN + "TEXT NOT NULL, " +
                    NAME + " TEXT NOT NULL"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public MadLibDB(Context c){
        myContext = c;
    }

    public MadLibDB open(){
        myDBHelper = new DBHelper(myContext);
        myDB  = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myDBHelper.close();
    }

}
