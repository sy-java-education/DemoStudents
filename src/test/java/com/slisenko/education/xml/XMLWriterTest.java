package com.slisenko.education.xml;

import com.slisenko.education.student.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLWriterTest {

    @Test
    public void writeToString() {

        String expected =
            "<students>" +
                "<student>" +
                    "<name>Name3</name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>3</course>" +
                "</student>" +
                "<student>" +
                    "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                "</student>" +
                "<student>" +
                    "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                "</student>" +
            "</students>";

        List<Student> list = new ArrayList<>();

        Student student;

        for (int i = 0; i < 3; i++) {
            student = new Student();
            student.setName("Name" + (3 - i));
            student.setSurname("Surname" + (3 - i));
            student.setYear(1990  + (3 - i));
            student.setFaculty("Faculty" + (3 - i));
            student.setCourse(3 - i);
            list.add(student);
        }

        XMLWriter writer = new XMLWriter();
        String actual = writer.writeToString(list);

        Assert.assertEquals(expected, actual);
    }
}