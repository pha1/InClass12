package edu.uncc.inclass12;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GradesDAO {
    private SQLiteDatabase db;

    public GradesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Grade grade) {
        ContentValues values = new ContentValues();
        values.put(GradesTable.COURSE_NUM_COL, grade.course_number);
        values.put(GradesTable.COURSE_NAME_COL, grade.course_name);
        values.put(GradesTable.CREDIT_NUM_COL, grade.credit_hours);
        values.put(GradesTable.GRADE_COL, grade.letter_grade);

        return db.insert(GradesTable.TABLE_NAME, null, values);
    }

    public boolean delete(Grade grade) {
        return db.delete(GradesTable.TABLE_NAME, GradesTable.ID_COL + " = ?", new String[]{String.valueOf(grade.id)}) > 0;
    }

    public Grade getGrade(long id) {
        Grade grade = null;
        Cursor cursor = db.query(GradesTable.TABLE_NAME,
                new String[]{GradesTable.ID_COL, GradesTable.COURSE_NUM_COL, GradesTable.COURSE_NAME_COL, GradesTable.CREDIT_NUM_COL, GradesTable.GRADE_COL},
                GradesTable.ID_COL + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor.moveToFirst()) {
            grade = buildGradeFromCursor(cursor);
        }
        return grade;
    }

    public ArrayList<Grade> getAll() {
        ArrayList<Grade> grades = new ArrayList<>();

        Cursor cursor = db.query(GradesTable.TABLE_NAME,
                new String[]{GradesTable.ID_COL, GradesTable.COURSE_NUM_COL, GradesTable.COURSE_NAME_COL, GradesTable.CREDIT_NUM_COL, GradesTable.GRADE_COL},
                null, null, null, null, null);

        while(cursor.moveToNext()) {
            Grade grade = buildGradeFromCursor(cursor);
            grades.add(grade);
        }

        return grades;
    }

    public Grade buildGradeFromCursor(Cursor cursor){
        Grade grade = new Grade();
        grade.setId(cursor.getLong(0));
        grade.setCourse_number(cursor.getString(1));
        grade.setCourse_name(cursor.getString(2));
        grade.setCredit_hours(cursor.getDouble(3));
        grade.setLetter_grade(cursor.getString(4));

        return grade;
    }
}
