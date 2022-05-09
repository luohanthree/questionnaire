package servlets;

import com.alibaba.fastjson.JSONArray;
import dao.QuestionnaireDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "createForm", value = "/doCreate.do")
public class Create extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String userID = null;
        /*获取用户的Id*/
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                userID = cookie.getValue();
            }
        }
        String questionnaire = request.getParameter("jsonStr");
        String questionnaireName = request.getParameter("formName");
        if (questionnaireName.equals("")) response.sendError(400);
        JSONArray questions = JSONArray.parseArray(questionnaire);
        assert userID != null;
        QuestionnaireDao questionnaireDao = new QuestionnaireDao(Integer.parseInt(userID));
        try {
            questionnaireDao.createNew(questions, questionnaireName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
