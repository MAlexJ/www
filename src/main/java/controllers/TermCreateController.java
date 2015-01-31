package controllers;

import constants.Constants;
import entity.Discipline;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class TermCreateController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showTermCreatePage(request, response);
        } else {
            insertTermCreate(request, response);
        }
    }

    public void showTermCreatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DisciplineService disciplineServise = new DisciplineServiceImpl();
        try {
            request.setAttribute("button", 1);
            List<Discipline> disciplineList = disciplineServise.getDisciplines();
            if (disciplineList.isEmpty()) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorListDB");
            }
            request.setAttribute("disciplines", disciplineList);
            gotoToJSP("main/termCreate.jsp", request, response);
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "pageError");
            forwardRequest("/admin/termsList.php", request, response);
        }
    }

    public void insertTermCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String strCount = request.getParameter("name");
            int countWeeks = Integer.parseInt(strCount);
        } catch (NumberFormatException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "validDate");
            forwardRequest("/admin/termCreate.php", request, response);
        }
    }
}
