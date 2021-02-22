package com.slisenko.education.comparator;

import com.slisenko.education.student.Student;

import java.util.Comparator;

/**
 * A class that implements the Comparator interface for objects of the Student
 * class by the course field
 */
public class StudentCourseComparator implements Comparator<Student> {

    @Override
    public int compare(Student st1, Student st2) {
        return st1.getCourse() - st2.getCourse();
    }
}
