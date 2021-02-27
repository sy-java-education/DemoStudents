package com.slisenko.education.service;

import com.slisenko.education.comparator.StudentCourseComparator;
import com.slisenko.education.comparator.StudentFacultyComparator;
import com.slisenko.education.comparator.StudentYearComparator;
import com.slisenko.education.student.Student;
import com.slisenko.education.xml.IConstStudent;

import java.util.*;

/**
 * A class with different sorting implementations for the list of objects of
 * the Student class
 */
public class StudentService {

    private static Map<IConstStudent.Field, Comparator<Student>> map = new HashMap<>();

    static {
        map.put(IConstStudent.Field.YEAR, new StudentYearComparator());
        map.put(IConstStudent.Field.FACULTY, new StudentFacultyComparator());
        map.put(IConstStudent.Field.COURSE, new StudentCourseComparator());
    }

    /**
     * Sorting the list of objects of the Student class
     * @param list List of objects of the Student class
     * @param field The field to sort by
     */
    public static void sort(List<Student> list, String field) {

        switch (field) {
            case IConstStudent.TAG_YEAR:
                Collections.sort(list, new StudentYearComparator());
                break;
            case IConstStudent.TAG_FACULTY:
                Collections.sort(list, new StudentFacultyComparator());
                break;
            case IConstStudent.TAG_COURSE:
                Collections.sort(list, new StudentCourseComparator());
                break;
        }
    }

    /**
     * Sorting the list of objects of the Student class (release 2)
     * @param list List of objects of the Student class
     * @param field The field to sort by
     */
    public static void sort2(List<Student> list, String field) {

        Comparator<Student> comp;
        switch (field) {
            case IConstStudent.TAG_YEAR:
                comp = new StudentYearComparator();
                break;
            case IConstStudent.TAG_FACULTY:
                comp = new StudentFacultyComparator();
                break;
            case IConstStudent.TAG_COURSE:
                comp = new StudentCourseComparator();
                break;
            default: return;
        }
        Collections.sort(list, comp);
    }

    /**
     * Sorting the list of objects of the Student class with the use of
     * a class that implements the interface Map
     * @param list List of objects of the Student class
     * @param field The field to sort by
     */
    //public static void sort3(List<Student> list, String field) {
    public static void sort3(List<Student> list, IConstStudent.Field field) {

        Comparator<Student> comp = map.get(field);

        if (comp != null) {
            Collections.sort(list, comp);
        } else {
            throw new IllegalArgumentException("Illegal parameter <" + field + "> for sorting");
        }
    }
}
