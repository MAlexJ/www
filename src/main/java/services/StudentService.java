package services;

import entity.Student;
import exeptions.InvalidDataException;

import java.sql.Date;
import java.util.List;

public interface StudentService {

    boolean studentModifing(int id);

    boolean studentDelete(int id);

    boolean studentAdd(String name, String first_name, String group, Date date);

    List<Student> getStudents();

    Student getStudentById(int id) throws InvalidDataException;

    boolean updateStudentById(Integer id, String name, String first_name, String group, Date date);

}
