import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class UserInterface {
    GridPane login;
    HBox headerbar;
    HBox footerbar;
    ProductList productList=new ProductList();
    VBox productPage;
    VBox body;
    Label welcomLabel;
    Button place=new Button("Place Order");
    ObservableList<Product> cartList=FXCollections.observableArrayList();
    public BorderPane create(){
        BorderPane pane=new BorderPane();
        pane.setPrefSize(800,600);
         pane.setTop(headerbar);
         body=new VBox();
         body.setPadding(new Insets(15));
         body.setAlignment(Pos.CENTER);
         pane.setCenter(body);
         pane.setBottom(footerbar);

         
       // pane.getChildren().add(login);//methods to add children or content to page
        //pane.setCenter(login);
        productPage=productList.getAllProducts();
      body.getChildren().add(productPage);
       
        
        return pane;
    }

    public UserInterface(){
        createLogin();
        createHeader();
        createFooter();
    }

     private void createLogin(){ // this creates login stuff 
        Text username=new Text("UserName ");
        Text password=new Text("Password ");

         TextField user=new TextField();
         user.setPromptText("Enter your Username");
         user.setText("saurabh");
         PasswordField pass=new PasswordField();
         pass.setPromptText("Enter your Password ");
         pass.setText("IKnowYou");

         Button button=new Button("Login");
         Label msgLabel=new Label();

         login =new GridPane();// gridpane is way of constructing things in grid format
         login.setStyle("-fx-background-color:orange;");
         
         login.setAlignment(Pos.CENTER);
         login.setPadding(new Insets(50));
         login.setHgap(10);
         login.setVgap(15);
         login.add(username, 0, 0);
         login.add(user,1,0);
         login.add(password,0,1);
         login.add(pass,1,1);
         login.add(msgLabel, 0, 2);
         login.add(button, 1, 2);

         button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
               
                
                String name=user.getText();
                String pas=pass.getText();
                Login login=new Login();
                loggedInCustomer=login.userLogin(name, pas);

                if(loggedInCustomer!=null){
                    
                    
                     msgLabel.setText("Successfully Login !" );
                     welcomLabel.setText("Welcome-"+ loggedInCustomer.getName());
                     headerbar.getChildren().add(welcomLabel);
                     body.getChildren().clear();
                    
                     body.getChildren().add(productPage);
                      footerbar.setVisible(true);


                }
                else{
                    msgLabel.setText("Invalid Credentials, Enter Correct Data");
                    welcomLabel.setText("login Failed !");
                    headerbar.getChildren().add(welcomLabel);
                }

            }
            
         });
        
    }
    Button buyButton;
    Button addCart;
    //Headerbar UI Code
    void createHeader(){ // this function creates header bar at top , it contains search button
        
        Image image=new Image("file:C:\\Users\\saurabh raut\\Desktop\\MAIN\\first\\src\\finalnew.png");
       ImageView view=new ImageView();

       view.setImage(image);
       view.setFitHeight(60);
       view.setFitWidth(100);
       StackPane root = new StackPane();
        root.getChildren().add(view);

        Button home=new Button("Home");


        TextField searchField=new TextField();
        searchField.setPromptText("Search Here ");
        searchField.setPrefWidth(180);

        Button search=new Button("Search");

        Button sign=new Button("Sign-In");
        welcomLabel =new Label();

        Button cart=new Button("My-Cart");

        Button myorder=new Button("My Orders");

        headerbar=new HBox(); // this is important to create header bar (HBOX)
        headerbar.setStyle("-fx-background-color:blue;");
        headerbar.setSpacing(15);
        headerbar.setPadding(new Insets(10));
        headerbar.setAlignment(Pos.CENTER_LEFT);
       
        headerbar.getChildren().addAll(view,home,searchField,search,sign,cart,myorder);
    

        sign.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                body.getChildren().clear();
                body.getChildren().add(login);
                footerbar.setVisible(false);
            }
            
        });

        cart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                
              body.getChildren().clear();
              VBox ProdPage=productList.getProductsInCart(cartList);
              ProdPage.setAlignment(Pos.CENTER);
              ProdPage.setSpacing(10);
              ProdPage.getChildren().add(place);
              body.getChildren().add(ProdPage);
              footerbar.setVisible(false);
           
            }
            
        });

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub

                String search=searchField.getText().trim();

                Product foundProduct=searchProduct(search);

                if(foundProduct!=null){
                   TableView <Product>ptTableView=new TableView<>();
                   TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    // Add the columns to the table.
    ptTableView.getColumns().addAll(idColumn, nameColumn, priceColumn);

    // Create an ObservableList containing the found product.
    ObservableList<Product> productData = FXCollections.observableArrayList(foundProduct);

    // Set the data for the product table.
    ptTableView.setItems(productData); 
    body.getChildren().clear();
    body.getChildren().add(ptTableView);

    
    buyButton.setOnAction(e -> {
        if (loggedInCustomer != null) {
            boolean status = orders.placeOrder(loggedInCustomer,foundProduct);
            if (status) {
                showDialog("Order Placed Successfully!");
            } else {  
                showDialog("Order Failed");
            }
        } else {
            showDialog("Please SignIn to Account to Place Order!");
        }
    });

    addCart.setOnAction(e -> {
        cartList.add(foundProduct);
        showDialog("Selected Item has been Added Successfully");
    });
               
}
                else{
                    showDialog("Item is Not Found !");
                }
                

            }
            
        });

        home.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerbar.setVisible(true);

                if(loggedInCustomer==null && headerbar.getChildren().indexOf(sign)==-1){
                    headerbar.getChildren().add(sign);
                }
                TableView<Product> productTable = new TableView<>();
                Product selectedProduct = productList.getSelecProduct();
                if (selectedProduct != null) {
                   productTable.getSelectionModel().select(selectedProduct);
                }
            }
            
        });

     
        place.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                 
               if(cartList==null){
                 showDialog("Please add some products in the Cart to place Order");
                 return;
               }
               if(loggedInCustomer==null){
                showDialog("Please SignIn to Account to Place Order!");
                return;
               }

               int count=orders.placeMultipleOrder(loggedInCustomer,cartList);
               if(count!=0){
                showDialog("Order for "+count+" products Placed Successfully");
               }
               else{
                showDialog("Order Failed");
               }

            }
            
        });

        myorder.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub

                body.getChildren().clear();
                
                displayPlacedOrders();
                footerbar.setVisible(false);

                 
                
            }
            
        });
        

    }
    Customer loggedInCustomer;

    // FOOTER below

    private void createFooter(){

          buyButton=new Button("Buy Now");
          addCart=new Button("Add to Cart");

          footerbar=new HBox(); // this is important to create header bar (HBOX)
        footerbar.setStyle("-fx-background-color:green;");
        footerbar.setSpacing(30);
        footerbar.setPadding(new Insets(15));
        footerbar.setAlignment(Pos.CENTER);

         footerbar.getChildren().addAll(buyButton,addCart);

         buyButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
               Product product=productList.getSelecProduct();
               if(product==null){
                 showDialog("Please Select Product to Order !");
                 return;
               }
               if(loggedInCustomer==null){
                showDialog("Please SignIn to Account to Place Order!");
                return;
               }

               boolean status=orders.placeOrder(loggedInCustomer, product);
               if(status==true){
                showDialog("Order Placed Successfully !");
               }
               else{
                showDialog("Order Failed");
               }

              
                
            }
            
         });

         addCart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub

                Product product=productList.getSelecProduct();
                if(product==null){
                    showDialog("Please Select the Product First to Add into Cart");
                  return;
                }
               
                cartList.add(product);
                showDialog("Selected Item has been Added Successfully");

                
            }
            
         });

        


    }

    private void displayPlacedOrders() {
    // Create a TableView to display placed orders details.
    TableView<Order> ordersTable = new TableView<>();

    // Define the columns for the orders table.
    TableColumn<Order, Integer> groupOrderIdColumn = new TableColumn<>("Group Order ID");
    groupOrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupOrderId"));

    TableColumn<Order, Integer> customerIdColumn = new TableColumn<>("Customer ID");
    customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    TableColumn<Order, Integer> productIdColumn = new TableColumn<>("Product ID");
    productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

    TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    TableColumn<Order, LocalDateTime> orderDateColumn = new TableColumn<>("Order Date");
    orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

    TableColumn<Order, String> orderStatusColumn = new TableColumn<>("Order Status");
    orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

    // Fetch placed orders from your database using the `Orders` class.
    ObservableList<Order> ordersData = Order.getAllOrders(); // Modify this to fetch orders from your database.

    // Set the data and columns for the orders table.
    ordersTable.setItems(ordersData);
    ordersTable.getColumns().addAll(groupOrderIdColumn, customerIdColumn, productIdColumn, quantityColumn, orderDateColumn, orderStatusColumn);

    // Create a new stage to display the orders table.
    body.getChildren().clear();
    body.getChildren().add(ordersTable);
}


    private void showDialog(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }

    private Product searchProduct(String searchTerm) {
        // Implement the search logic to find the product in your list.
        // Return the found product or null if not found.
        // You can use a for loop or any search logic based on your data structure.
        ObservableList<Product> data=Product.getAllpProducts();
        for (Product product : data) {
            if (product.getName().equalsIgnoreCase(searchTerm)) {
                return product;
            }
        }
        return null;
    }


} 
