package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex
 * @version 1.0
 */

public class ErrorController extends AbstractWebtasksServletHandler{
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String notfound = request.getParameter("notfound");
        request.setAttribute("notfound", notfound);
        gotoToJSP("/error.jsp", request, response);
    }
}
