/**
 * In Class 12
 * Grade.java
 * Phi Ha
 * Srinath Dittakavi
 */

package edu.uncc.inclass12;

public class Grade {
    long id;
    String course_number, course_name, letter_grade;
    double credit_hours;

    public Grade() {}

    public Grade(String course_number, String course_name, String letter_grade, double credit_hours) {
        this.course_number = course_number;
        this.course_name = course_name;
        this.letter_grade = letter_grade;
        this.credit_hours = credit_hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLetter_grade() {
        return letter_grade;
    }

    public void setLetter_grade(String letter_grade) {
        this.letter_grade = letter_grade;
    }

    public double getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(double credit_hours) {
        this.credit_hours = credit_hours;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", course_number='" + course_number + '\'' +
                ", course_name='" + course_name + '\'' +
                ", letter_grade='" + letter_grade + '\'' +
                ", credit_hours=" + credit_hours +
                '}';
    }
}
