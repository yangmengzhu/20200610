package com.yang.sql;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @program: 20200605
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -09 15 :14
 */

public class Use_DataSourse {
    public static void getConnectionUseDriverManger() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/huojianban_0605?charsetEncoding=utf8&useSSL=false";
        String user="root";
        String password="sg7188916";
        try(Connection connection= DriverManager.getConnection(url,user,password)){

        }
    }
    //写法比url更高效，支持连接池 支持url的方式指定连接参数
    public static void getConnectionUseDataSourse() throws SQLException {
        DataSource dataSource;
        MysqlDataSource mysqlDataSource=new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("sg7188916");
        mysqlDataSource.setDatabaseName("huojianban_0605");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");
        dataSource=mysqlDataSource;
        try(Connection connection=dataSource.getConnection()){

        }

    }
}
