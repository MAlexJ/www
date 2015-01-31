package services.impl;

import dao.TermDao;
import dao.impl.TermDaoImpl;
import entity.Term;
import services.TermService;

import java.util.List;

public class TermServiceImpl implements TermService {
    TermDao termDao = new TermDaoImpl();
    @Override
    public boolean modifing(int id) {
        return false;
    }

    @Override
    public boolean deleteTerm(int id) {
        return false;
    }

    @Override
    public boolean add(int idS) {
        return false;
    }

    @Override
    public List<Term> getTerms() {
        return termDao.getTerms();
    }

    @Override
    public Term getById(int id) {
        return null;
    }
}
