package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Donia.SessionUser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.interf.IUserServiceRemote;

public class FirstUIController implements Initializable {
	@FXML
	private ImageView userIMG;
	@FXML
	private ImageView moneyIMG;
	@FXML
	private JFXTextField usernameTF;
	@FXML
	private JFXPasswordField passwordTF;
	@FXML
	private JFXButton loginBTN;
	@FXML
	private AnchorPane AnchorLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//passwordTF.setText("12345678");
		//usernameTF.setText("ayoubinoh");

	}
	
	@FXML
    void login(ActionEvent event) throws NamingException {
    	
    	String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
    	Context context = new InitialContext();
    	IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
    
    	if (passwordTF.getText().trim().isEmpty() && usernameTF.getText().trim().isEmpty()) {
            Al("Entrez vote login entrez vote mot de passe");
            return;
           }else{ // Login Code
             
           } //
            if (usernameTF.getText().trim().isEmpty()){
           Al("Entrez vote login");
            
           }else if (passwordTF.getText().trim().isEmpty()){
             
            Al("entrez vote mot de passe");
           }else{
           
        	   User U = proxy.login(usernameTF.getText(), passwordTF.getText()) ;	
   			       System.out.println(U);
   			    SessionUser.createSession(U);
   			    if (SessionUser.getUser()==null)
				 {
					 Al("Inexisting Account !");
				 }else{
							
						    
				            if (SessionUser.getUser().getRole().equals("Admin"))
				            {
				            	System.out.println("c'est un Admin");
				            	Parent root;
				                try {
				                	root = FXMLLoader.load(getClass().getResource("HomeUI.fxml"));
				                    Stage stage = new Stage();
				                    stage.initStyle(StageStyle.UNDECORATED);
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
					                	root = FXMLLoader.load(getClass().getResource("HomeUI.fxml"));
					                    Stage stage = new Stage();
					                    stage.initStyle(StageStyle.UNDECORATED);
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
					
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@FXML
	/*private void loging(ActionEvent event) throws NamingException, IOException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/UserServiceImpl!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
		Context context = new InitialContext();
		IUserServiceRemote proxy = (IUserServiceRemote)context.lookup(jndiName);
		if(proxy.findUserByLogin(usernameTF.getText().toLowerCase(),passwordTF.getText()) instanceof User)
		{
			Entry.connectedUser=proxy.findUserByLogin(usernameTF.getText().toLowerCase(),passwordTF.getText());
			closeStage();
			loadHome();
		}
		else
		{
			System.out.println("Vérifier les coordonnées");
		}

	}
	
    private void closeStage() {
        ((Stage)usernameTF.getScene().getWindow()).close();
    }

    *//**
     * @throws IOException
     *//*
    private void loadHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
*/


