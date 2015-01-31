package controllers;

import constants.Constants;
import services.TermService;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TermDeleteController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String str = request.getParameter("selected");
        TermService termService = new TermServiceImpl();
        try {
            int idTerm = Integer.parseInt(str);
          //  if (!termService.deleteTerm(idTerm)) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorDelete");
         //   }
          forwardRequest("/admin/termsList.php", request, response);

        } catch (NumberFormatException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorId");
            forwardRequest("/admin/termsList.php", request, response);
        }
    }
}
