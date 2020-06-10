package com.yang.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: 20200605
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -08 17 :33
 */

public class MysqlClient1 {
    private static String host="127.0.0.1";
    private static int port=3306;
    private static String user="";
    private static String password="";
    private static String defaultDatebaseName="";
    public static void main(String[] args) throws ClassNotFoundException {
        if(args.length==0){
            printUsageAndExit();
        }
        parseArguments(args);
        System.out.println(host);
        System.out.println(port);
        System.out.println(user);
        System.out.println(password );
        System.out.println(defaultDatebaseName);
        //进行数据库连接
        Class.forName("com.mysql.jdbc.Driver");
        String url=String.format("jdbc:mysql://%s:%d/%s?useSSL=" +
                "false&charsetEncoding=utf8",host,port,defaultDatebaseName);
        try {
            Connection connection= DriverManager.getConnection(url,user,password);
            printWelcome();
            Scanner scan=new Scanner(System.in);
            System.out.println("mysql> ");
            while(true){
               String sql=scan.nextLine();
               if(sql.equalsIgnoreCase("quit") ){
                   break;
               }
                System.out.println("mysql> ");
               sql+=scan.nextLine();
            }
        } catch (SQLException e) {
            printUsageAndExit(e.toString());
        }
    }

    private static void printWelcome() {
        System.out.println("欢迎使用msqlClient");
    }

    private static void parseArguments(String[] args){
        defaultDatebaseName=args[args.length-1];
        args= Arrays.copyOfRange(args,0,args.length-1);
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
                    printUsageAndExit();
            }
        }
    }

    private static void printUsageAndExit(String... message) {
        System.out.println("使用帮助: mysql[选项] [默认数据库] ");
        System.out.println("--help          打印帮助信息");
        System.out.println("-h, --host      连接主机，默认是 127.0.0.1");
        System.out.println("-p, --port      连接端口，默认是 3306");
        System.out.println("-u, --user      用户名，必须设置");
        System.out.println("-p, --password  mysql 密码");
        for (String s:message) {
            System.out.println(s);
        }
    }
}
