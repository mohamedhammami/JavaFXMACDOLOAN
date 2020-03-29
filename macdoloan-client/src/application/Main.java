package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Parent root;
		try {
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//root = FXMLLoader.load(getClass().getResource("/main/resources/AddAccount.fxml"));
			//root = FXMLLoader.load(getClass().getResource("/main/resources/DisplayAccounts.fxml"));
			root = FXMLLoader.load(getClass().getResource("/application/Donia/AddAdmin.fxml"));
			//root = FXMLLoader.load(getClass().getResource("/main/resources/Appointments.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Hellooooooooooooooo!!");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
