package dao.impl;

import dao.DisciplineDao;
import database.DataService;
import entity.Discipline;

import java.util.List;

public class DisciplineDaoImpl implements DisciplineDao {


	@Override
	public List<Discipline> getDisciplines() {
		DataService conn = new DataService();
		return conn.getDisciplinesList();
	}

    @Override
    public Discipline getDisciplineById(int id) {
        DataService conn = new DataService();
        return  conn.getDisciplineById(id);
    }

    @Override
    public boolean updateDiscipline(Discipline discipline) {
        DataService conn = new DataService();
        return conn.setDisciplineByIdUpdate(discipline);
    }

    @Override
    public boolean addDiscipline(Discipline discipline) {
        DataService conn = new DataService();
        return conn.setDisciplineAdd(discipline);
    }

    @Override
    public boolean deleteeDiscipline(int id) {
        DataService conn = new DataService();
        return conn.setDisciplineByIdDelete(id);
    }


}
