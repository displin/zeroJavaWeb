package org.smart4j.chapter3.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.util.PropertiesUtil;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *数据库操作辅助类
 *
 * Created by lin on 2016/5/9.
 */
public class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    private static String DRIVER = null;
    private static String URL = "";
    private static String USER_NAME = "";
    private static String PASSWORD = "";

    static {
        try {
            Properties configProperties = PropertiesUtil.loadProperties("config.properties");

            DRIVER = configProperties.getProperty("jdbc.driver");
            URL = configProperties.getProperty("jdbc.url");
            USER_NAME = configProperties.getProperty("jdbc.username");
            PASSWORD = configProperties.getProperty("jdbc.password");

            Class.forName(DRIVER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("FileNotFoundException: config.properties" + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("ClassNotFoundException: could not load DRIVER" + e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 成功时，数据库连接对象； 失败时，返回null
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("SQLException: get connection failed" + e);
        }

        return connection;
    }

    /**
     *关闭数据库连接
     *
     * @param connection 需要关闭的连接对象
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("SQLException: close connection failed" + e);
            }
        }
    }
}
