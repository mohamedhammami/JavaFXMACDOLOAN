package application.Donia;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.interf.IUserServiceRemote;;

public class Test extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception {
    		Parent root;
    		try {
    			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    			Scene scene = new Scene(root);
    			//primaryStage.setFullScreen(true);
    			primaryStage.setTitle("Hellooooooooooooooo!!");
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
	public static void main(String[] args) {
		launch(args);
		/*String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.interfaces.IUserServiceRemote";
		Context context;
		try {
			context = new InitialContext();
			IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
			User u = proxy.FindUserById(8);
			System.out.println(u.getFirstName());
	}catch (Exception e) {
	}*/
	}
}
