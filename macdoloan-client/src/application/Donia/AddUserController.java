package application.Donia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddUserController {
	@FXML
	private AnchorPane Anchor1;

	// Event Listener on Button.onAction
	@FXML
	public void AddnewAdmin(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("AddAdmin.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void AddnewAgent(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("AddAgent.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void AddnewClient(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("AddClient.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}
