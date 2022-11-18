/**
 * In Class 12
 * Database.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    final String TAG = "test";
    final static String DB_NAME = "grades.db";
    final static int DATABASE_VERSION = 1;


    // creating a constructor for our database handler.
    public Database(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, "onOpen: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        GradesTable.onCreate(sqLiteDatabase);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        GradesTable.onUpgrade(sqLiteDatabase, i, i1);
        GradesTable.onCreate(sqLiteDatabase);
        Log.d(TAG, "onUpgrade: ");
    }
}
