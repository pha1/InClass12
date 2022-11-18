/**
 * In Class 12
 * DatabaseManager.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    Context mContext;
    SQLiteDatabase db;
    Database database;

    public GradesDAO getGradesDAO() {
        return gradesDAO;
    }

    GradesDAO gradesDAO;

    public DatabaseManager(Context context) {
        this.mContext = context;
        database = new Database(mContext);
        db = database.getWritableDatabase();
        gradesDAO = new GradesDAO(db);

    }
}
