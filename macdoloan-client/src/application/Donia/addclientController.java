package application.Donia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.service.interf.IClientServiceRemote;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;

public class addclientController implements Initializable {

	
	@FXML
    private AnchorPane addcl;
	
	@FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXTextField post;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXDatePicker birthdate;

    @FXML
    private JFXTextField salary;
    

    @FXML
    private Hyperlink exit;
	// Event Listener on Button[#ajout].onAction
	@FXML
	public void addClient(ActionEvent event) throws NamingException, IOException {

		String jndiName = "macdoloan-ear/macdoloan-ejb/ClientService!tn.esprit.macdoloan.service.interf.IClientServiceRemote";
		Context context =new InitialContext();
		IClientServiceRemote proxy = (IClientServiceRemote) context.lookup(jndiName);
		Client c = new Client();
		c.setFirstName(firstname.getText());
		c.setLastName(lastname.getText());
		c.setAdress(adress.getText());
		c.setAdresseMail(email.getText());
		c.setBirthDate(Date.valueOf(birthdate.getValue()));
		c.setCin(cin.getText());
		c.setLogin(login.getText());
		c.setPassword(cin.getText());
		c.setPhone(phone.getText());
		c.setRole("Client");
		proxy.addClient(c);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Client ajouté!");
		alert.showAndWait();

		System.out.println("Client Ajouté");
	}
	
		
		@FXML
	    void exit(ActionEvent event) {
			System.out.println(SessionUser.getUser().getRole());
			if (SessionUser.getUser().getRole().equals("Admin")){
			Parent root;
	        try {
	        	root = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("Add new Agent");
	            stage.setScene(new Scene(root));
	            //stage.setFullScreen(true);
	            stage.show();
	            // Hide this current window (if this is what you want)
	            addcl.getScene().getWindow().hide();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }}else if (SessionUser.getUser().getRole().equals("Agent")){
	    		Parent root;
	            try {
	            	root = FXMLLoader.load(getClass().getResource("ProfilAgent.fxml"));
	                Stage stage = new Stage();
	                stage.setTitle("Profil Agent");
	                stage.setScene(new Scene(root));
	                //stage.setFullScreen(true);
	                stage.show();
	                // Hide this current window (if this is what you want)
	                addcl.getScene().getWindow().hide();
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }}

	    }
    

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
