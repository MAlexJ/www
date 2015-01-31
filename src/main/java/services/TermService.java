package services;

import entity.Term;

import java.util.List;

public interface TermService {
    boolean modifing(int id);

    boolean deleteTerm(int id);

    boolean add(int idS);

    List<Term> getTerms();

    Term getById(int id);
}
