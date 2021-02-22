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

        boolean fromFile = false;

        // The XML string with information about students
        String studentXML = null;

        if ((args.length == 3) && (args[2].equals("file"))) {
            fromFile = true;
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
            List<Student> findList = new ArrayList<Student>();

            try {
                if (fromFile) {
                    // Output of the original XML file to the console
                    System.out.println("Sourse file XML:");
                    System.out.println(parserXml.fileToString(IConstDemoStudents.FILE_NAME_IN));
                } else {
                    // Output of the original XML string to the console
                    System.out.println("Sourse string XML:");
                    System.out.println(studentXML);
                }
                System.out.println();

                if (fromFile) {
                    studentList = parserXml.parseFile(IConstDemoStudents.FILE_NAME_IN);
                }
                else {
                    studentList = parserXml.parseStr(studentXML);
                }

                if ((studentList != null) && (!studentList.isEmpty())) {

                    switch (args[0]) {
                        case IConstStudent.TAG_YEAR:
                            int year;
                            try {
                                year = Integer.parseInt(args[1]);
                            } catch (NumberFormatException nfEsc) {
                                throw new NumberFormatException("Wrong parameter year");
                            }

                            // Sorting the list by the year field
                            //StudentService.sort(studentList, IConstStudent.TAG_YEAR);
                            //StudentService.sort2(studentList, IConstStudent.TAG_YEAR);
                            StudentService.sort3(studentList, IConstStudent.TAG_YEAR);

                            for (Student st : studentList) {
                                if (st.getYear() == year) {
                                    findList.add(st);
                                }
                            }
                            break;
                        case IConstStudent.TAG_FACULTY:
                            // Sorting the list by the faculty field
                            //StudentService.sort(studentList, IConstStudent.TAG_FACULTY);
                            //StudentService.sort2(studentList, IConstStudent.TAG_FACULTY);
                            StudentService.sort3(studentList, IConstStudent.TAG_FACULTY);

                            for (Student st : studentList) {
                                if (st.getFaculty().equals(args[1])) {
                                    findList.add(st);
                                }
                            }
                            break;
                        case IConstStudent.TAG_COURSE:
                            int course;
                            try {
                                course = Integer.parseInt(args[1]);
                            } catch (NumberFormatException nfEsc) {
                                throw new NumberFormatException("Wrong parameter course");
                            }

                            // Sorting the list by the course field
                            //StudentService.sort(studentList, IConstStudent.TAG_COURSE);
                            //StudentService.sort2(studentList, IConstStudent.TAG_COURSE);
                            StudentService.sort3(studentList, IConstStudent.TAG_COURSE);

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

                // Output the list with information about students to the console
                System.out.println("\nStudentList = " + studentList);

                XMLWriter writer = new XMLWriter();

                if (fromFile) {
                    // Writing the XML string with results of search to the file
                    writer.write(findList, IConstDemoStudents.FILE_NAME_OUT);
                } else {
                    // Output the XML string with results of search to the console
                    System.out.println("Result string XML:");
                    System.out.println(writer.write(findList));
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
        }
        else {
            System.out.println("example: DemoStudents year 1991");
            System.out.println("example: DemoStudents faculty FITU");
            System.out.println("example: DemoStudents course 3");
            System.out.println("example: DemoStudents year 1992 file");
            System.out.println("example: DemoStudents faculty FKSIS file");
            System.out.println("example: DemoStudents course 4 file");
            System.out.println();
        }
    }
}

/*
Error:java: error: release version 5 not supported.
Решение: File->Settings->Compiler->Java Compiler->Target bytecode version = 8 (was 1.5)

Error:java: Source option 5 is no longer supported. Use 6 or later.
Не помогло: File->Settings->Compiler->Java Compiler->Project bytecode version: 8 (was: "same as kanguage level")
Решение: File->Project Structure->Modules->Language level: "8 - Lambdas, type annotations etc." (was: "5 - 'enum' keyword, generics, autoboxing etc.")
 */