package controllers;

import constants.Constants;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentsDeleteController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String items = request.getParameter("listCheckbox");
        String delims = "[,]";
        String[] tokens = items.split(delims);
        StudentService studentService = new StudentServiceImpl();
        for (String iter : tokens) {
            try {
                int id = Integer.parseInt(iter);
                if (!studentService.studentDelete(id)) {
                    request.setAttribute(Constants.VALIDATION_MESSAGE,
                            "errorDelete");
                    forwardRequest("/admin/studentsList.php", request, response);
                    break;
                }
            } catch (NumberFormatException e) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorId");
                forwardRequest("/admin/studentsList.php", request, response);
            } catch (Exception e) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorList");
                forwardRequest("/admin/studentsList.php", request, response);
            }
        }
        forwardRequest("/admin/studentsList.php", request, response);
    }
}
