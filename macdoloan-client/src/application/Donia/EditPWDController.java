package application.Donia;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXPasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.service.interf.IUserServiceRemote;

public class EditPWDController {

	
	
	 @FXML
	    private AnchorPane Personalinfo;
	 @FXML
	    private JFXPasswordField currentpwd;

	    @FXML
	    private JFXPasswordField newpwd;

	    @FXML
	    private JFXPasswordField newpwd2;

	    @FXML
	    void exit(ActionEvent event) {
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
		            Personalinfo.getScene().getWindow().hide();
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
		                Personalinfo.getScene().getWindow().hide();
		            }
		            catch (IOException e) {
		                e.printStackTrace();
		            }}


	    }

	    @FXML
	    void updatepwd(ActionEvent event) throws NamingException {
	    	
	    	 if (newpwd.getText().trim().isEmpty()){
		           Al("Invalid new password!");
		            
		           } else
	    	if (!(SessionUser.getUser().getPassword().equals(currentpwd.getText())))
	    	{
	    	    Al("invalid current password!");	
	    	}else if (newpwd.getText().equals(newpwd2.getText()))
	    	{
	    		String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
	    		Context context =new InitialContext();
	    		IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
	    		proxy.modifpassword(SessionUser.getUser().getId(), newpwd.getText());
	    		
	    		Al("Password updated");
	    		
	    	}else {
	    		Al("erreur!");
	    	}
	    	
	    	
	    }
	    
	    public void Al(String txt){
	        Alert alert = new Alert(AlertType.INFORMATION);
	           alert.setHeaderText(null);
	           alert.setTitle("Information Dialog");
	           alert.setContentText(txt);
	           alert.showAndWait();
	       }
}
