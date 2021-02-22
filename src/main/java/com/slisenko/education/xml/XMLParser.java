package com.slisenko.education.xml;

import com.slisenko.education.student.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing information about students in XML format
 */
public class XMLParser {

    // Returns the string between an open and close XML tags
    private String parseTag(String record, String tag) {

        int begin = record.indexOf("<" + tag + ">"); // Opening tag
        int end = record.indexOf("</" + tag + ">"); // Closing tag
        if ((begin == -1) || (end == -1)) return "";

        // Returning a string between tags
        return record.substring(begin + tag.length() + 2, end);
    }

    // Sets the string value of a field for an object of the Student class
    private void parseFieldStr(String record, String field, Student student) {

        String value = parseTag(record, field);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Tag + " + field + " is absent");
        }
        switch (field) {
            case IConstStudent.TAG_NAME:
                student.setName(value);
                break;
            case IConstStudent.TAG_SURNAME:
                student.setSurname(value);
                break;
            case IConstStudent.TAG_FACULTY:
                student.setFaculty(value);
        }
    }

    // Sets the integer value of a field for an object of the Student class
    private void parseFieldInt(String record, String field, Student student) {

        String value = parseTag(record, field);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Tag + " + field + " is absent");
        }
        try {
            int valueInt = Integer.parseInt(value);
            switch (field) {
                case IConstStudent.TAG_YEAR:
                    student.setYear(valueInt);
                    break;
                case IConstStudent.TAG_COURSE:
                    student.setCourse(valueInt);
                    break;
            }
        } catch (NumberFormatException nfExc) {
            switch (field) {
                case IConstStudent.TAG_YEAR:
                    throw new NumberFormatException("Wrong field year");
                case IConstStudent.TAG_COURSE:
                    throw new NumberFormatException("Wrong field course");
            }
        }
    }

    /**
     * Parsing an XML string and forming a list with objects of the Student class
     * @param strXml A string with information about students, presented in XML format
     * @return List with objects of the Student class
     */
    public List<Student> parseStr(String strXml) {

        int begin;
        int end;
        List<Student> listStudent = new ArrayList<>();

        if ((strXml == null) || (strXml.isEmpty())) {
            throw new IllegalArgumentException(":XML is empty");
        }

        // We leave only student records in the original XML string
        strXml = parseTag(strXml, IConstStudent.TAG_TABLE);

        // Parsing records

        while (true) {

            // Copy the found record about the student to a separate string
            String recordXml = parseTag(strXml, IConstStudent.TAG_RECORD);

            if (recordXml.isEmpty()) break; // If there are no more records, then exit

            // Search for the closing tag of record
            int index = strXml.indexOf("</" + IConstStudent.TAG_RECORD + ">");

            // We leave all the entries in the original XML string, except the copied one
            strXml = strXml.substring(index + IConstStudent.TAG_RECORD.length() + 3, strXml.length()); // 3 => "</>"

            // Creating an object for a student
            Student person = new Student();

            // Parsing the one record with fields

            parseFieldStr(recordXml, IConstStudent.TAG_NAME, person); // name
            parseFieldStr(recordXml, IConstStudent.TAG_SURNAME, person); // surname
            parseFieldInt(recordXml, IConstStudent.TAG_YEAR, person); // year
            parseFieldStr(recordXml, IConstStudent.TAG_FACULTY, person); // faculty
            parseFieldInt(recordXml, IConstStudent.TAG_COURSE, person); // course

            // Adding the completed person object of the Student class to the list
            listStudent.add(person);
        }

        return listStudent;
    }

    /**
     * Reads an XML file into a string
     * @param fileName The name of the file for reading
     * @return A string with the information read from the file
     * @throws IOException Exception if the file is not found
     */
    public String fileToString(String fileName) throws IOException {

        StringBuilder sb = new StringBuilder();

        try (FileReader fin = new FileReader(fileName)) {
            int ch;
            while ((ch = fin.read()) != -1) {
                sb.append((char)ch);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + fileName + " not found");
        }

        return sb.toString();
    }

    /**
     * Parsing an XML file and forming a list with objects of the Student class
     * @param fileName The name of the XML file for reading and parsing
     * @return List with objects of the Student class
     * @throws IOException Exception if the file is not found
     */
    public List<Student> parseFile(String fileName) throws IOException {

        return parseStr(fileToString(fileName));
    }
}
