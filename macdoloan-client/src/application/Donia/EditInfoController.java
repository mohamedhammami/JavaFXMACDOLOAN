package application.Donia;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.service.interf.IAdminServiceRemote;
import tn.esprit.macdoloan.service.interf.IUserServiceRemote;

public class EditInfoController implements Initializable{
	
	@FXML
    private AnchorPane Personalinfo;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField lastename;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField adress;

    @FXML
    private DatePicker birthdate;

    @FXML
    private Button modif;

    @FXML
    private Hyperlink exit;

    @FXML
    void exit(ActionEvent event) {
    	
    	if (SessionUser.getUser().getRole().equals("Admin")){
    		Parent root;
            try {
            	root = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
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
            }}else if (SessionUser.getUser().getRole().equals("Agent")){
        		Parent root;
                try {
                	root = FXMLLoader.load(getClass().getResource("ProfilAgent.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("profil Agent");
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
    void modif(ActionEvent event) throws NamingException {

    	String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
		Context context =new InitialContext();
		IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation");
         alert.setHeaderText("Etes-vous sûr?");

         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
            proxy.modif(SessionUser.getUser().getId(), firstname.getText(), lastename.getText(), adress.getText(), email.getText(), phone.getText(), Date.valueOf(birthdate.getValue()), cin.getText(), login.getText());
         	
         	System.out.println("client modifié!");
         	int id_currentuser = SessionUser.getUser().getId();
         	SessionUser.dropSession();
    		SessionUser.createSession(proxy.FindUserById(id_currentuser));
         	Node node;
     		try {
     			node = (Node) FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));
     			Personalinfo.getChildren().setAll(node);
             }
             catch (IOException e) {
                 e.printStackTrace();
             }
		
		
         
         }
    	
    	
    }
    
    
    
public void initialize(URL location, ResourceBundle resources) {
    	firstname.setEditable(true);
    	lastename.setEditable(true);
    	adress.setEditable(true);
    	birthdate.setEditable(true);
    	cin.setEditable(true);
    	email.setEditable(true);
    	login.setEditable(true);
    	phone.setEditable(true);
    	
    	firstname.setText(SessionUser.getUser().getFirstName());
    	lastename.setText(SessionUser.getUser().getLastName());
    	adress.setText(SessionUser.getUser().getAdress());
    	birthdate.setValue(LocalDate.parse(SessionUser.getUser().getBirthDate().toString()));
    	cin.setText(SessionUser.getUser().getCin());
    	email.setText(SessionUser.getUser().getAdresseMail());
    	login.setText(SessionUser.getUser().getLogin());
    	phone.setText(SessionUser.getUser().getPhone());
    	
    }

}
