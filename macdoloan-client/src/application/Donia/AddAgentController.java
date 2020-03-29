package application.Donia;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Agent;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.service.interf.IAgentServiceRemote;
import tn.esprit.macdoloan.service.interf.IClientServiceRemote;

public class AddAgentController implements Initializable{
	

	

    @FXML
    private AnchorPane addag;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXDatePicker birthdate;

    @FXML
    private JFXTextField cin;
    
    @FXML
    private JFXTextField post;


    @FXML
    void addAgent(ActionEvent event)  throws IOException, NamingException{
    	String jndiName = "macdoloan-ear/macdoloan-ejb/AgentService!tn.esprit.macdoloan.service.interf.IAgentServiceRemote";
		Context context =new InitialContext();
		IAgentServiceRemote proxy = (IAgentServiceRemote) context.lookup(jndiName);
		Agent c = new Agent();
		c.setFirstName(firstname.getText());
		c.setLastName(lastname.getText());
		c.setAdress(adress.getText());
		c.setAdresseMail(email.getText());
		c.setBirthDate(Date.valueOf(birthdate.getValue()));
		c.setCin(cin.getText());
		c.setLogin(login.getText());
		c.setPassword(cin.getText());
		c.setPhone(phone.getText());
		c.setPost(post.getText());
		c.setRole("Agent");
		proxy.addAgent(c);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Agent ajouté!");
		alert.showAndWait();

		System.out.println("Agent Ajouté");
	}


    @FXML
    void exit(ActionEvent event) {
		
		Parent root;
        try {
        	root = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add new Agent");
            stage.setScene(new Scene(root));
            //stage.setFullScreen(true);
            stage.show();
            // Hide this current window (if this is what you want)
            addag.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}    

	
	
	
	
	
    }



