import java.sql.DriverManager;
import java.util.Collection;
import java.sql.*;
/*
 * @program: 20200605
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -05 19 :33
 */

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException ,SQLException{
        //注册驱动（选择乙方）
        Class.forName("com.mysql.jdbc.Driver");
        //建议连接
        String defaultDatabaseName="huojianban_0605";
        String password="sg7188916";
        String user="root";
        String url="jdbc:mysql://127.0.0.1:3306/"+defaultDatabaseName+
                "?useSSL=false&characterEncoding=utf8";
        Connection connection= DriverManager.getConnection(url,user,password);
        System.out.println(connection);
        queryDemo(connection);
        updateDemo(connection);
        //关闭连接
        connection.close();
    }

    private static void updateDemo(Connection connection ) throws SQLException {
        Statement  statement=connection.createStatement();
        String sql="insert into student_0605 (sn,name,sex) values ('4','小杨','女')";
        int affectedRows=statement.executeUpdate(sql);
        System.out.printf("Query OK,%d row affected%n",affectedRows);
        statement.close();
    }

    private static void queryDemo(Connection connection) throws SQLException {
        //执行SQL语言，获取数据库返回的结果（show databases/select...)
        //语句的抽象对象
        Statement statement=connection.createStatement();
        String sql="select * from student_0605";
        ResultSet resultSet=statement.executeQuery(sql);
        int count=0;
        while(resultSet.next()){//结果集一开始指向的是无效行
            String id=resultSet.getString(1);//下标从1开始
            String sn=resultSet.getString(2);
            String name=resultSet.getString(3);
            String sex=resultSet.getString(4);
        }
        statement.close();
    }
}
