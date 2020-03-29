package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Donia.SessionUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static application.Entry.connectedUser;

public class HomeUIController implements Initializable{

	@FXML
	private Label nameLB;
	@FXML
	private Pane windowPane;
	@FXML
	private AnchorPane HomeAnchropane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SessionUser sessionUser=new SessionUser();
		nameLB.setText("Hello, "+sessionUser.getUser().getFirstName()+" "+sessionUser.getUser().getLastName().toUpperCase());
		if(sessionUser.getUser().getRole().equals("Agent")){
			Pane newLoadedPane;
			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("Donia/ProfilAgent.fxml"));
				windowPane.getChildren().setAll(newLoadedPane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
			}
			else if(sessionUser.getUser().getRole().equals("Admin")){
				Pane newLoadedPane;
				try {
					newLoadedPane = FXMLLoader.load(getClass().getResource("Donia/ProfilAdmin.fxml"));
					windowPane.getChildren().setAll(newLoadedPane);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	@FXML
	public void goToPersonel (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Donia/ProfilAgent.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	
	@FXML
	public void goToClaims (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("ayoub/CLaimUI.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	
	@FXML
	public void goToAppointments (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Cyrine/Appointments.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	
	@FXML
	public void goToAccounts (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Cyrine/Accounts.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	
	@FXML
	public void goToBranchs (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Ayoub/BranchUI.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	@FXML
	public void goToLoans (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Mohamed/adminloanf.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	@FXML
	public void goToWithDawelDesposit (ActionEvent event) throws IOException {
	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Mohamed/deposit_withdrawel.fxml"));
	windowPane.getChildren().setAll(newLoadedPane); 
	}
	
	
	@FXML
    void logout(ActionEvent event) {
		
		  
    	SessionUser.dropSession();
    	Parent root;
        try {
        	root = FXMLLoader.load(getClass().getResource("FirstUI.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            //stage.setFullScreen(true);
            stage.show();
            // Hide this current window (if this is what you want)
            HomeAnchropane.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	@FXML
	private void closeStage(ActionEvent event){
		((Stage)nameLB.getScene().getWindow()).close();
	}
	
	
	

}
