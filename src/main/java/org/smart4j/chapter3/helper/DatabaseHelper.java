package org.smart4j.chapter3.helper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.util.ColletionUtil;
import org.smart4j.chapter3.util.PropertiesUtil;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *数据库操作辅助类
 *
 * Created by lin on 2016/5/9.
 */
public final class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

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
        Connection connection = CONNECTION_THREAD_LOCAL.get();

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("SQLException: get connection failed" + e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /**
     *关闭数据库连接
     *
     */
    public static void closeConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("SQLException: close connection failed" + e);
            }
            finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param entityClass
     * @param sql
     * @param parameters
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... parameters) {
        List<T> entityList;

        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            logger.error("query entity list failed.", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return entityList;
    }

    /**
     * 查询实体
     *
     * @param entityClass
     * @param sql
     * @param parameters
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... parameters) {
        T entity;

        try {
            Connection connection = CONNECTION_THREAD_LOCAL.get();
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            logger.error("query entity failed.", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return entity;
    }

    /**
     * 执行查询语句
     *
     * @param sql
     * @param parameters
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... parameters) {
        List<Map<String, Object>> result;

        try {
            Connection connection = getConnection();
            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), parameters);
        } catch (SQLException e) {
            logger.error("executeQuery failed.", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return result;
    }

    /**
     * 执行更新语句， 包括insert,delete,update
     * @param sql
     * @param parameters
     * @return
     */
    public static int executeUpdate(String sql, Object... parameters) {
        int rows = 0;

        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, parameters);
        } catch (SQLException e) {
            logger.error("executeUpdate failed.", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return rows;
    }

    /**
     * 插入实体
     *
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return true-成功，false-失败
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (ColletionUtil.isEmpty(fieldMap)) {
            logger.error("can't insertEntity: fieldMap is empty.");
            return false;
        }

        String sql = "insert into " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }

        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " values " + values;

        Object [] parameters = fieldMap.values().toArray();

        return executeUpdate(sql, parameters) == 1;
    }

    /**
     * 更新实体
     *
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return true-成功，false-失败
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if (ColletionUtil.isEmpty(fieldMap)) {
            logger.error("can't updateEntity: fieldMap is empty.");
            return false;
        }

        String sql = "update " + getTableName(entityClass) + " set ";
        StringBuilder columns = new StringBuilder("");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }

        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id=?";

        List<Object> parameterList = new ArrayList<Object>();
        parameterList.addAll(fieldMap.values());
        parameterList.add(id);
        Object [] parameters = parameterList.toArray();

        return executeUpdate(sql, parameters) == 1;
    }

    /**
     * 删除实体
     *
     * @param entityClass
     * @param id
     * @param <T>
     * @return true-成功，false-失败
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = "delete from " + getTableName(entityClass) + " where id=?";
        return executeUpdate(sql, id) == 1;
    }

    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }
}
