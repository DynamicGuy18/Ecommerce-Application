import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Order {
    private SimpleIntegerProperty groupOrderId;
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty productId;
    private SimpleIntegerProperty quantity;
    private SimpleObjectProperty<LocalDateTime> orderDate;
    private SimpleStringProperty orderStatus;

    public Order(int groupOrderId, int customerId, int productId, int quantity, LocalDateTime orderDate, String orderStatus) {
        this.groupOrderId = new SimpleIntegerProperty(groupOrderId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.productId = new SimpleIntegerProperty(productId);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.orderDate = new SimpleObjectProperty<>(orderDate);
        this.orderStatus = new SimpleStringProperty(orderStatus);
    }

   
   
    public int getGroupOrderId() {
        return groupOrderId.get();
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public int getProductId() {
        return productId.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public LocalDateTime getOrderDate() {
        return orderDate.get();
    }

    public String getOrderStatus() {
        return orderStatus.get();
    }

     public static ObservableList<Order> getAllOrders() {
        String query = "SELECT group_order_id, customer_id, product_id, quantity, order_date, order_status FROM orders";
        return fetchOrders(query);
    }


     public static ObservableList<Order> fetchOrders(String query) {
        ObservableList<Order> orderData = FXCollections.observableArrayList();
        DBconnection dBconnection = new DBconnection();

        try {
            ResultSet rs = dBconnection.getQueryTable(query);

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("group_order_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getTimestamp("order_date").toLocalDateTime(),
                    rs.getString("order_status")
                );
                orderData.add(order);
            }

            return orderData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
   




}

