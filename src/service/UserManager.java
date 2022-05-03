package service;

import utils.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhihuan
 */
public class UserManager {
    public boolean register(String userName, String pwd) throws SQLException {
        Connection conn = DBTools.getDatasource().getConnection();
        String selectSql = "select userName from user where userName = ?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(selectSql);
        ps.setString(1,userName);
        if (!ps.executeQuery().next()) {
            String updateSql = "INSERT INTO user (userName, userPwd) VALUES (?, ?)";
            ps = conn.prepareStatement(updateSql);
            ps.setString(1, userName);
            ps.setString(2, pwd);
            ps.executeUpdate();
            return true;
        }
        DBTools.release(ps);
        return false;
    }

    public void deleteOne(String userName, String pwd) throws SQLException {
        Connection conn = DBTools.getDatasource().getConnection();
        String selectSql = "select userPwd from user where userName = ?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getString("userPwd").equals(pwd)) {
                String deleteSql = "delete from user where userName = ?";
                conn.prepareStatement(deleteSql).executeUpdate();
            }
        }

        DBTools.release(ps,rs);
    }
}
