package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.UserManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author zhihuan
 */
@WebServlet(name = "RegisterServlet", value = "/doRegister.do")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;utf-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        UserManager doRegister = new UserManager();
        try {
            if (doRegister.register(userName, pwd)) {
                out.println("注册成功，将在3秒后前往个人主页");
                response.setHeader("refresh","3;URL=home.jsp");
            } else {
                out.println("注册失败，用户名已被占用，请重新注册");
                response.setHeader("refresh","1;URL=register.html");
            }
        } catch (SQLException e) {
            out.println("发生未知错误，请联系管理员");
            response.setHeader("refresh","5;URL=index.jsp");
            e.printStackTrace();
        }

    }
}
