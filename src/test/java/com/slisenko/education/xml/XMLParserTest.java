package com.slisenko.education.xml;

import com.slisenko.education.student.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLParserTest {

    @Test
    public void parseStr() {

        String strXml =
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

        List<Student> expected = new ArrayList<>();

        Student student;

        for (int i = 0; i < 3; i++) {
            student = new Student();
            student.setName("Имя " + (3 - i));
            student.setSurname("Фамилия " + (3 - i));
            student.setYear(1990  + (3 - i));
            student.setFaculty("Факультет " + (3 - i));
            student.setCourse(3 - i);
            expected.add(student);
        }

        XMLParser parser = new XMLParser();
        List<Student> actual = parser.parseStr(strXml);

        Assert.assertEquals(expected, actual);
    }
}