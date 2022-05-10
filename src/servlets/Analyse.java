package servlets;

import dao.QuestionnaireDao;
import entity.questionaire.Questionnaire;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ANA", value = "/ANA/*")
public class Analyse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String path = request.getRequestURI();
//        String formId = path.substring(path.lastIndexOf("/")+1);
//        QuestionnaireDao questionnaireDao = new QuestionnaireDao();
//        Questionnaire questionnaire = questionnaireDao.findOne(formId);
//        request.setAttribute("questionnaire",questionnaire);
//        request.getRequestDispatcher("dataANA.jsp").forward(request, response);

    }
}
