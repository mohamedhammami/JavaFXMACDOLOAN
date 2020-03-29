package application.Donia;

import java.awt.Color;
import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.interf.IAdminServiceRemote;
import tn.esprit.macdoloan.service.interf.IUserServiceRemote;

public class LoginController {
	 @FXML
	    private JFXTextField mail;

	    @FXML
	    private JFXTextField password;

	    @FXML
	    private JFXButton login;

	    @FXML
	    private JFXButton forgotpass;
	    
	    @FXML
	    private AnchorPane AnchorLogin;


	    @FXML
	    void forgotpass(ActionEvent event) {
	    	


	    }

	    @FXML
	    void login(ActionEvent event) throws NamingException {
	    	
	    	String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
	    	Context context = new InitialContext();
	    	IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
	    
	    	if (password.getText().trim().isEmpty() && mail.getText().trim().isEmpty()) {
	            Al("Entrez vote email entrez vote mot de passe");
	            return;
	           }else{ // Login Code
	             
	           } //
	            if (mail.getText().trim().isEmpty()){
	           Al("Entrez vote email");
	            
	           }else if (password.getText().trim().isEmpty()){
	             
	            Al("entrez vote mot de passe");
	           }else{
	           
	        	   User U = proxy.login(mail.getText(), password.getText()) ;	
	   			       System.out.println(U);
	   			    SessionUser.createSession(U);
	   			    if (SessionUser.getUser()==null)
					 {
						 Al("Inexisting Account !");
					 }else{
      
								Al("you are connected !");
								
							    
					            if (SessionUser.getUser().getRole().equals("Admin"))
					            {
					            	System.out.println("c'est un Admin");
					            	Parent root;
					                try {
					                	root = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
					                    Stage stage = new Stage();
					                    stage.setTitle("Profil Admin");
					                    stage.setScene(new Scene(root));
					                    //stage.setFullScreen(true);
					                    stage.show();
					                    // Hide this current window (if this is what you want)
					                    AnchorLogin.getScene().getWindow().hide();
					                }
					                catch (IOException e) {
					                    e.printStackTrace();
					                }
								
					           
					            	
					            }else if (SessionUser.getUser().getRole().equals("Agent"))
					            {
					         
						            	System.out.println("c'est un Agent");
						            	Parent root;
						                try {
						                	root = FXMLLoader.load(getClass().getResource("/application/HomeUI.fxml"));
						                    Stage stage = new Stage();
						                    stage.setTitle("Profil Agent");
						                    stage.setScene(new Scene(root));
						                    //stage.setFullScreen(true);
						                    stage.show();
						                    // Hide this current window (if this is what you want)
						                    AnchorLogin.getScene().getWindow().hide();
						                }
						                catch (IOException e) {
						                    e.printStackTrace();
						                }
					            
					            
					            
					            }
	               
						}
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
