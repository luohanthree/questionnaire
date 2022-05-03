package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jetbrains.annotations.NotNull;
import service.doLogin;
import utils.DBTools;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

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
        Logger logger = Logger.getLogger("loginServlet");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;utf-8");
        HttpSession session = request.getSession();
        Cookie[] doLogin = request.getCookies();
//        try {
//            if (autoLogin(doLogin) && doLogin != null) {
//                response.sendRedirect("home.jsp");
//            }
//        } catch (SQLException e) {
//            out.println("自动登录发生错误，请清空浏览器缓存重试!");
//            e.printStackTrace();
//        }
        doLogin login = new doLogin();
        String userName = request.getParameter("username");
        String userPwd = request.getParameter("password");
        int id;
        try {
            id = login.login(userName, userPwd);
            if (id != -1) {
                logger.info("登陆成功");
                if (auto.equals(request.getParameter("auto"))) {
                    session.setAttribute("userName", userName);
                    Cookie cookieUserName = new Cookie("userName", userName);
                    Cookie cookieUserPwd = new Cookie("pwd", userPwd);
                    cookieUserName.setMaxAge(60 * 24 * 60);
                    cookieUserPwd.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookieUserName);
                    response.addCookie(cookieUserPwd);
                }
                Cookie cookieId = new Cookie("id", String.valueOf(id));
                cookieId.setPath("/");
                cookieId.setMaxAge(60*60*24);
                response.addCookie(cookieId);
                response.sendRedirect("home.html");
            } else {
                out.println("<script>alert( " + "用户名或密码错误" + ")");
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean autoLogin(Cookie @NotNull [] cookies) throws SQLException {

        String userName = cookies[0].getValue();
        String userPwd = cookies[1].getValue();
        String dbPwd;
        boolean isAutoLogin = false;
        Connection conn = DBTools.getDatasource().getConnection();
        String sql = "select userName, userPwd from user where userName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            dbPwd = rs.getString("userPwd");
            if (userPwd.equals(dbPwd)) {
                isAutoLogin = true;
            }
        }

        return isAutoLogin;
    }

}
