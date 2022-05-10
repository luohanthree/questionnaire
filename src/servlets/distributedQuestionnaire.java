package servlets;

import dao.QuestionnaireDao;
import dao.SamplesDao;
import entity.Results;
import entity.Samples;
import entity.questionaire.Questionnaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DataANA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "distributedQuestionnaire", value = "/questionnaires/*")
public class distributedQuestionnaire extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        String formId = path.substring(path.lastIndexOf("/")+1);
        response.setContentType("text/html;utf-8");
        QuestionnaireDao questionnaireDao = new QuestionnaireDao();
        Questionnaire questionnaire = questionnaireDao.findOne(formId);
        request.setAttribute("questionnaire",questionnaire);
        request.getRequestDispatcher("/model.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
