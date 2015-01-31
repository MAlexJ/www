package controllers;

import constants.Constants;
import entity.Student;
import exeptions.InvalidDataException;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;


public class StudentModifingController extends AbstractWebtasksServletHandler {

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showStudentModifyingPage(request, response);
        } else {
            updateStudentModifying(request, response);
        }
    }

    protected void showStudentModifyingPage(HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        try {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            if (id <= 0) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorId");
                forwardRequest("/admin/studentsList.php", request, response);
            }
            StudentService studentService = new StudentServiceImpl();
            Student student = studentService.getStudentById(id);
            request.setAttribute("student", student);
            request.setAttribute("button", 2);
            gotoToJSP("main/studentsCreate.jsp", request, response);
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

    protected void updateStudentModifying(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        try {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            String lastName = request.getParameter("last_name");
            String firstName = request.getParameter("first_name");
            String group = request.getParameter("group");
            String dateStr = request.getParameter("date");
            Date date = convertingToData(dateStr);
            validateRequestStudent(lastName, firstName, group, dateStr);
            StudentService studentService = new StudentServiceImpl();
            if (!studentService.updateStudentById(id, lastName, firstName, group, date)) {
                gotoToJSP("main/studentsCreate.jsp", request, response);
            }
            forwardRequest("/admin/studentsList.php", request, response);
        } catch (NumberFormatException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorId");
            forwardRequest("/admin/studentsList.php", request, response);
        } catch (InvalidDataException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorModify");
            forwardRequest("/admin/studentsList.php", request, response);
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorList");
            forwardRequest("/admin/studentsList.php", request, response);
        }

    }
}
