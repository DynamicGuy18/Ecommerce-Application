import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;


/**
 * App
 */
public class App extends Application{
   UserInterface userInterface=new UserInterface();

   

   public void start(Stage stage) {
    Scene scene=new Scene(userInterface.create());
    stage.setTitle("Ecommerce Platform");
    stage.setScene(scene);
    stage.show();

   }
    


   public static void main(String[] args) {
    launch();
   }
    
}