package controllers;

import constants.Constants;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplineCreateController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showDisciplineCreatePage(request, response);
        } else {
            insertDisciplineCreate(request, response);
        }
    }

    public void showDisciplineCreatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            request.setAttribute("button", 1);
            gotoToJSP("main/disciplineCreate.jsp", request, response);
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "pageError");
            forwardRequest("/admin/disciplinesList.php", request, response);
        }
    }

    public void insertDisciplineCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        try {
            validateRequestDiscipline(name);
            DisciplineService disciplineServise = new DisciplineServiceImpl();
            if (disciplineServise.addDiscipline(name)) {
                forwardRequest("/admin/disciplinesList.php", request, response);
            } else {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorCreate");
                forwardRequest("/admin/disciplinesList.php", request, response);
            }
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorList");
            forwardRequest("/admin/disciplinesList.php", request, response);
        }
    }
}
