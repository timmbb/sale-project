import java.sql.*;

public class Database {
    public Connection conn;
    public Statement stmt;
    public ResultSet rs=null;
    public String sqlStr="";
    public Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url ="jdbc:mysql://localhost:3306/jgproject?&useSSL=false&serverTimezone=UTC";
            conn=DriverManager.getConnection(url,"root","95279426txy%");
            stmt = conn.createStatement ();
        }catch(Exception ee){
            System.out.println("connect db error:"+ee.getMessage());
        }
        return conn;
    }
}
