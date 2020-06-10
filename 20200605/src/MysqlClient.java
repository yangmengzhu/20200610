import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.*;
import java.util.Scanner;

/**
 * @program: 20200605
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -05 21 :16
 */

public class MysqlClient {
    private static String host="127.0.0.1";
    private static int port=3306;
    private static String user="";
    private static String password="";
    private static String defaultDatebaseName="";

    public static void main(String[] args) throws ClassNotFoundException
            , SQLException {
        System.out.println(Arrays.toString(args));
        if(args.length==0){
            printUsageAndExit();
        }
        //进行数据库连接
        Class.forName("com.mysql.jdbc.Driver");
        String url=String.format("jdbc:mysql://%s:%d/%s?useSSL=" +
                "false&charsetEncoding=utf8", host,port,defaultDatebaseName);
        System.out.println("DEBUG:url=" +url);
        System.out.println("DEBUG:user=" +user);
        System.out.println("DEBUG: password=" +password);
        //一次连接
        Connection connection= DriverManager.getConnection(url,user,password);
        printWelcome();
        Scanner scan=new Scanner(System.in);
        //进入之前的MySQL界面
        System.out.println("mysql> ");
        //一次连接，多次执行SQL执行
        while(true){
            String cmd=scan.nextLine();
            //输入quit退出mysql界面
            if(cmd.equalsIgnoreCase("quit") ){
                break;
            }
            //模拟mysql中不输入一行分号命令就不结束
            while(!cmd.endsWith(";") ){
                System.out.println("->");
                cmd+=scan.nextLine();
            }
            cmd=cmd.substring(0,cmd.length()-1);
            System.out.println("DEBUG"+cmd);
            //select 和show 开头的sql是有返回值的，所以使用ResultSet的查询方式
            //否则使用excecuteUpdate的执行方式
            connection.close();
        }
        parseArguments(args);
        System.out.println(host);
        System.out.println(port);
        System.out.println(user);
        System.out.println(password );
        System.out.println(defaultDatebaseName);
    }
    private static void printWelcome(){
        System.out.println("欢迎使用 MySQLClient");
        System.out.println();
    }
    //解析参数，获取连接数据库的信息
    public static void parseArguments(String[] args){
       defaultDatebaseName=args[args.length-1];
       args=Arrays.copyOfRange(args,0,args.length-1);
        for (int i = 0; i < args.length; i++) {
            String arg=args[i];
            switch(arg){
                case "--help":
                    printUsageAndExit(args);
                case "-h":
                case "--host":
                    host=args[++i];
                    break;
                case "-P":
                case "--port":
                    port=Integer.parseInt(args[++i]);
                    break;
                case "-u":
                case "--user":
                    user=args[++i];
                    break;
                case "-p":
                case "--p":
                    password=args[++i];
                    break;
                default:
                    printUsageAndExit(args);
            }
        }
    }
    public static void printUsageAndExit(String... message){
      System.out.println("使用帮助：mysql[选项]默认数据库");
        System.out.println("--help   打印帮助信息");
        System.out.println("-h,--host   连接主机，默认是 127.0.0.1");
        System.out.println("-p,--port  连接顿口，默认是 3306");
        System.out.println("-u,--user  mysql 用户名，必须设置");
        System.out.println("-p,--possword mysql 密码");

        for (String s:message) {
            System.out.println(s);
        }
        System.exit(1) ;
    }
}
