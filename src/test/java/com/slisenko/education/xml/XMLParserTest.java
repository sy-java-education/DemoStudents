package com.slisenko.education.xml;

import com.slisenko.education.student.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLParserTest {

    public static String strXml =
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

    @Test
    public void parseStr() {

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

    @Test(expected = IllegalArgumentException.class)
    public void parseStr_BadFieldOpenTag() {

        String tag = "<name>";
        int index = strXml.indexOf(tag);
        String badXml = strXml.substring(0,  index) + "<????>" +
                strXml.substring(index + tag.length());
        /*
        String badXml = strXml.substring(0,  index) +
                strXml.substring(index + tag.length());
        */

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
        //List<Student> list = parser.parseStr(strXml);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseStr_BadFieldCloseTag() {

        String tag = "</year>";
        int index = strXml.indexOf(tag);
        /*
        String badXml = strXml.substring(0,  index) + "</????>" +
                strXml.substring(index + tag.length());
        */
        String badXml = strXml.substring(0,  index) +
                strXml.substring(index + tag.length());

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
        //List<Student> list = parser.parseStr(strXml);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseStr_EmptyField() {

        String openTag = "<faculty>";
        String closeTag = "</faculty>";
        int indexOpenTag = strXml.indexOf(openTag);
        int indexCloseTag = strXml.indexOf(closeTag);
        String badXml = strXml.substring(0,  indexOpenTag + openTag.length()) +
                strXml.substring(indexCloseTag);

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
        //List<Student> list = parser.parseStr(strXml);
    }

    @Test(expected = NumberFormatException.class)
    public void parseStr_BadFieldNumberFormat() {

        String openTag = "<course>";
        String closeTag = "</course>";
        String badNumber = "?";
        int indexOpenTag = strXml.indexOf(openTag);
        int indexCloseTag = strXml.indexOf(closeTag);
        String badXml = strXml.substring(0,  indexOpenTag + openTag.length()) + badNumber +
                strXml.substring(indexCloseTag);

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
        //List<Student> list = parser.parseStr(strXml);
    }

    @Test
    public void parseStr_BadRecordOpenTag() {

        String tag = "<student>";
        int index = strXml.indexOf(tag);
        index = strXml.indexOf(tag, index + tag.length()); // Next tag
        /*
        String badXml = strXml.substring(0,  index) + "<????>" +
                strXml.substring(index + tag.length());
        */
        String badXml = strXml.substring(0,  index) +
                strXml.substring(index + tag.length());

        List<Student> expected = new ArrayList<>();

        Student student = new Student();

        student.setName("Имя 3");
        student.setSurname("Фамилия 3");
        student.setYear(1993);
        student.setFaculty("Факультет 3");
        student.setCourse(3);
        expected.add(student);

        XMLParser parser = new XMLParser();
        List<Student> actual = parser.parseStr(badXml);
        //List<Student> actual = parser.parseStr(strXml);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStr_BadRecordCloseTag() {

        String tag = "</student>";
        int index = strXml.indexOf(tag);
        index = strXml.indexOf(tag, index + tag.length()); // Next tag

        String badXml = strXml.substring(0,  index) + "</????>" +
                strXml.substring(index + tag.length());
        /*
        String badXml = strXml.substring(0,  index) +
                strXml.substring(index + tag.length());
        */
        List<Student> expected = new ArrayList<>();

        Student student = new Student();

        student.setName("Имя 3");
        student.setSurname("Фамилия 3");
        student.setYear(1993);
        student.setFaculty("Факультет 3");
        student.setCourse(3);
        expected.add(student);

        XMLParser parser = new XMLParser();
        List<Student> actual = parser.parseStr(badXml);
        //List<Student> actual = parser.parseStr(strXml);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStr_BadTableOpenTag() {

        String tag = "<students>";
        int index = strXml.indexOf(tag);
        String badXml = strXml.substring(0,  index) + "<????>" +
                strXml.substring(index + tag.length());
        /*
        String badXml = strXml.substring(0,  index) +
                strXml.substring(index + tag.length());
        */
        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
        //List<Student> list = parser.parseStr(strXml);

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void parseStr_BadTableCloseTag() {

        String tag = "</students>";
        int index = strXml.indexOf(tag);
        /*
        String badXml = strXml.substring(0,  index) + "</????>" +
                strXml.substring(index + tag.length());
        */
        String badXml = strXml.substring(0,  index) +
                strXml.substring(index + tag.length());

        XMLParser parser = new XMLParser();
        List<Student> list = parser.parseStr(badXml);
        //List<Student> list = parser.parseStr(strXml);

        //Assert.assertTrue(list.isEmpty());
        if (!list.isEmpty()) {
            Assert.fail();
        }
    }
}