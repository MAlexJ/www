package controllers;

import constants.Constants;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;


public class StudentCreateController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showStudentCreatePage(request, response);
        } else {
            insertStudentCreate(request, response);
        }
    }

    protected void showStudentCreatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            request.setAttribute("button", 1);
            gotoToJSP("main/studentsCreate.jsp", request, response);
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "pageError");
            forwardRequest("/admin/studentsList.php", request, response);
        }

    }

    protected void insertStudentCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String group = request.getParameter("group");
        String dateStudent = request.getParameter("date");
        try {
            validateRequestStudent(lastName, firstName, group, dateStudent);
            Date date = convertingToData(dateStudent);
            StudentService studentService = new StudentServiceImpl();
            if (studentService.studentAdd(lastName, firstName, group, date)) {
                forwardRequest("/admin/studentsList.php", request, response);
            } else {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorCreate");
                forwardRequest("/admin/studentsList.php", request, response);

            }
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorList");
            forwardRequest("/admin/studentsList.php", request, response);
        }
    }
}
