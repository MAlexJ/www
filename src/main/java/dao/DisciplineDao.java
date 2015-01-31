package dao;

import entity.Discipline;

import java.util.List;

public interface DisciplineDao {

	List<Discipline> getDisciplines();

	Discipline getDisciplineById(int id);

	boolean updateDiscipline(Discipline discipline);

    boolean addDiscipline(Discipline discipline);

	boolean deleteeDiscipline(int id);

}
