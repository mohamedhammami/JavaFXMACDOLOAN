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
import tn.esprit.macdoloan.service.interf.IClaimServiceRemote;

public class ClaimUIController implements Initializable{

	@FXML
	private Label unAnswredLB;
	@FXML
	private Label answredLB;
	@FXML
	private Label totalLB;
	@FXML
	private Pane windowPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String jndiName = "macdoloan-ear/macdoloan-ejb/ClaimServiceImpl!tn.esprit.macdoloan.service.interf.IClaimServiceRemote";
		Context context;
		try {
			context = new InitialContext();
			IClaimServiceRemote proxy = (IClaimServiceRemote)context.lookup(jndiName);
			totalLB.setText(""+proxy.findAllClaims().size());
			answredLB.setText(""+proxy.findAnswredClaims().size());
			unAnswredLB.setText(""+proxy.findNotAnswredClaims().size());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	public void goToAnswredClaims (ActionEvent event) throws IOException {
		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("AnswredCLaimsUI.fxml"));
		windowPane.getChildren().setAll(newLoadedPane); 
	}
	@FXML
	public void goToUnanswredClaims (ActionEvent event) throws IOException {
		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("UnanswredCLaimsUI.fxml"));
		windowPane.getChildren().setAll(newLoadedPane); 
	}	
	@FXML
	public void goToTotalClaims (ActionEvent event) throws IOException {
		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("TotalCLaimsUI.fxml"));
		windowPane.getChildren().setAll(newLoadedPane); 
	}
	@FXML
	public void goToStatisticsClaims (ActionEvent event) throws IOException {
		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("StatisticsCLaimsUI.fxml"));
		windowPane.getChildren().setAll(newLoadedPane); 
	}
}
