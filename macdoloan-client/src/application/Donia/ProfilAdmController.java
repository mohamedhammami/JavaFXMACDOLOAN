package application.Donia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfilAdmController implements Initializable {
	@FXML
	private AnchorPane Profil;
	@FXML
	private Label firstname;
	@FXML
	private Label lastname;
	@FXML
	private ImageView profilepic;
	
	@FXML
    private AnchorPane Personalinfo;

    @FXML
    private Label firstnamelb;

    @FXML
    private Label lastnamelb;

    @FXML
    private Label cinlb;

    @FXML
    private Label loginlb;

    @FXML
    private Label phonelb;

    @FXML
    private Label emaillb;

    @FXML
    private Label adresslb;

    @FXML
    private Label salarylb;
    
    @FXML
    private AnchorPane Anchor1;
    
    @FXML
    private Button logoutbtn;
    
    

    @FXML
    void AddUser(ActionEvent event) {
    	
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("AddUser.fxml"));
			Anchor1.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}


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

    @FXML
    void ConsultUsers(ActionEvent event) {

    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("ConsultUser.fxml"));
			Anchor1.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Profile(ActionEvent event) {
    	
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));
			Anchor1.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
	
	public void initialize(URL location, ResourceBundle resources) {	
	}
}
