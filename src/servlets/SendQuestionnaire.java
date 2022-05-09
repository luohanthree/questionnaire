package servlets;

import dao.QuestionnaireDao;
import entity.questionaire.Questionnaire;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "getQues", value = "/getQues.do")
public class SendQuestionnaire extends HttpServlet {
    Logger logger = Logger.getLogger("servlets.SendQuestionnaire");
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
        try {
            QuestionnaireDao questionnaireDao = new QuestionnaireDao(id);
            List<Questionnaire> questionnaireList = new ArrayList<>();
            if (formId == null) {
                questionnaireList = questionnaireDao.findAll(id);
            } else {
                questionnaireList.add(questionnaireDao.findOne(formId));
            }
            request.setAttribute("questionnaires", questionnaireList);
            request.setAttribute("url",url);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
