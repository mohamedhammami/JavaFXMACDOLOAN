package application.Donia;

import java.io.IOException;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Admin;
import tn.esprit.macdoloan.entity.Agent;
import tn.esprit.macdoloan.service.interf.IAdminServiceRemote;
import tn.esprit.macdoloan.service.interf.IAgentServiceRemote;

public class AddAdminController {
	
	

    @FXML
    private AnchorPane addad;

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
	    private JFXTextField salary;

	    @FXML
	    void addAdmin(ActionEvent event) throws NamingException {
	    	
	    	String jndiName = "macdoloan-ear/macdoloan-ejb/AdminService!tn.esprit.macdoloan.service.interf.IAdminServiceRemote";
			Context context =new InitialContext();
			IAdminServiceRemote proxy = (IAdminServiceRemote) context.lookup(jndiName);
			Admin c = new Admin();
			c.setFirstName(firstname.getText());
			c.setLastName(lastname.getText());
			c.setAdress(adress.getText());
			c.setAdresseMail(email.getText());
			c.setBirthDate(Date.valueOf(birthdate.getValue()));
			c.setCin(cin.getText());
			c.setLogin(login.getText());
			c.setPassword(cin.getText());
			c.setPhone(phone.getText());
			c.setSalary(Integer.valueOf(salary.getText()));
			c.setRole("Admin");
			proxy.addAdmin(c);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Admin ajouté!");
			alert.showAndWait();

			System.out.println("Admin Ajouté");

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
            addad.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

	    }
}
