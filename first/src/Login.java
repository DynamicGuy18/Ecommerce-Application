import java.sql.ResultSet;

public class Login {
    public Customer userLogin(String userName,String password){
        String query="SELECT * FROM customer WHERE username='"+userName+"' AND password='"+password+"'";

        DBconnection db=new DBconnection();
        try {
            ResultSet rs=db.getQueryTable(query);
            if(rs.next()){
               return new Customer(rs.getInt("ID"), rs.getString("name"), rs.getString("mobile_no"), rs.getString("username"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    // public static void main(String[] args) {
    //     Login login=new Login();
    //     Customer customer=login.userLogin("johndoe", "password123");
    //     System.out.println("Welcome"+ customer.getName());
    // }
}
