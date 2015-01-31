package services.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entity.Student;
import services.StudentService;

import java.sql.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public boolean studentModifing(int id) {
        //TODO: Need check out valid this key to Data Base
        if (id != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Student getStudentById(int id) {
        StudentDao studentDao = new StudentDaoImpl();
        return studentDao.getStudentById(id);
    }


    @Override
    public boolean updateStudentById(Integer id, String name, String first_name, String group, Date date) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setFirst_name(first_name);
        student.setGroup(group);
        student.setDate(date);
        return studentDao.updateStudentById(student);
    }


    @Override
    public boolean studentDelete(int id) {
        return studentDao.deleteStudentById(id);
    }

    @Override
    public boolean studentAdd(String name, String first_name, String group, Date date) {
        Student student = new Student();
        student.setName(name);
        student.setFirst_name(first_name);
        student.setGroup(group);
        student.setDate(date);
        return studentDao.studentAdd(student);
    }


    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

}
