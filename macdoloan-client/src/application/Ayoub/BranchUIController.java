package application.Ayoub;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tn.esprit.macdoloan.service.interf.IBranchServiceRemote;

public class BranchUIController implements Initializable {
	@FXML
	private Label allLB;
	@FXML
	private Pane windowPane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String jndiName = "macdoloan-ear/macdoloan-ejb/BranchServiceImpl!tn.esprit.macdoloan.service.interf.IBranchServiceRemote";
		Context context;
		try {
			context = new InitialContext();
			IBranchServiceRemote proxy = (IBranchServiceRemote)context.lookup(jndiName);
			allLB.setText(proxy.findAllBranchs().size()+"");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void goToAllBranchs (ActionEvent event) throws IOException {
		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("AllBranchsUI.fxml"));
		windowPane.getChildren().setAll(newLoadedPane); 
	}	
	@FXML
	public void goToAddBranch (ActionEvent event) throws IOException {
		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("AddBranchUI.fxml"));
		windowPane.getChildren().setAll(newLoadedPane); 
	}
}
