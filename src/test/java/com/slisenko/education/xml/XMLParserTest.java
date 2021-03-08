package com.slisenko.education.xml;

import com.slisenko.education.student.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLParserTest {

    private String strXml =
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

    @Test
    public void parseStr() {

        List<Student> expected = new ArrayList<>();
        Student student;

        for (int i = 0; i < 3; i++) {
            student = new Student();
            student.setName("Name" + (3 - i));
            student.setSurname("Surname" + (3 - i));
            student.setYear(1990  + (3 - i));
            student.setFaculty("Faculty" + (3 - i));
            student.setCourse(3 - i);
            expected.add(student);
        }

        XMLParser parser = new XMLParser();
        List<Student> actual = parser.parseStr(strXml);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseStr_BadFieldOpenTag() {

        // Bad tag <name> -> <????>
        String badXml =
                "<students>" +
                    "<student>" +
                        "<????>Name3</name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                "</students>";

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseStr_BadFieldCloseTag() {

        // Absent tag </year>
        String badXml =
                "<students>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>1993<faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                "</students>";

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseStr_EmptyField() {

        // Absent field Faculty
        String badXml =
                "<students>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>1993</year><faculty></faculty><course>3</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                "</students>";

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
    }

    @Test(expected = NumberFormatException.class)
    public void parseStr_BadFieldNumberFormat() {

        // Bad field Course -> ?
        String badXml =
                "<students>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>?</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                "</students>";

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
    }

    @Test
    public void parseStr_BadRecordOpenTag() {

        // Absent tag <student>
        String badXml =
                "<students>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                    "" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                "</students>";

        List<Student> expected = new ArrayList<>();
        Student student = new Student();

        student.setName("Name3");
        student.setSurname("Surname3");
        student.setYear(1993);
        student.setFaculty("Faculty3");
        student.setCourse(3);
        expected.add(student);

        XMLParser parser = new XMLParser();
        List<Student> actual = parser.parseStr(badXml);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStr_BadRecordCloseTag() {

        // Bad tag </student> -> <????>
        String badXml =
                "<students>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "<????>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                "</students>";

        List<Student> expected = new ArrayList<>();
        Student student = new Student();

        student.setName("Name3");
        student.setSurname("Surname3");
        student.setYear(1993);
        student.setFaculty("Faculty3");
        student.setCourse(3);
        expected.add(student);

        XMLParser parser = new XMLParser();
        List<Student> actual = parser.parseStr(badXml);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStr_BadTableOpenTag() {

        // Bad tag <students> -> <????>
        String badXml =
                "<????>" +
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

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void parseStr_BadTableCloseTag() {

        // Absent tag </students>
        String badXml =
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
                "";

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);

        if (!list.isEmpty()) {
            Assert.fail();
        }
    }
}