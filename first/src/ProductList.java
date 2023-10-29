import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductList {
    
    public TableView<Product> productTable;
    public VBox createTable(ObservableList<Product> data){
        TableColumn id=new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn<>("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price=new TableColumn<>("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

      

        productTable =new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vbox=new VBox();
        vbox.setPadding(new Insets(15));
        vbox.getChildren().addAll(productTable);
        return vbox;

    }

   

    public VBox getAllProducts(){
        ObservableList<Product> data=Product.getAllpProducts();
        return createTable(data);
    }

    public Product getSelecProduct(){
        return productTable.getSelectionModel().getSelectedItem();
    }

    public VBox getProductsInCart(ObservableList<Product>data){
        return createTable(data);

    }

}
