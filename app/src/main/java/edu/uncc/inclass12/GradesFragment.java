/**
 * In Class 12
 * GradesFragment.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.uncc.inclass12.databinding.FragmentGradesBinding;

public class GradesFragment extends Fragment {

    final String TAG = "test";

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    FragmentGradesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    DatabaseManager dm;
    GradeRecyclerViewAdapter adapter;
    ArrayList<Grade> mGrades = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dm = new DatabaseManager(getContext());

        getData();
        calculateGpa();

        Log.d(TAG, "onViewCreated: " + mGrades);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GradeRecyclerViewAdapter(mGrades, (GradeRecyclerViewAdapter.IGradeRecycler) getContext());
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                // Navigate to Add Course screen
                mListener.addNewCourse();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof GradesFragmentListener) {
            mListener = (GradesFragmentListener) context;
        }
    }

    GradesFragmentListener mListener;

    public interface GradesFragmentListener{
        void addNewCourse();
    }

    public void calculateGpa() {
        double gpa;
        double hours;
        double total_grade_points = 0.0;
        double credit_points;
        double total_hours = 0.0;

        for (int i = 0; i < mGrades.size(); i++) {
            // Hours
            hours = mGrades.get(i).credit_hours;

            // Check which letter grade/point worth
            switch (mGrades.get(i).letter_grade) {
                case "A":
                    credit_points = 4.0;
                    break;
                case "B":
                    credit_points = 3.0;
                    break;
                case "C":
                    credit_points = 2.0;
                    break;
                case "D":
                    credit_points = 1.0;
                    break;
                default:
                    credit_points = 0.0;
                    break;
            }
            // Get the total grade points (credit hours * Letter Grade)
            total_grade_points += hours * credit_points;
            total_hours += hours;
        }
        Log.d(TAG, "onEvent: " + mGrades.size());
        if (total_hours > 0.0){
            gpa = total_grade_points/total_hours;
        } else {
            gpa = 4.0;
        }
        // GPA = Total Grade Points/Total Credit Hours

        binding.textView2.setText("Hours: " + total_hours);
        binding.textViewGPA.setText("GPA: " + String.format("%.2f", gpa));
    }

    public void getData() {
        mGrades = dm.getGradesDAO().getAll();
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }
}