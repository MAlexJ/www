package controllers;

import constants.Constants;
import entity.Discipline;
import exeptions.InvalidDataException;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplineModifyingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showDisciplineModifyingPage(request, response);
        } else {
            updateDisciplineModifying(request, response);
        }
    }

    protected void showDisciplineModifyingPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String idStr = request.getParameter("listCheckbox");
            int id = Integer.parseInt(idStr);
            if (id <= 0) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorId");
                forwardRequest("/admin/disciplinesList.php", request, response);
            }
            DisciplineService disciplineServise = new DisciplineServiceImpl();
            Discipline discipline = disciplineServise.getDisciplineById(id);
            request.setAttribute("discipline", discipline);
            request.setAttribute("button", 2);
            gotoToJSP("main/disciplineCreate.jsp", request, response);
        } catch (NumberFormatException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorId");
            forwardRequest("/admin/disciplinesList.php", request, response);
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "pageError");
            forwardRequest("/admin/disciplinesList.php", request, response);
        }
    }

    protected void updateDisciplineModifying(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            String name = request.getParameter("name");
            validateRequestDiscipline(name);
            DisciplineService disciplineServise = new DisciplineServiceImpl();
            disciplineServise.updateStudentById(id,name);
            forwardRequest("/admin/disciplinesList.php", request, response);
        } catch (NumberFormatException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorId");
            forwardRequest("/admin/disciplinesList.php", request, response);
        } catch (InvalidDataException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorModify");
            forwardRequest("/admin/disciplinesList.php", request, response);
        }catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "pageError");
            forwardRequest("/admin/disciplinesList.php", request, response);
        }
    }

}
