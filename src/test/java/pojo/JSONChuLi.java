package pojo;

import java.sql.Connection;
import java.sql.DriverManager;

public class JSONChuLi {
    public static void main(String[] args) throws  Exception{





        Class.forName("com.mysql.cj.jdbc.Driver");
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://172.16.0.242:3306/db_work_flow","guoshiying","iG8CeqlNF6ukhx4l");














    }
}
