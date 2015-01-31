package database;

import constants.Constants;
import entity.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    private static final Logger LOGGER = Logger
            .getLogger(DataService.class);
    private static List<DBConnection> conPool = new ArrayList<DBConnection>();
    private static Object monitor = new Object();

    public boolean init() {
        try {
            LOGGER.info("init database");
            for (int i = 0; i < Constants.CONNECTING_POOL_SIZE; i++) {
                newConnection();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DBConnection getDBConnection() {
        synchronized (monitor) {
            if (conPool.isEmpty()) {
                newConnection();
            }
            DBConnection conn = conPool.remove(0);
            return conn;
        }
    }

    public void putDBConnection(DBConnection conn) {
        synchronized (monitor) {
            conPool.add(conn);
        }
    }

    private void newConnection() {
        DBConnection conn = new DBConnection(Constants.CONNECTING_URL);
        synchronized (monitor) {
            conPool.add(conn);
        }
    }

    public List<Role> getAllRoles() {
        DBConnection conn = getDBConnection();
        List<Role> result = conn.getAllRoles();
        this.putDBConnection(conn);
        return result;

    }

    public List<Account> getAllLogins() {
        DBConnection conn = getDBConnection();
        List<Account> result = conn.getAllLogins();
        this.putDBConnection(conn);
        return result;
    }

    public Account getAccountByLogin(String login) {
        DBConnection conn = getDBConnection();
        Account result = conn.getAccountByLogin(login);
        this.putDBConnection(conn);
        return result;
    }

    public List<Role> getRolesById(int id) {
        DBConnection conn = getDBConnection();
        List<Role> result = conn.getRolesById(id);
        this.putDBConnection(conn);
        return result;

    }

    public List<Integer> getIdAccountRoles(int idAccount) {
        DBConnection conn = getDBConnection();
        List<Integer> idAccountRoles = conn.getIdAccountRoles(idAccount);
        this.putDBConnection(conn);
        return idAccountRoles;
    }

    public List<Student> getStudentList() {
        DBConnection conn = getDBConnection();
        List<Student> studentList = conn.getStudentList();
        this.putDBConnection(conn);
        return studentList;
    }

    public Student getStudenteById(int id) {
        DBConnection conn = getDBConnection();
        Student student = conn.getStudentById(id);
        this.putDBConnection(conn);
        return student;
    }

    public boolean setStudenteByIdUpdate(Student student) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setStudentByIdUpdate(student);
        this.putDBConnection(conn);
        return flag;
    }

    public boolean setStudenteByIdDelete(int id) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setStudentByIdDelete(id);
        this.putDBConnection(conn);
        return flag;
    }

    public boolean setStudenteAdd(Student student) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setStudentAdd(student);
        this.putDBConnection(conn);
        return flag;
    }

    public List<Discipline> getDisciplinesList() {
        DBConnection conn = getDBConnection();
        List<Discipline> disciplinesList = conn.getDisciplinesList();
        this.putDBConnection(conn);
        return disciplinesList;
    }

    public boolean setDisciplineAdd(Discipline discipline) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setDisciplineAdd(discipline);
        this.putDBConnection(conn);
        return flag;
    }

    public boolean setDisciplineByIdDelete(int id) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setDisciplineByIdDelete(id);
        this.putDBConnection(conn);
        return flag;
    }

    public Discipline getDisciplineById(int id) {
        DBConnection conn = getDBConnection();
        Discipline discipline = conn.getDisciplineById(id);
        this.putDBConnection(conn);
        return discipline;
    }

    public boolean setDisciplineByIdUpdate(Discipline discipline) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setDisciplineByIdUpdate(discipline);
        this.putDBConnection(conn);
        return flag;
    }

    public List<Term> getTermsList() {
        DBConnection conn = getDBConnection();
        List<Term> termesList = conn.getAllTerm();
        this.putDBConnection(conn);
        return termesList;
    }

    public boolean setTermByIdDelete(int id) {
        boolean flag;
        DBConnection conn = getDBConnection();
        flag = conn.setTermByIdDelete(id);
        this.putDBConnection(conn);
        return flag;
    }


    public void close() {
        //????????????????????
    }
}
