package edu.uncc.inclass12;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class GradesDAO {
    private SQLiteDatabase db;

    public GradesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Grade grade) {
        ContentValues values = new ContentValues();
        return 0;
    }

    public boolean delete(Grade grade) {
        return false;
    }

    public boolean getGrade(Grade grade) {
        return false;
    }
}
