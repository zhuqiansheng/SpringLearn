package com.njupt.mybatis.utils;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class jdbcUtil {

    //处理数据库事务的提交事务
    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //事务的回滚
    public static void rollBack(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //事务的开始
    //因为批量操作必须手动提交，在批量操作之前，关掉自动提交
    public static void begin(Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */

    public static Connection getConnection() {
        Connection conn = null;
        Properties properties = new Properties();
        InputStream is = jdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
            String driver = properties.getProperty("driver");
            Class.forName(driver);

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            conn = DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

    /**
     * 关闭资源
     */
    public static void close(Connection conn, Statement stmt) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        close(conn, stmt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
