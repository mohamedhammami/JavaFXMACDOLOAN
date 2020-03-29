package application.Donia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.User;

public class PersonalInfoController implements Initializable{
	@FXML
	private AnchorPane Personalinfo;
	@FXML
	private Label firstnamelb;
	@FXML
	private Label lastnamelb;
	@FXML
	private Label cinlb;
	@FXML
	private Label loginlb;
	@FXML
	private Label phonelb;
	@FXML
	private Label emaillb;
	@FXML
	private Label adresslb;
	@FXML
	private Label agelb;

	// Event Listener on Hyperlink.onAction
	@FXML
	public void exit(ActionEvent event) {
		Parent root;
        try {
        	root = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add new Agent");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            Personalinfo.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }	}
	
	public void initialize(URL location, ResourceBundle resources) {
	 
		User user =SessionUser.getUser();
		
		adresslb.setText(user.getAdress());
		cinlb.setText(user.getCin());
		emaillb.setText(user.getAdresseMail());
		firstnamelb.setText(user.getFirstName());
		lastnamelb.setText(user.getLastName());
		loginlb.setText(user.getLogin());
		phonelb.setText(user.getPhone());
		int age = LocalDate.now().getYear()-user.getBirthDate().getYear()-1900;
		System.out.println(LocalDate.now().getYear());
		System.out.println(user.getBirthDate().getYear());
		System.out.println(age);
		agelb.setText(String.valueOf(age));
		
		
	}
	
	 @FXML
	    void modif(ActionEvent event) {
		 
		 Node node;
			try {
				node = (Node) FXMLLoader.load(getClass().getResource("EditInfo.fxml"));
				Personalinfo.getChildren().setAll(node);
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
}
