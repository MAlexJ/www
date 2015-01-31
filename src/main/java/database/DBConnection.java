package database;

import constants.Constants;
import entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    private Connection conn = null;
    private ResultSet rs = null;

    private static PreparedStatement loadAllRoles;
    private static PreparedStatement loadAllLogins;
    private static PreparedStatement loadAccountByLogin;
    private static PreparedStatement loadRolesById;
    private static PreparedStatement getIdAccountRoles;
    private static PreparedStatement getStudentList;
    private static PreparedStatement getStudentById;
    private static PreparedStatement setStudentByIdUpdate;
    private static PreparedStatement setStudentByIdDelete;
    private static PreparedStatement setStudent;
    private static PreparedStatement getDisciplinesList;
    private static PreparedStatement setDiscipline;
    private static PreparedStatement setDisciplinesByIdDelete;
    private static PreparedStatement getDisciplineById;
    private static PreparedStatement setDisciplineByIdUpdate;
    private static PreparedStatement loadAllTerm;
    private static PreparedStatement setTermByIdDelete;

    public DBConnection(String url) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Constants.CONNECTING_URL, "root", "root");
            loadPreparedStatements();
        } catch (SQLException e) {
            LOGGER.error("SQLException - > DBConnection(String url); " + e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException - > DBConnection(String url); " + e);
        }
    }

    private void loadPreparedStatements() {
        try {
            loadAllRoles = conn.prepareStatement("SELECT * FROM roles");
            loadAllLogins = conn.prepareStatement("SELECT login, id FROM account");
            loadAccountByLogin = conn.prepareStatement("SELECT * FROM account WHERE login = ?");
            loadRolesById = conn.prepareStatement("SELECT * FROM roles WHERE id_roles =?");
            getIdAccountRoles = conn.prepareStatement("SELECT id_role FROM user_roles WHERE id_account = ?");
            getStudentList = conn.prepareStatement("SELECT * FROM students ORDER BY last_name");
            getStudentById = conn.prepareStatement("SELECT * FROM students WHERE id =?");
            setStudentByIdUpdate = conn.prepareStatement("UPDATE students SET first_name=?, last_name=?, groupe=?, data_o_entri=? WHERE id=?");
            setStudentByIdDelete = conn.prepareStatement("DELETE FROM students WHERE id =?");
            setStudent = conn.prepareStatement("INSERT INTO students (first_name, last_name, groupe, data_o_entri) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            getDisciplinesList = conn.prepareStatement("SELECT * FROM disciplines ORDER BY (name)");
            setDiscipline = conn.prepareStatement("INSERT INTO disciplines (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            setDisciplinesByIdDelete = conn.prepareStatement("DELETE FROM disciplines WHERE id =?");
            getDisciplineById = conn.prepareStatement("SELECT * FROM disciplines WHERE id =?");
            setDisciplineByIdUpdate = conn.prepareStatement("UPDATE disciplines SET name=?  WHERE id=?");
            loadAllTerm = conn.prepareStatement("SELECT semestr.id, semestr.length, disciplines.name FROM semestr_discplines INNER JOIN semestr ON semestr.id=semestr_discplines.id_semestr INNER JOIN disciplines ON disciplines.id=semestr_discplines.id_discplines ORDER BY semestr.id, disciplines.name");
            setTermByIdDelete = conn.prepareStatement("DELETE FROM semestr WHERE id =?");
        } catch (SQLException e) {
            LOGGER.error("SQLException - > void loadPreparedStatements(); " + e);
        }
    }

    private void closePreparedStatements() {
        try {
            //TODO: need add all Prepared
            loadAllRoles.close();
            loadAllLogins.close();
            loadAccountByLogin.close();
            loadRolesById.close();
            getIdAccountRoles.close();
            getStudentList.close();
            getStudentById.close();
            setStudentByIdUpdate.close();
            setStudentByIdDelete.close();
            setStudent.close();
            getDisciplinesList.close();
            setDiscipline.close();
            setDisciplinesByIdDelete.close();
            getDisciplineById.close();
            setDisciplineByIdUpdate.close();
        } catch (SQLException e) {
            LOGGER.error("SQLException - > void closePreparedStatements(); " + e);
        }
    }

    public List<Role> getAllRoles() {
        rs = null;
        List<Role> result = new LinkedList<Role>();
        try {
            //SELECT * FROM roles
            rs = loadAllRoles.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("id_roles"));
                r.setName(rs.getString("name_roles"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Account> getAllLogins() {
        //SELECT login, id FROM account
        rs = null;
        List<Account> result = new LinkedList<Account>();
        try {
            rs = loadAllLogins.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("login"));
                account.setId(rs.getInt("id"));
                account.setPassword(rs.getString("password"));
                result.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Account getAccountByLogin(String login) {
        rs = null;
        Account result = new Account();
        try {
            // SELECT * FROM account WHERE login = ?
            loadAccountByLogin.setString(1, login);
            rs = loadAccountByLogin.executeQuery();

            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setUsername(rs.getString("login"));
                result.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<Role> getRolesById(int id) {
        rs = null;
        List<Role> result = new LinkedList<Role>();
        try {
            // SELECT * FROM roles WHERE id_roles =?
            loadRolesById.setInt(1, id);
            rs = loadRolesById.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id_roles"));
                role.setName(rs.getString("name_roles"));
                result.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void close() {
        closePreparedStatements();
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            LOGGER.info("close() exeption " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Integer> getIdAccountRoles(int idAccount) {
        //SELECT id_role FROM user_roles WHERE id_account = ?
        rs = null;
        List<Integer> idAccountRoles = new ArrayList<Integer>();
        try {
            getIdAccountRoles.setInt(1, idAccount);
            rs = getIdAccountRoles.executeQuery();
            while (rs.next()) {
                idAccountRoles.add(rs.getInt("id_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAccountRoles;
    }

    //***************** Student *************************************
    public List<Student> getStudentList() {
        rs = null;
        List<Student> studentsList = new LinkedList<Student>();
        try {
            //SELECT * FROM students
            rs = getStudentList.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("first_name"));
                student.setFirst_name(rs.getString("last_name"));
                student.setGroup(rs.getString("groupe"));
                student.setDate(rs.getDate("data_o_entri"));
                studentsList.add(student);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean getStudentList() " + e);
        }
        return studentsList;
    }

    public Student getStudentById(int id) {
        rs = null;
        Student student = new Student();
        try {
            //SELECT * FROM students WHERE id =?
            getStudentById.setInt(1, id);
            rs = getStudentById.executeQuery();
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("first_name"));
                student.setFirst_name(rs.getString("last_name"));
                student.setGroup(rs.getString("groupe"));
                student.setDate(rs.getDate("data_o_entri"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > Student getStudentById(int id); " + e);
        }
        return student;
    }

    public boolean setStudentByIdUpdate(Student student) {
        //UPDATE students SET first_name=?, last_name=?, groupe=?, data_o_entri=?   WHERE id=?
        int flag;
        try {
            setStudentByIdUpdate.setString(1, student.getFirst_name());
            setStudentByIdUpdate.setString(2, student.getName());
            setStudentByIdUpdate.setString(3, student.getGroup());
            setStudentByIdUpdate.setString(4, String.valueOf(student.getDate()));
            setStudentByIdUpdate.setInt(5, student.getId());
            flag = setStudentByIdUpdate.executeUpdate();
            if (flag == 0) {
                throw new SQLException("Record not exist in the DataBase");
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setStudentByIdUpdate(Student student); " + e);
            return false;
        }
        return true;
    }

    public boolean setStudentByIdDelete(int id) {
        //DELETE FROM students WHERE id =?
        int flag;
        try {
            setStudentByIdDelete.setInt(1, id);
            flag = setStudentByIdDelete.executeUpdate();
            if (flag == 0) {
                throw new SQLException("Record not exist in the DataBase");
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setStudentAdd(Student student); " + e);
            return false;
        }
        return true;
    }

    public boolean setStudentAdd(Student student) {
        //"INSERT INTO students (first_name, last_name, groupe, data_o_entri) VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS
        try {
            setStudent.setString(1, student.getFirst_name());
            setStudent.setString(2, student.getName());
            setStudent.setString(3, student.getGroup());
            setStudent.setDate(4, student.getDate());
            setStudent.executeUpdate();
            try (ResultSet generatedKeys = setStudent.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int key = generatedKeys.getInt(1);
                    student.setId(key);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setStudentAdd(Student student); " + e);
            return false;
        }
        return true;
    }

    //***************** Discipline *************************************

    public List<Discipline> getDisciplinesList() {
        rs = null;
        List<Discipline> disciplinesList = new LinkedList<Discipline>();
        try {
            //SELECT * FROM disciplines
            rs = getDisciplinesList.executeQuery();
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setName(rs.getString("name"));
                disciplinesList.add(discipline);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > getDisciplinesList() " + e);
        }
        return disciplinesList;
    }

    public boolean setDisciplineAdd(Discipline discipline) {
        //"INSERT INTO disciplines (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        try {
            setDiscipline.setString(1, discipline.getName());
            setDiscipline.executeUpdate();
            try (ResultSet generatedKeys = setDiscipline.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int key = generatedKeys.getInt(1);
                    discipline.setId(key);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setDisciplineAdd(Discipline discipline); " + e);
            return false;
        }
        return true;
    }

    public boolean setDisciplineByIdDelete(int id) {
        //DELETE FROM disciplines WHERE id =?
        int flag;
        try {
            setDisciplinesByIdDelete.setInt(1, id);
            flag = setDisciplinesByIdDelete.executeUpdate();
            if (flag == 0) {
                throw new SQLException("Record not exist in the DataBase");
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setDisciplineByIdDelete(int id); " + e);
            return false;
        }
        return true;
    }

    public Discipline getDisciplineById(int id) {
        rs = null;
        Discipline discipline = new Discipline();
        try {
            //SELECT * FROM disciplines WHERE id =?"
            getDisciplineById.setInt(1, id);
            rs = getDisciplineById.executeQuery();
            while (rs.next()) {
                discipline.setId(rs.getInt("id"));
                discipline.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > Student getStudentById(int id); " + e);
        }
        return discipline;
    }

    public boolean setDisciplineByIdUpdate(Discipline discipline) {
        //UPDATE disciplines SET name=?  WHERE id=?
        int flag;
        try {
            setDisciplineByIdUpdate.setString(1, discipline.getName());
            setDisciplineByIdUpdate.setInt(2, discipline.getId());
            flag = setDisciplineByIdUpdate.executeUpdate();
            if (flag == 0) {
                throw new SQLException("Record not exist in the DataBase");
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setDisciplineByIdUpdate(Discipline discipline); " + e);
            return false;
        }
        return true;
    }

    //***************** Term *************************************

    public List<Term> getAllTerm() {
        //SELECT semestr.id, semestr.length, disciplines.name
        //FROM semestr_discplines
        //INNER JOIN semestr  on semestr.id=semestr_discplines.id_semestr
        //INNER JOIN disciplines on disciplines.id=semestr_discplines.id_discplines
        //ORDER BY semestr.id;
        rs = null;
        int idSemest = 0;
        List<Term> listTerm = new LinkedList<Term>();
        LinkedList<Term> resultTerm = new LinkedList<Term>();

        try {
            rs = loadAllTerm.executeQuery();
            while (rs.next()) {
                Term term = new Term();
                Discipline discipline = new Discipline();
                List<Discipline> disciplinesList = new LinkedList<Discipline>();
                term.setId(rs.getInt("id"));
                term.setDuration(rs.getInt("length"));
                discipline.setName(rs.getString("name"));
                disciplinesList.add(discipline);
                term.setDiscipline(disciplinesList);
                listTerm.add(term);
            }
            if (listTerm.isEmpty()) {
                return listTerm;
            }
            // work if ORDER BY semestr.id;
            for (Term iter : listTerm) {
                int id =iter.getId();
                if (id == idSemest) {
                    Term term = resultTerm.getLast();
                    resultTerm.removeLast();
                    List<Discipline> list = term.getDiscipline();
                    List<Discipline> listIter = iter.getDiscipline();
                    for(Discipline disIter:listIter ){
                        list.add(disIter);
                    }
                    resultTerm.add(term);
                } else {
                    resultTerm.add(iter);
                }
                idSemest=id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultTerm;
    }

    public boolean setTermByIdDelete(int id) {
        //DELETE FROM semestr WHERE id =?
        int flag;
        try {
            setTermByIdDelete.setInt(1, id);
            flag = setTermByIdDelete.executeUpdate();
            if (flag == 0) {
                throw new SQLException("Record not exist in the DataBase");
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException - > boolean setTermByIdDelete(int id); " + e);
            return false;
        }
        return true;
    }
}
