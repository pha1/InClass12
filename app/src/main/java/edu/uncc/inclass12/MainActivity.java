/**
 * In Class 12
 * MainActivity.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GradesFragment.GradesFragmentListener, AddCourseFragment.AddCourseFragmentListener, GradeRecyclerViewAdapter.IGradeRecycler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new GradesFragment(), "Grades")
                .commit();
    }

    /**
     * This goes to Add Course Fragment
     */
    @Override
    public void addNewCourse() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddCourseFragment(), "Add Course")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToGrades() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * This method deletes the grade from the Database
     * Implemented by the RecyclerView's interface
     * @param grade
     */
    @Override
    public void delete(Grade grade) {
        // Remove Grade from Database
        DatabaseManager dm;
        dm = new DatabaseManager(this);
        dm.getGradesDAO().delete(grade);

        // Get the Grades Fragment
        GradesFragment gradesFragment = (GradesFragment) getSupportFragmentManager().findFragmentByTag("Grades");
        // Update the RecyclerView List and Recalculate the GPA when deleting a Course Grade
        gradesFragment.notifyDataSetChanged();
        gradesFragment.calculateGpa();
    }
}