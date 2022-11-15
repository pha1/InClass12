package edu.uncc.inclass12;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "courses";
    private static final String TABLE_NAME = "courses";
    private static final String ID_COL = "id";
    private static final String GRADE_COL = "letter_grade";
    private static final String COURSE_NUM_COL = "course_number";
    private static final String COURSE_NAME_COL = "course_name";
    private static final double CREDIT_NAME_COL = Double.parseDouble("credit_hour");

    // creating a constructor for our database handler.
    public Database(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GRADE_COL + " TEXT,"
                + COURSE_NUM_COL + " TEXT,"
                + COURSE_NAME_COL + " TEXT,"
                + COURSE_NAME_COL + " TEXT,"
                + CREDIT_NAME_COL + " TEXT)";


        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
