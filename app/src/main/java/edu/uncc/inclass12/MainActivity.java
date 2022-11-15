/**
 * In Class 12
 * MainActivity.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GradesFragment.GradesFragmentListener, AddCourseFragment.AddCourseFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new GradesFragment())
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
}