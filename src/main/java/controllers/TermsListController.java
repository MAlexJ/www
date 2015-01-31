package controllers;

import constants.Constants;
import entity.Term;
import services.TermService;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

public class TermsListController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showTermsListFirstTerm(request, response);
        } else {
            showTermsListSelectTerm(request, response);
        }
    }

    protected void showTermsListFirstTerm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TermService termService = new TermServiceImpl();
        List<Term> terms = termService.getTerms();
        if (terms.isEmpty()) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorListDB");
        } else {
            LinkedList<Term> termList = new LinkedList<>();
            for (Term iter : terms) {
                termList.add(iter);
            }
            Term firstTerm = termList.getFirst();
            request.setAttribute("firstTerm", firstTerm);
            int idSelected = firstTerm.getId();
            request.setAttribute("idSelected", idSelected);
            request.setAttribute("terms", terms);
        }
        gotoToJSP("main/termsList.jsp", request, response);
    }

    protected void showTermsListSelectTerm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String idStr = request.getParameter("select");
            int id = Integer.parseInt(idStr);
            TermService termService = new TermServiceImpl();
            List<Term> terms = termService.getTerms();
            if (terms.isEmpty()) {
                request.setAttribute(Constants.VALIDATION_MESSAGE,
                        "errorListDB");
            }
            LinkedList<Term> termList = new LinkedList<>();
            for (Term iter : terms) {
                if (iter.getId() == id) {
                    termList.add(iter);
                    break;
                }
            }
            Term firstTerm = termList.getFirst();
            request.setAttribute("firstTerm", firstTerm);
            int idSelected = firstTerm.getId();
            request.setAttribute("idSelected", idSelected);
            request.setAttribute("terms", terms);
            gotoToJSP("main/termsList.jsp", request, response);
        } catch (NumberFormatException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "errorId");
            forwardRequest("/admin/termsList.php", request, response);
        } catch (Exception e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    "pageError");
            forwardRequest("/admin/termsList.php", request, response);
        }

    }


}
