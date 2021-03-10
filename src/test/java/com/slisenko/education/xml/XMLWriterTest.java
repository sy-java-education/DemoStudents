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

        String expected[] = {
                "<students>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                "</students>",

                // Absent name fields
                "<students>" +
                    "<student>" +
                        "<name></name><surname>Surname1</surname><year>1991</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                    "<student>" +
                        "<name></name><surname>Surname2</surname><year>1992</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name></name><surname>Surname3</surname><year>1993</year><faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                "</students>",

                // Absent year fields
                "<students>" +
                    "<student>" +
                        "<name>Name1</name><surname>Surname1</surname><year>0</year><faculty>Faculty1</faculty><course>1</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name2</name><surname>Surname2</surname><year>0</year><faculty>Faculty2</faculty><course>2</course>" +
                    "</student>" +
                    "<student>" +
                        "<name>Name3</name><surname>Surname3</surname><year>0</year><faculty>Faculty3</faculty><course>3</course>" +
                    "</student>" +
                "</students>",
        };

        for (int numTest = 0; numTest < 3; numTest++) {

            List<Student> list = new ArrayList<>();

            Student student;

            for (int i = 1; i <= 3; i++) {
                student = new Student();
                if (numTest != 1) {
                    student.setName("Name" + i);
                }
                student.setSurname("Surname" + i);
                if (numTest != 2) {
                    student.setYear(1990  + i);
                }
                student.setFaculty("Faculty" + i);
                student.setCourse(i);
                list.add(student);
            }

            XMLWriter writer = new XMLWriter();
            String actual = writer.writeToString(list);

            Assert.assertEquals(expected[numTest], actual);
        }
    }
}