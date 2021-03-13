package com.slisenko.education;

import com.slisenko.education.service.StudentService;
import com.slisenko.education.student.Student;
import com.slisenko.education.xml.IConstStudent;
import com.slisenko.education.xml.XMLParser;
import com.slisenko.education.xml.XMLWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for searching and sorting information about students, which is
 * presented in XML format
 */
public class DemoStudents {

    public static void main(String[] args) {

        String inFileName = "";
        String outFileName = "";
        boolean fromFile = false;

        // The XML string with information about students
        String studentXML = null;

        if (args.length >= 3) {
            fromFile = true;
            inFileName = args[2];
            if (args.length >= 4) {
                outFileName = args[3];
            } else {
                outFileName = "result.xml";
            }
        } else {
            studentXML =
                    "<students>\n" +
                        "\t<student>\n" +
                            "\t\t<name>Ivan</name><surname>Ivanov</surname><year>1991</year><faculty>FKSIS</faculty><course>4</course>\n" +
                        "\t</student>\n" +
                        "\t<student>\n" +
                            "\t\t<name>Petr</name><surname>Petrov</surname><year>1992</year><faculty>FKSIS</faculty><course>3</course>\n" +
                        "\t</student>\n" +
                        "\t<student>\n" +
                            "\t\t<name>Sidor</name><surname>Sidorov</surname><year>1991</year><faculty>FITU</faculty><course>3</course>\n" +
                        "\t</student>\n" +
                        "\t<student>\n" +
                            "\t\t<name>Sergey</name><surname>Sergeev</surname><year>1992</year><faculty>FITU</faculty><course>4</course>\n" +
                        "\t</student>\n" +
                    "</students>";
        }

        // List with information about students
        List<Student> studentList = null;

        if (args.length >= 2) {

            XMLParser parserXml = new XMLParser(); // Parser

            // This list is intended for search results
            List<Student> findList = new ArrayList<>();

            try {
                if (fromFile) {
                    System.out.println("Sourse file " + inFileName + ":");
                    studentXML = parserXml.fileToString(inFileName);
                } else {
                    System.out.println("Sourse string XML:");
                }
                System.out.println(studentXML);
                System.out.println();

                studentList = parserXml.parseStr(studentXML);

                // Output the list with information about students to the console
                System.out.println("StudentList = " + studentList);

                if ((studentList != null) && (!studentList.isEmpty())) {

                    switch (args[0]) {
                        case IConstStudent.TAG_YEAR:
                            // Sorting the list by the year field
                            StudentService.sort(studentList, args[0]);

                            int year;
                            try {
                                year = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e) {
                                throw new NumberFormatException("Wrong parameter year");
                            }

                            // Searching by the year field
                            for (Student st : studentList) {
                                if (st.getYear() == year) {
                                    findList.add(st);
                                }
                            }
                            break;
                        case IConstStudent.TAG_FACULTY:
                            // Sorting the list by the faculty field
                            StudentService.sort2(studentList, args[0]);

                            // Searching by the faculty field
                            for (Student st : studentList) {
                                if (st.getFaculty().equals(args[1])) {
                                    findList.add(st);
                                }
                            }
                            break;
                        case IConstStudent.TAG_COURSE:
                            // Sorting the list by the course field
                            StudentService.sort3(studentList, IConstStudent.Field.COURSE);

                            int course;
                            try {
                                course = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e) {
                                throw new NumberFormatException("Wrong parameter course");
                            }

                            // Searching by the course field
                            for (Student st : studentList) {
                                if (st.getCourse() == course) {
                                    findList.add(st);
                                }
                            }
                            break;
                    }

                    // Output the list with results of search to the console
                    System.out.println("FindList = " + findList);
                }

                XMLWriter writer = new XMLWriter();

                if (fromFile) {
                    // Writing the XML string with results of search to the file
                    writer.writeToFile(findList, outFileName);
                } else {
                    // Output the XML string with results of search to the console
                    System.out.println("Result string XML:");
                    System.out.println(writer.writeToString(findList));
                }
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArgumentException: " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        } else {
            System.out.println("example: DemoStudents year 1991");
            System.out.println("example: DemoStudents faculty FITU");
            System.out.println("example: DemoStudents course 3");
            System.out.println("example: DemoStudents year 1992 infile.xml outfile.xml");
            System.out.println("example: DemoStudents faculty FKSIS infile.xml outfile.xml");
            System.out.println("example: DemoStudents course 4 infile.xml outfile.xml");
            System.out.println();
        }
    }
}
