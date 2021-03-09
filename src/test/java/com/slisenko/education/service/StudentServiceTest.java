package com.slisenko.education.service;

import com.slisenko.education.student.Student;
import com.slisenko.education.xml.IConstStudent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {

    private static List<Student> listStudent = new ArrayList<>();
    private static List<Student> actual = new ArrayList<>();

    @BeforeClass
    public static void setUp() {

        Student student;

        for (int i = 0; i < 3; i++) {

            // Create an unsorted list
            student = new Student();
            student.setName("Name" + (3 - i));
            student.setSurname("Surname" + (3 - i));
            student.setYear(1990  + (3 - i));
            student.setFaculty("Faculty" + (3 - i));
            student.setCourse(3 - i);
            listStudent.add(student);

            // Create a sorted list
            student = new Student();
            student.setName("Name" + (i + 1));
            student.setSurname("Surname" + (i + 1));
            student.setYear(1990  + (i + 1));
            student.setFaculty("Faculty" + (i + 1));
            student.setCourse(i + 1);
            actual.add(student);
        }
    }

    @Test
    public void sort() {

        List<Student> expected;

        expected = new ArrayList<>(listStudent);
        StudentService.sort(expected, IConstStudent.TAG_YEAR);
        Assert.assertEquals("Error sorting by year", expected, actual);

        expected = new ArrayList<>(listStudent);
        StudentService.sort(expected, IConstStudent.TAG_FACULTY);
        Assert.assertEquals("Error sorting by faculty", expected, actual);

        expected = new ArrayList<>(listStudent);
        StudentService.sort(expected, IConstStudent.TAG_COURSE);
        Assert.assertEquals("Error sorting by course", expected, actual);
    }

    @Test
    public void sort2() {

        List<Student> expected;

        expected = new ArrayList<>(listStudent);
        StudentService.sort2(expected, IConstStudent.TAG_YEAR);
        Assert.assertEquals("Error sorting by year", expected, actual);

        expected = new ArrayList<>(listStudent);
        StudentService.sort2(expected, IConstStudent.TAG_FACULTY);
        Assert.assertEquals("Error sorting by faculty", expected, actual);

        expected = new ArrayList<>(listStudent);
        StudentService.sort2(expected, IConstStudent.TAG_COURSE);
        Assert.assertEquals("Error sorting by course", expected, actual);
    }

    @Test
    public void sort3() {

        List<Student> expected;

        expected = new ArrayList<>(listStudent);
        StudentService.sort3(expected, IConstStudent.Field.YEAR);
        Assert.assertEquals("Error sorting by year", expected, actual);

        expected = new ArrayList<>(listStudent);
        StudentService.sort3(expected, IConstStudent.Field.FACULTY);
        Assert.assertEquals("Error sorting by faculty", expected, actual);

        expected = new ArrayList<>(listStudent);
        StudentService.sort3(expected, IConstStudent.Field.COURSE);
        Assert.assertEquals("Error sorting by course", expected, actual);
    }
}