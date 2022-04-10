package utils;



import java.sql.*;

/**
 * @author zhihuan
 */
public class DBTools {
    private static final String DBUser = "ques";
    private static final String JDBCURI = "jdbc:mariadb://localhost:3306/questinnaire";
    private static final String pwd = "1234QWER!@#$";
    private static Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(JDBCURI, DBUser, pwd);

        return conn;
    }

    /**
     *
     * @param sql 需要执行的sql语句
     * @return 返回结果集
     */
    public ResultSet query(String sql) throws SQLException {
        conn = getConnection();
        stat = conn.createStatement();
        rs = stat.executeQuery(sql);

        return rs;
    }

    /**
     *
     * @param preparedStatement 传入的预编译的sql语句
     * @param args 需要传入的值
     * @return 返回改变值的数量
     */
    public int update(PreparedStatement preparedStatement,String[] args) throws SQLException {
        conn = getConnection();
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }

        return preparedStatement.executeUpdate();
    }


    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        if (stat != null ) {
            stat.close();
        }
        if (rs != null ) {
            rs.close();
        }
        assert conn != null;

    }
}
