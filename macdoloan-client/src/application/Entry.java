package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.macdoloan.entity.User;

public class Entry extends Application{
	public static User connectedUser;
	
	@Override
	public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FirstUI.fxml"));
        
        Scene scene = new Scene(root);
      
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
	}
    
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
