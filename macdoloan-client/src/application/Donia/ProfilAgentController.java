package application.Donia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class ProfilAgentController implements Initializable {
	@FXML
	private AnchorPane Profil;
	@FXML
	private Label firstname;
	@FXML
	private Label lastname;
	@FXML
	private ImageView profilepic;
	@FXML
	private AnchorPane Anchor1;
	@FXML
    private Button logoutbtn;

	// Event Listener on Button.onAction
	@FXML
	public void Profile(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));
			Anchor1.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// Event Listener on Button.onAction
	@FXML
	public void ConsultUsers(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("ListClient.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void AddUser(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("addclient.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }	}
	
	public void initialize(URL location, ResourceBundle resources) {	
	}
	
	@FXML
    void updatepwd(ActionEvent event) {
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("EditPWD.fxml"));
			Anchor1.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

}
