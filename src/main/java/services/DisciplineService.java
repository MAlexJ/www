package services;

import entity.Discipline;

import java.util.List;

public interface DisciplineService {

	boolean deleteDiscipline(int id);

	boolean addDiscipline(String name);

	List<Discipline> getDisciplines();

	Discipline getDisciplineById(int id);

    boolean updateStudentById(Integer id, String name);

}
