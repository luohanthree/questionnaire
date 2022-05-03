package servlets;

import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Questionnaires;

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
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                userID = cookie.getValue();
            }
        }
        String questionnaire = request.getParameter("jsonStr");
        JSONArray jsonArray = JSONArray.parseArray(questionnaire);
        assert userID != null;
        Questionnaires questionnaires = new Questionnaires(Integer.parseInt(userID));
        try {
            questionnaires.createNew(jsonArray);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
