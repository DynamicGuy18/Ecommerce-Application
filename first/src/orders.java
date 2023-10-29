import java.sql.ResultSet;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

public class orders {

    
    
    public static boolean placeOrder(Customer customer , Product product){
        String group_id="SELECT MAX(group_order_id)+1 id FROM orders";
       
        DBconnection dBconnection=new DBconnection();

        try {
            ResultSet rs=dBconnection.getQueryTable(group_id);
            if(rs.next()){

                 String placeorder="INSERT INTO orders(group_order_id,customer_id,product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                return dBconnection.updateDatabase(placeorder)!=0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;


    }

    public static int placeMultipleOrder(Customer customer , ObservableList<Product> listOfProducts){
        String group_id="SELECT MAX(group_order_id)+1 id FROM orders";
       
        DBconnection dBconnection=new DBconnection();

        try {
            ResultSet rs=dBconnection.getQueryTable(group_id);
            int count=0;
            if(rs.next()){

                for(Product product:listOfProducts){
                    String placeorder="INSERT INTO orders(group_order_id,customer_id,product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                   count+=dBconnection.updateDatabase(placeorder);
               
                }

                
                return count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return 0;


    }
}
