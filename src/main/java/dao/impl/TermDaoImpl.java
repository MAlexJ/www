package dao.impl;

import dao.TermDao;
import database.DataService;
import entity.Term;

import java.util.List;


public class TermDaoImpl implements TermDao {
    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean add(int idS) {
        return false;
    }

    @Override
    public List<Term> getTerms() {
        DataService conn = new DataService();
        return conn.getTermsList();
    }

    @Override
    public Term getById(int id) {
        return null;
    }
}
