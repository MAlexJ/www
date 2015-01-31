package services.impl;


import dao.DisciplineDao;
import dao.impl.DisciplineDaoImpl;
import entity.Discipline;
import services.DisciplineService;

import java.util.List;

public class DisciplineServiceImpl implements DisciplineService {
    DisciplineDao disciplineDao = new DisciplineDaoImpl();

    @Override
    public boolean deleteDiscipline(int id) {
        return disciplineDao.deleteeDiscipline(id);
    }

    @Override
    public boolean addDiscipline(String name) {
        Discipline discipline = new Discipline();
        discipline.setName(name);
        return disciplineDao.addDiscipline(discipline);
    }

    @Override
    public List<Discipline> getDisciplines() {
        return disciplineDao.getDisciplines();
    }

    @Override
    public Discipline getDisciplineById(int id) {
        return disciplineDao.getDisciplineById(id);
    }

    @Override
    public boolean updateStudentById(Integer id, String name) {
        Discipline discipline = new Discipline();
        discipline.setId(id);
        discipline.setName(name);
        return disciplineDao.updateDiscipline(discipline);
    }

}
