package utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zhihuan
 */
public class DBTools {



    public static DataSource getDatasource() {
        DataSource dataSource = null;
        Properties properties = new Properties();
        InputStream inputStream = DBTools.class.getClassLoader().getResourceAsStream("/druid.properties");
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static void release(Statement statement, ResultSet resultSet) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void release(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}
