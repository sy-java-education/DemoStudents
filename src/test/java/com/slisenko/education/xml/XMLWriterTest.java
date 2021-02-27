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
                    "<name>Имя 3</name><surname>Фамилия 3</surname><year>1993</year><faculty>Факультет 3</faculty><course>3</course>" +
                "</student>" +
                "<student>" +
                    "<name>Имя 2</name><surname>Фамилия 2</surname><year>1992</year><faculty>Факультет 2</faculty><course>2</course>" +
                "</student>" +
                "<student>" +
                    "<name>Имя 1</name><surname>Фамилия 1</surname><year>1991</year><faculty>Факультет 1</faculty><course>1</course>" +
                "</student>" +
            "</students>";

        /*
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();

        student1.setName("Имя 3");
        student1.setSurname("Фамилия 3");
        student1.setYear(1993);
        student1.setFaculty("Факультет 3");
        student1.setCourse(3);

        student2.setName("Имя 2");
        student2.setSurname("Фамилия 2");
        student2.setYear(1992);
        student2.setFaculty("Факультет 2");
        student2.setCourse(2);

        student3.setName("Имя 1");
        student3.setSurname("Фамилия 1");
        student3.setYear(1991);
        student3.setFaculty("Факультет 1");
        student3.setCourse(1);
         */

        List<Student> list = new ArrayList<>();

        /*
        list.add(student1);
        list.add(student2);
        list.add(student3);
         */

        Student student;

        for (int i = 0; i < 3; i++) {
            student = new Student();
            student.setName("Имя " + (3 - i));
            student.setSurname("Фамилия " + (3 - i));
            student.setYear(1990  + (3 - i));
            student.setFaculty("Факультет " + (3 - i));
            student.setCourse(3 - i);
            list.add(student);
        }

        XMLWriter writer = new XMLWriter();
        String actual = writer.writeToString(list);

        Assert.assertEquals(expected, actual);
    }
}