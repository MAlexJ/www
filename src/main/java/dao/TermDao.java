package dao;

import java.util.List;

import entity.Term;

public interface TermDao {
	boolean update(int id);

	boolean delete(int id);

	boolean add(int idS);

	List<Term> getTerms();

	Term getById(int id);
}
