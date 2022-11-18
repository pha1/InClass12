/**
 * In Class 12
 * GradesDAO.java
 * Phi Ha
 * Srinath Dittakavi
 */

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

    /**
     * Store the grade into the database
     * @param grade
     * @return
     */
    public long save(Grade grade) {
        // New ContentValues
        ContentValues values = new ContentValues();

        // Put the values
        values.put(GradesTable.COURSE_NUM_COL, grade.course_number);
        values.put(GradesTable.COURSE_NAME_COL, grade.course_name);
        values.put(GradesTable.CREDIT_NUM_COL, grade.credit_hours);
        values.put(GradesTable.GRADE_COL, grade.letter_grade);

        return db.insert(GradesTable.TABLE_NAME, null, values);
    }

    /**
     * Delete a row from the database
     * The row containing the data to the given Grade
     * @param grade The grade to be deleted
     * @return A boolean if any data was deleted
     */
    public boolean delete(Grade grade) {
        // db.delete returns the number of rows deleted
        // >0 is to see if anything was deleted
        return db.delete(GradesTable.TABLE_NAME, GradesTable.ID_COL + " = ?", new String[]{String.valueOf(grade.id)}) > 0;
    }

    /**
     * Gets the row of data and builds that row of data into a Grade Object
     * @param id The id of the row where the grade is stored
     * @return Grade Object
     */
    public Grade getGrade(long id) {
        Grade grade = null;
        // Cursor points to the row where the data with row id is stored
        Cursor cursor = db.query(GradesTable.TABLE_NAME,
                new String[]{GradesTable.ID_COL, GradesTable.COURSE_NUM_COL, GradesTable.COURSE_NAME_COL, GradesTable.CREDIT_NUM_COL, GradesTable.GRADE_COL},
                GradesTable.ID_COL + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor.moveToFirst()) {
            grade = buildGradeFromCursor(cursor);
        }
        return grade;
    }

    /**
     * Gets all the rows from the Table and returns an ArrayList of Grades
     * @return ArrayList of Grades
     */
    public ArrayList<Grade> getAll() {
        ArrayList<Grade> grades = new ArrayList<>();

        Cursor cursor = db.query(GradesTable.TABLE_NAME,
                new String[]{GradesTable.ID_COL, GradesTable.COURSE_NUM_COL, GradesTable.COURSE_NAME_COL, GradesTable.CREDIT_NUM_COL, GradesTable.GRADE_COL},
                null, null, null, null, null);

        // While there is another row that is not empty
        while(cursor.moveToNext()) {
            // Build the grade from the cursor and add it to the ArrayList
            Grade grade = buildGradeFromCursor(cursor);
            grades.add(grade);
        }
        return grades;
    }

    /**
     * Method that builds the Grade from a cursor
     * @param cursor Object that holds the information from the Database row
     * @return Grade Object
     */
    public Grade buildGradeFromCursor(Cursor cursor){
        // New Grade
        Grade grade = new Grade();

        // Set its values
        grade.setId(cursor.getLong(0));
        grade.setCourse_number(cursor.getString(1));
        grade.setCourse_name(cursor.getString(2));
        grade.setCredit_hours(cursor.getDouble(3));
        grade.setLetter_grade(cursor.getString(4));

        return grade;
    }
}
