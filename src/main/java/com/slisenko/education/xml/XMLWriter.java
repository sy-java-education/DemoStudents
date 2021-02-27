package com.slisenko.education.xml;

import com.slisenko.education.student.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class for converting information about students to XML format.
 */
public class XMLWriter {

    /**
     * Returns an open string tag with the angle brackets
     * @param tag String tag
     * @return Open string tag with the angle brackets
     */
    public static String openTag(String tag) {

        return String.format("<%s>", tag);
    }

    /**
     * Returns an close string tag with the angle brackets
     * @param tag String tag
     * @return Close string tag with the angle brackets
     */
    public static String closeTag(String tag) {

        return String.format("</%s>", tag);
    }

    /**
     * Returns a string in the format "<tag>field</tag>"
     * @param tag String tag
     * @param field String field
     * @return String in the format "<tag>field</tag>"
     */
    private String fieldWithTags(String tag, String field) {

        return String.format("%s%s%s", openTag(tag), field, closeTag(tag));
    }

    /**
     * Converts the information from the list of objects of the Student class
     * to XML format and writes it to a file.
     * @param list List with objects of the Student class
     * @param fileName The name of the XML file for writing
     * @throws IOException
     */
    public void writeToFile(List<Student> list, String fileName) throws IOException {

        if (list == null) {
            throw new IllegalArgumentException("List pointer is NULL");
        }

        if (fileName == null) {
            throw new IllegalArgumentException("FileName pointer is NULL");
        }

        try (FileWriter fout = new FileWriter(fileName)) {
            fout.write(writeToString(list));
        } catch (IOException ioExc) {
            throw new IOException("Error writing to file " + fileName);
        }
    }

    /**
     * Converts the information from the list of objects of the Student class
     * to XML string.
     * @param list List with objects of the Student class
     * @return XML String with information about students
     */
    public String writeToString(List<Student> list) {

        StringBuilder sb = new StringBuilder();

        sb.append(openTag(IConstStudent.TAG_TABLE));
        for (Student st : list) {
            sb.append(openTag(IConstStudent.TAG_RECORD));

            sb.append(fieldWithTags(IConstStudent.TAG_NAME, st.getName())); // name
            sb.append(fieldWithTags(IConstStudent.TAG_SURNAME, st.getSurname())); // surname
            //sb.append(fieldWithTags(IConstStudent.TAG_YEAR,String.valueOf(st.getYear()))); // year
            //sb.append(fieldWithTags(IConstStudent.TAG_YEAR, Integer.toString(st.getYear()))); // year
            sb.append(fieldWithTags(IConstStudent.TAG_YEAR, String.format("%d", st.getYear()))); // year
            sb.append(fieldWithTags(IConstStudent.TAG_FACULTY, st.getFaculty())); // faculty
            //sb.append(fieldWithTags(IConstStudent.TAG_COURSE, String.valueOf(st.getCourse()))); // course
            //sb.append(fieldWithTags(IConstStudent.TAG_COURSE, Integer.toString(st.getCourse()))); // course
            sb.append(fieldWithTags(IConstStudent.TAG_COURSE, String.format("%d", st.getCourse()))); // course

            sb.append(closeTag(IConstStudent.TAG_RECORD));
        }
        sb.append(closeTag(IConstStudent.TAG_TABLE));

        return sb.toString();
    }
}
