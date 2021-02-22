package com.slisenko.education.student;

/**
 * Class with information about students
 */
public class Student {

    private String name;
    private String surname;
    private int year;
    private String faculty;
    private int course;

    public Student() {
        name = "";
        surname = "";
        year = 0;
        faculty = "";
        course = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        char comma = ',';

        sb.append('(')
                .append(getName())
                .append(comma)
                .append(getSurname())
                .append(comma)
                .append(getYear())
                .append(comma)
                .append(getFaculty())
                .append(comma)
                .append(getCourse())
                .append(')');

        return sb.toString();
    }
}
