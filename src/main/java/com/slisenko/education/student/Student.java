package com.slisenko.education.student;

import java.util.Objects;

/**
 * Class with information about students
 */
public class Student {

    private String name;
    private String surname;
    private int year;
    private String faculty;
    private int course;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return year == student.year &&
                course == student.course &&
                name.equals(student.name) &&
                surname.equals(student.surname) &&
                faculty.equals(student.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, year, faculty, course);
    }
}
