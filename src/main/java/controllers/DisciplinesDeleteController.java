package controllers;

import constants.Constants;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplinesDeleteController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String items = request.getParameter("listCheckbox");
        String delims = "[,]";
        String[] tokens = items.split(delims);
        DisciplineService disciplineServise = new DisciplineServiceImpl();

        for(String iter:tokens) {
            try {
                int id = Integer.parseInt(iter);
                if(!disciplineServise.deleteDiscipline(id)){
                    request.setAttribute(Constants.VALIDATION_MESSAGE,
                            "errorDelete");
                    forwardRequest("/admin/disciplinesList.php", request, response);
                    break;
                }
            } catch (NumberFormatException e) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorId");
                forwardRequest("/admin/disciplinesList.php", request, response);
            } catch (Exception e) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorList");
                forwardRequest("/admin/disciplinesList.php", request, response);
            }
        }
        forwardRequest("/admin/disciplinesList.php", request, response);
    }
}
