package controllers;

import constants.Constants;
import entity.Discipline;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisciplinesListController extends AbstractWebtasksServletHandler {

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DisciplineService disciplineServise = new DisciplineServiceImpl();
        List<Discipline> disciplineList = disciplineServise.getDisciplines();
        if (disciplineList.isEmpty()) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorListDB");
        }
        request.setAttribute("disciplines", disciplineList);
        gotoToJSP("main/disciplinesList.jsp", request, response);
    }
}
