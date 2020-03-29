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

public class ListAdminController implements Initializable {
	@FXML
	private AnchorPane Anchor1;
	@FXML
	private TableView<User> AdminList;
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
	    
	    
		ObservableList<User> champs = FXCollections.observableArrayList();
		
		public static User user = new User();
		
		 @FXML
		    void supprimer(ActionEvent event) {
		    	
		    	String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
				Context context;
				try {
					context = new InitialContext();
					IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
					//int supp = proxy.deleteUser(id);
					
					User u=new User();
			        if (!AdminList.getSelectionModel().isEmpty()) {
			            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			            alert.setTitle("Confirmation");
			            alert.setHeaderText("Etes-vous sûr de vouloir supprimer?");

			            Optional<ButtonType> result = alert.showAndWait();
			            if (result.get() == ButtonType.OK) {
			            	int id_supp = AdminList.getSelectionModel().getSelectedItem().getId();
			            	proxy.deleteUser(AdminList.getSelectionModel().getSelectedItem().getId());
			            	System.out.println("client supprimé!");
			            	if (id_supp==SessionUser.getUser().getId())
			            	{
			            		SessionUser.dropSession();
			                	Parent root;
			                    try {
			                    	root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			                        Stage stage = new Stage();
			                        stage.setTitle("Profil Agent");
			                        stage.setScene(new Scene(root));
			                        //stage.setFullScreen(true);
			                        stage.show();
			                        // Hide this current window (if this is what you want)
			                        Anchor1.getScene().getWindow().hide();
			                    }
			                    catch (IOException e) {
			                        e.printStackTrace();
			                    }
			            	}else{
			            	Node node;
			        		try {
			        			node = (Node) FXMLLoader.load(getClass().getResource("ListClient.fxml"));
			        			Anchor1.getChildren().setAll(node);
			                }
			                catch (IOException e) {
			                    e.printStackTrace();
			                }}
			            } 
			        } else {
			            Alert alert = new Alert(Alert.AlertType.WARNING);
			            alert.setTitle("Warning");
			            alert.setHeaderText("Veuillez Selectionner un utilisateur!");
			            alert.showAndWait();
			        }
			    

					}catch (Exception e) {
						// TODO: handle exception
					}
		 }

		
		
		public void initialize(URL location, ResourceBundle resources) {

			String jndiName = "macdoloan-ear/macdoloan-ejb/UserService!tn.esprit.macdoloan.service.interf.IUserServiceRemote";
			Context context;
			try {
				context = new InitialContext();
				IUserServiceRemote proxy = (IUserServiceRemote) context.lookup(jndiName);
				List<User> list = proxy.getAdmins();
				System.out.println(list);
				ObservableList<User> champs = FXCollections.observableArrayList(list);
				firstname.setCellValueFactory(new PropertyValueFactory<User,String>("FirstName"));
				lastname.setCellValueFactory(new PropertyValueFactory<User,String>("LastName"));
				age.setCellValueFactory(new PropertyValueFactory<User,Date>("Birthdate"));
				cin.setCellValueFactory(new PropertyValueFactory<User,String>("Cin"));
				adress.setCellValueFactory(new PropertyValueFactory<User,String>("Adress"));
				email.setCellValueFactory(new PropertyValueFactory<User,String>("adresseMail"));
				
				
				
				AdminList.setItems(champs);
				


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
            stage.setScene(new Scene(root));
            //stage.setFullScreen(true);
            stage.show();
            // Hide this current window (if this is what you want)
            AdminList.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

	    }
		}
