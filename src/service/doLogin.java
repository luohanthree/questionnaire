package service;

import jakarta.servlet.http.*;
import utils.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


public class doLogin {
    /**
     *
     * @param userName 用户名
     * @param userPwd 用户密码
     * @return 返回是否登录成功
     * @throws SQLException
     */
    public int  login(String userName, String userPwd) throws SQLException {
        int id = -1;
        Connection conn = DBTools.getDatasource().getConnection();
        String selectSql = "select userPwd, userId from user where userName = ?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (userPwd.equals(rs.getString(1))){
                return rs.getInt(2);
            }
        }
        return id;
    }

}
