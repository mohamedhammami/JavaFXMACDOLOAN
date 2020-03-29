package application.Donia;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.interf.IUserServiceRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class ListAgentController implements Initializable {
	@FXML
	private AnchorPane Anchor1;
	@FXML
	private TableView<User> AgentList;
	@FXML
	private TableColumn<User, String> firstname;
	@FXML
	private TableColumn<User, String> lastname;
	@FXML
	private TableColumn<User, Date> age;
	@FXML
	private TableColumn<User, String> cin;
	@FXML
	private TableColumn<User, String> email;
	@FXML
	private TableColumn<User, String> adress;
	
	@FXML
    private Button supp;
	
	 @FXML
	    void supprimer(ActionEvent event) {
	    	
	    	String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
			Context context;
			try {
				context = new InitialContext();
				IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
				//int supp = proxy.deleteUser(id);
				
				User u=new User();
		        if (!AgentList.getSelectionModel().isEmpty()) {
		            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		            alert.setTitle("Confirmation");
		            alert.setHeaderText("Etes-vous s�r de vouloir supprimer?");

		            Optional<ButtonType> result = alert.showAndWait();
		            if (result.get() == ButtonType.OK) {
		            	proxy.deleteUser(AgentList.getSelectionModel().getSelectedItem().getId());
		            	System.out.println("client supprim�!");
		            	Node node;
		        		try {
		        			node = (Node) FXMLLoader.load(getClass().getResource("ListClient.fxml"));
		        			Anchor1.getChildren().setAll(node);
		                }
		                catch (IOException e) {
		                    e.printStackTrace();
		                }
		            } 
		        } else {
		            Alert alert = new Alert(Alert.AlertType.WARNING);
		            alert.setTitle("Warning");
		            alert.setHeaderText("Veuillez Selectionner un serveur!");
		            alert.showAndWait();
		        }
		    

				}catch (Exception e) {
					// TODO: handle exception
				}
	 }
	 
	 @FXML
	    void exit(ActionEvent event) {
	    Parent root;
     try {
     	root = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
         Stage stage = new Stage();
         stage.setTitle("Add new Agent");
         stage.setScene(new Scene(root, 450, 450));
         stage.setFullScreen(true);
         stage.show();
         // Hide this current window (if this is what you want)
         AgentList.getScene().getWindow().hide();
     }
     catch (IOException e) {
         e.printStackTrace();
     }

	    }
	
	public void initialize(URL location, ResourceBundle resources) {

		String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
		Context context;
		try {
			context = new InitialContext();
			IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
			List<User> list = proxy.getAgents();
			System.out.println(list);
			ObservableList<User> champs = FXCollections.observableArrayList(list);
			firstname.setCellValueFactory(new PropertyValueFactory<User,String>("FirstName"));
			lastname.setCellValueFactory(new PropertyValueFactory<User,String>("LastName"));
			age.setCellValueFactory(new PropertyValueFactory<User,Date>("Birthdate"));
			cin.setCellValueFactory(new PropertyValueFactory<User,String>("Cin"));
			adress.setCellValueFactory(new PropertyValueFactory<User,String>("Adress"));
			email.setCellValueFactory(new PropertyValueFactory<User,String>("adresseMail"));
			
			
			
			AgentList.setItems(champs);
			


}catch (Exception e) {
	// TODO: handle exception
}		
	}
	
	

}
