package servlets;

import dao.QuestionnaireDao;
import dao.SamplesDao;
import entity.Results;
import entity.questionaire.Question;
import entity.questionaire.Questionnaire;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@WebServlet(name = "getQues", value = "/getQues.do")
public class getAllQuestionnaires extends HttpServlet {
    Logger logger = Logger.getLogger("servlets.getAllQuestionnaires");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String formId = request.getParameter("formId");
        int id = -1;
        ServletContext context = request.getServletContext();
        String url = "http://localhost:8080/questionnaire/questionnaires/";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                id = Integer.parseInt(cookie.getValue());
            }
        }
        Map<UUID, Results> results = new HashMap<>();
        try {
            QuestionnaireDao questionnaireDao = new QuestionnaireDao(id);
            List<Questionnaire> questionnaireList = new ArrayList<>();
            if (formId == null) {
                questionnaireList = questionnaireDao.findAll(id);
                for (Questionnaire questionnaire : questionnaireList){
                    UUID eachFormId = questionnaire.getFormId();
                    SamplesDao samplesDao = new SamplesDao();
                    results.put(eachFormId, samplesDao.getData(eachFormId));
                }
            } else {
                questionnaireList.add(questionnaireDao.findOne(formId));
            }
            request.setAttribute("results",results);
            request.setAttribute("questionnaires", questionnaireList);
            request.setAttribute("url",url);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
