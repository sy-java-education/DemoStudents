package com.slisenko.education.xml;

import com.slisenko.education.student.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing information about students in XML format
 */
public class XMLParser {

    /**
     * Returns the string between an open and close XML tags
     * @param record Xml string
     * @param tag string without angle brackets
     * @return the string between an open and close XML tags
     */
    private String parseTag(String record, String tag) {

        //int begin = record.indexOf("<" + tag + ">"); // Opening tag
        int begin = record.indexOf(XMLWriter.openTag(tag)); // Opening tag
        //int end = record.indexOf("</" + tag + ">"); // Closing tag
        int end = record.indexOf(XMLWriter.closeTag(tag)); // Closing tag
        if ((begin == -1) || (end == -1)) return "";

        // Returning a string between tags
        //return record.substring(begin + tag.length() + 2, end);
        return record.substring(begin + XMLWriter.openTag(tag).length(), end); // So on feng shui
    }

    // Sets the string value of a field for an object of the Student class
    /*
    private void parseFieldStr(String record, String field, Student student) {

        String value = parseTag(record, field);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Tag " + field + " is absent");
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
    */

    /**
     * Returns the value of a string field.
     * Throws an exception if the field is missing.
     * @param record Xml string
     * @param field Field name without angle brackets
     * @return Value of a string field
     */
    private String parseFieldStr(String record, String field) {

        String value = parseTag(record, field);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Tag " + field + " is absent.");
        }
        return value;
    }

    // Sets the integer value of a field for an object of the Student class
    /*
    private void parseFieldInt(String record, String field, Student student) {

        String value = parseTag(record, field);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Tag " + field + " is absent");
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
    */

    /**
     * Returns the value of an integer field.
     * Throws an exception if the field is missing.
     * Throws an exception if the field is wrong.
     * @param record Xml string
     * @param field Field name without angle brackets
     * @return Value of an integer field
     */
    private int parseFieldInt(String record, String field) {

        String valueStr = parseFieldStr(record, field);
        int value;
        try {
            value = Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Wrong field " + field);
        }
        return value;
    }

    /**
     * Parsing information for one student.
     * @param recordXml XML String with information about students
     * @return Object of the Student class
     */
    private Student parseStudent(String recordXml) {

        Student st = new Student();
        st.setName(parseFieldStr(recordXml, IConstStudent.TAG_NAME));
        st.setSurname(parseFieldStr(recordXml, IConstStudent.TAG_SURNAME));
        st.setYear(parseFieldInt(recordXml, IConstStudent.TAG_YEAR));
        st.setFaculty(parseFieldStr(recordXml, IConstStudent.TAG_FACULTY));
        st.setCourse(parseFieldInt(recordXml, IConstStudent.TAG_COURSE));
        return st;
    }

    /**
     * Parsing an XML string and forming a list with objects of the Student class.
     * Throws an exception if the XML string is empty.
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

        // Copy the record about the student to a separate string
        String recordXml = parseTag(strXml, IConstStudent.TAG_RECORD);

        //while (true) {
        while (!recordXml.isEmpty()) {

            // Copy the record about the student to a separate string
            //String recordXml = parseTag(strXml, IConstStudent.TAG_RECORD);

            //if (recordXml.isEmpty()) break; // If there are no more records, then exit

            // Search for the closing tag of record
            //int index = strXml.indexOf("</" + IConstStudent.TAG_RECORD + ">");
            int index = strXml.indexOf(XMLWriter.closeTag(IConstStudent.TAG_RECORD));

            // We leave all the entries in the original XML string, except the copied one
            //strXml = strXml.substring(index + IConstStudent.TAG_RECORD.length() + 3); // 3 => "</>"
            strXml = strXml.substring(index + XMLWriter.closeTag(IConstStudent.TAG_RECORD).length());

            // Creating an object for a student
            //Student person = new Student();

            // Parsing the one record with fields
            /*
            parseFieldStr(recordXml, IConstStudent.TAG_NAME, person); // name
            parseFieldStr(recordXml, IConstStudent.TAG_SURNAME, person); // surname
            parseFieldInt(recordXml, IConstStudent.TAG_YEAR, person); // year
            parseFieldStr(recordXml, IConstStudent.TAG_FACULTY, person); // faculty
            parseFieldInt(recordXml, IConstStudent.TAG_COURSE, person); // course
            */

            // Parsing the one record with fields
            Student person = parseStudent(recordXml);

            // Adding the completed person object of the Student class to the list
            listStudent.add(person);

            // Copy the record about the student to a separate string
            recordXml = parseTag(strXml, IConstStudent.TAG_RECORD);
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

        /*
        try (FileReader fin = new FileReader(fileName)) {
            int ch;
            while ((ch = fin.read()) != -1) {
                sb.append((char)ch);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + fileName + " not found");
        }
        */

        try (BufferedReader fin = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = fin.readLine()) != null) {
                sb.append(s + "\n");
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
