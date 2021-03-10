package com.slisenko.education.service;

import com.slisenko.education.student.Student;
import com.slisenko.education.xml.IConstStudent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class StudentServiceTest {

    private static List<Student> actual = new ArrayList<>();
    private String fieldForSort;

    public StudentServiceTest(String fieldForSort) {

        this.fieldForSort = fieldForSort;
    }

    @Parameterized.Parameters(name = "{index}: fieldForSort - {0}")
    public static Object[] data () {

        return new Object[] {
            "year",
            "faculty",
            "course"
        };
    }

    private List<Student> getSortedList(String field) {

        List<Student> list = new ArrayList<>();

        switch (field) {
            case IConstStudent.TAG_YEAR:
                list.add(new Student("Name3", "Surname3", 1991, "Faculty1", 2));
                list.add(new Student("Name2", "Surname2", 1992, "Faculty3", 1));
                list.add(new Student("Name1", "Surname1", 1993, "Faculty2", 3));
                break;
            case IConstStudent.TAG_FACULTY:
                list.add(new Student("Name3", "Surname3", 1991, "Faculty1", 2));
                list.add(new Student("Name1", "Surname1", 1993, "Faculty2", 3));
                list.add(new Student("Name2", "Surname2", 1992, "Faculty3", 1));
                break;
            case IConstStudent.TAG_COURSE:
                list.add(new Student("Name2", "Surname2", 1992, "Faculty3", 1));
                list.add(new Student("Name3", "Surname3", 1991, "Faculty1", 2));
                list.add(new Student("Name1", "Surname1", 1993, "Faculty2", 3));
                break;
        }
        return list;
    }

    @Before
    public void setUp() {

        actual.clear();
        actual.add(new Student("Name1", "Surname1", 1993, "Faculty2", 3));
        actual.add(new Student("Name2", "Surname2", 1992, "Faculty3", 1));
        actual.add(new Student("Name3", "Surname3", 1991, "Faculty1", 2));
    }

    @Test
    public void sort() {

        StudentService.sort(actual, fieldForSort);
        Assert.assertEquals("Error sorting by " + fieldForSort, getSortedList(fieldForSort), actual);
    }

    @Test
    public void sort2() {

        StudentService.sort2(actual, fieldForSort);
        Assert.assertEquals("Error sorting by " + fieldForSort, getSortedList(fieldForSort), actual);
    }

    @Test
    public void sort3() {

        IConstStudent.Field field = IConstStudent.Field.YEAR;

        switch (fieldForSort) {
            case IConstStudent.TAG_YEAR:
                field = IConstStudent.Field.YEAR;
                break;
            case IConstStudent.TAG_FACULTY:
                field = IConstStudent.Field.FACULTY;
                break;
            case IConstStudent.TAG_COURSE:
                field = IConstStudent.Field.COURSE;
                break;
        }

        StudentService.sort3(actual, field);
        Assert.assertEquals("Error sorting by " + fieldForSort, getSortedList(fieldForSort), actual);
    }
}