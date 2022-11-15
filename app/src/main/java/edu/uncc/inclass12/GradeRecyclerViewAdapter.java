/**
 * In Class 12
 * GradeRecyclerViewAdapter.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GradeRecyclerViewAdapter extends RecyclerView.Adapter<GradeRecyclerViewAdapter.GradeViewHolder> {

    final String TAG = "test";
    ArrayList<Grade> grades;
    IGradeRecycler iGradeRecycler;

    public GradeRecyclerViewAdapter(ArrayList<Grade> data, IGradeRecycler iGradeRecycler){
        this.grades = data;
        this.iGradeRecycler = iGradeRecycler;
    }

    public interface IGradeRecycler {
        void delete(Grade grade);
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_row_item, parent, false);
        GradeViewHolder gradeViewHolder = new GradeViewHolder(view, iGradeRecycler);
        return gradeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        Grade grade = grades.get(position);

        holder.grades = grades;
        holder.grade = grade;
        holder.position = position;

        holder.courseNumber.setText(grade.course_number);
        holder.courseName.setText(grade.course_name);
        holder.courseHours.setText(String.valueOf(grade.credit_hours));
        holder.courseLetterGrade.setText(grade.letter_grade);
    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    public static class GradeViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        IGradeRecycler iGradeRecycler;
        ImageView delete;

        TextView courseNumber;
        TextView courseName;
        TextView courseHours;
        TextView courseLetterGrade;

        int position;
        ArrayList<Grade> grades;
        Grade grade;

        public GradeViewHolder(@NonNull View itemView, IGradeRecycler iGradeRecycler) {
            super(itemView);

            rootView = itemView;
            this.iGradeRecycler = iGradeRecycler;

            delete = itemView.findViewById(R.id.imageViewDelete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO Delete from database
                    grade = grades.get(position);
                    iGradeRecycler.delete(grade);
                }
            });
        }
    }
}