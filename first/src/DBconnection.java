import java.sql.*;
import java.sql.Statement;

public class DBconnection {
    
    private final String url="jdbc:mysql://localhost:3306/ecommerce";
    private final String username="root";
    private final String password="Saurabh@1999";

    private Statement gStatement(){
       try {
        Connection connection=DriverManager.getConnection(url, username, password);
        return connection.createStatement();
        
       } catch (Exception e) {
        e.printStackTrace();
        
       }
       return null;
    }

    public ResultSet getQueryTable(String query){
        try {
          Statement statement=gStatement();
          return statement.executeQuery(query);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;

    }

    public int updateDatabase(String query){
        try {
          Statement statement=gStatement();
          return statement.executeUpdate(query);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return 0;
    }

    public static void main(String[] args) {
        DBconnection db=new DBconnection();
        ResultSet res=db.getQueryTable("SELECT * FROM customer");

        if(res!=null){
            System.out.println("Connection is Successful");
        }
        else{
            System.out.println("Connection Failed !");
        }
    }


    
}
