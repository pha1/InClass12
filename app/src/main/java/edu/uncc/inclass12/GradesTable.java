package edu.uncc.inclass12;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GradesTable {

    private static final String TABLE_NAME = "grades";
    private static final String ID_COL = "id";
    private static final String GRADE_COL = "letter_grade";
    private static final String COURSE_NUM_COL = "course_number";
    private static final String COURSE_NAME_COL = "course_name";
    private static final double CREDIT_NAME_COL = Double.parseDouble("credit_hour");

    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + GradesTable.TABLE_NAME + " (");
        sb.append(ID_COL + " integer primary key autoincrement, ");
        sb.append(COURSE_NUM_COL + " text not null ,");
        sb.append(COURSE_NAME_COL + " text not null,");
        sb.append(CREDIT_NAME_COL + " text not null,");
        sb.append(GRADE_COL + " text not null);");

        try {
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + GradesTable.TABLE_NAME);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
