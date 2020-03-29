package application.Donia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class ConsultUserController {
	@FXML
	private AnchorPane Anchor1;

	// Event Listener on Button.onAction
	@FXML
	public void Admins(ActionEvent event) {
		
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("ListAdmin.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void Agents(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("ListAgent.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void Clients(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("ListClient.fxml"));
			Anchor1.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}
