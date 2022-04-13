package service;

import utils.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhihuan
 */
public class DoRegister {
    public boolean register(String userName, String pwd) throws SQLException {
        boolean isRegistered = false;
        Connection conn = DBTools.getDatasource().getConnection();
        String[] args = {userName, pwd};
        String selectSql = "select userName from user where userName = ?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(selectSql);
        assert ps != null;
        ps.setString(1,userName);
        if (!ps.executeQuery().next()) {
            isRegistered = true;
            String updateSql = "INSERT INTO user (userName, userPwd) VALUES (?, ?)";
            ps = conn.prepareStatement(updateSql);
            ps.setString(1, userName);
            ps.setString(2, pwd);
        }
        DBTools.release(ps);
        return isRegistered;
    }
}
