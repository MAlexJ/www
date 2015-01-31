package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getStudents();

    Student getStudentById(int id);

    boolean updateStudentById(Student student);

    boolean deleteStudentById(int idSudent);

    boolean studentAdd(Student student);

}
