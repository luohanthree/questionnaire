package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

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
        Logger logger = Logger.getLogger("register");
        response.setContentType("text/html;utf-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("username");
        String pwd = request.getParameter("password");
        UserManager doRegister = new UserManager();
        logger.info(userName + " " + pwd);
        try {
            if (doRegister.register(userName, pwd)) {
                out.println("注册成功，将在3秒后前往个人主页");
                response.setHeader("refresh", "3;URL=home.html");
            } else {
                out.println("注册失败，用户名已被占用，请重新注册");
                response.setHeader("refresh", "1;URL=register.html");
            }
        } catch (SQLException e) {
            out.println("发生未知错误，请联系管理员");
            response.setHeader("refresh","5;URL=index.html");
            e.printStackTrace();
        }

    }
}
