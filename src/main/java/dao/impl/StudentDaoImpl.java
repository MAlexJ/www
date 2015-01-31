package dao.impl;

import dao.StudentDao;
import database.DataService;
import entity.Student;

import java.util.List;


public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getStudents() {
        DataService conn = new DataService();
        return conn.getStudentList();
    }

    @Override
    public Student getStudentById(int id) {
        DataService conn = new DataService();
        return conn.getStudenteById(id);
    }

    @Override
    public boolean updateStudentById(Student student) {
        DataService conn = new DataService();
        return conn.setStudenteByIdUpdate(student);
    }


    @Override
    public boolean deleteStudentById(int idSudent) {
        boolean flag;
        DataService conn = new DataService();
        flag = conn.setStudenteByIdDelete(idSudent);
        return flag;
    }

    @Override
    public boolean studentAdd(Student student) {
        boolean flag;
        DataService conn = new DataService();
        flag = conn.setStudenteAdd(student);
        return flag;
    }
}
