import java.sql.*;

import static java.lang.Class.forName;

public class dataBaseConnection {
    Connection con;
    public Connection GetConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book","root","2021065219@umt");
        }
        catch(Exception e){
            System.out.println("connection failed"+e.getMessage());
        }
        return con;
    }
}
