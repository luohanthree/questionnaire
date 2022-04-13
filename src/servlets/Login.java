package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.doLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author zhihuan
 */
@WebServlet(name = "doLogin.do", value = "/doLogin.do")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auto = "on";
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;utf-8");
        HttpSession doLogin = request.getSession();
        doLogin login = new doLogin();
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("pwd");
        try {
            if (login.login(userName, userPwd) ) {
                if (auto.equals(request.getParameter("auto"))) {
                    doLogin.setAttribute("userName",userName);
                    doLogin.setAttribute("userPwd",userPwd);
                }
                response.setHeader("refresh","0;url=home.jsp");
            } else {
                out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
