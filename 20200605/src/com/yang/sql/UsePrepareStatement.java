package com.yang.sql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Scanner;

/**
 * @program: 20200605
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -09 15 :35
 */

public class UsePrepareStatement {
    private static DataSource dataSource = null;
    private static DataSource initDataSourse(){
        MysqlDataSource mysqlDataSource=new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("sg7188916");
        mysqlDataSource.setDatabaseName("huojianban_0605");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");
        return mysqlDataSource;
    }
    public static Connection   getConnectionUseDataSourse() throws SQLException {
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
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        initDataSourse();
        Scanner scan=new Scanner(System.in);
        try(Connection c=getConnectionUseDataSourse()){
            System.out.println("请输入英语获奖分数");
            try(Statement s= c.createStatement()){
                int input=scan.nextInt();
                String sql="select * from exam_result where english> "+input;
                try(ResultSet resultSet=s.executeQuery(sql) ){
                    while(resultSet.next()){
                        String id=resultSet.getString("id");
                        String name=resultSet.getString("name");
                        String english=resultSet.getString("name");
                        String chinese=resultSet.getString("name");
                        String math=resultSet.getString("name");
                        System.out.println(id+","+name+","+english+","+math+","+chinese+",");
                    }
                }
            }
        }
        try(Connection c=getConnectionUseDataSourse()){
            System.out.println("请输入英语获奖分数");
            int ci=scan.nextInt();
            //提前写好SQL 使用？作为占位符
            String sql="select * from exam_result where english>? ";
            try(PreparedStatement s=c.prepareStatement(sql) ){
                //使用具体的值代替占位符
               s.setInt(1,ci);
               try(ResultSet r=s.executeQuery()){
                   while(r.next()){
                       String id=r.getString("id");
                       String name=r.getString("name");
                       String english=r.getString("name");
                       String chinese=r.getString("name");
                       String math=r.getString("name");
                       System.out.println(id+","+name+","+english+","+math+","+chinese+",");
                   }
               }
            }
        }
    }
}
