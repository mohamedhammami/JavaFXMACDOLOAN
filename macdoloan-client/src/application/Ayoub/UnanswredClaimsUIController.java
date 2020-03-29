package application.Ayoub;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import application.Entry;
import tn.esprit.macdoloan.entity.Admin;
import tn.esprit.macdoloan.entity.Answer;
import tn.esprit.macdoloan.entity.Claim;
import tn.esprit.macdoloan.service.interf.IClaimServiceRemote;

public class UnanswredClaimsUIController implements Initializable {
	private ArrayList<Claim> unanswredClaimsList;

	private Claim c;

	@FXML
	private StackPane vide;
	@FXML
	private Pane details;
	@FXML
	private Pagination paginator;
	@FXML
	private AnchorPane box1;
	@FXML
	private Label object1;
	@FXML
	private Label date1;
	@FXML
	private Label description1;
	@FXML
	private Label user1;
	@FXML
	private AnchorPane box2;
	@FXML
	private Label object2;
	@FXML
	private Label date2;
	@FXML
	private Label description2;
	@FXML
	private Label user2;
	@FXML
	private AnchorPane box3;
	@FXML
	private Label object3;
	@FXML
	private Label date3;
	@FXML
	private Label description3;
	@FXML
	private Label user3;


	@FXML
	private Label claimDescriptionDetail;
	@FXML
	private Label claimDateDetail;
	@FXML
	private Label claimObjectDetail;
	@FXML
	private Label claimNameDetail;
	@FXML
	private Label claimUsernameDetail;
	@FXML
	private JFXTextArea adminAnswerDetail;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initComponents();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private void initComponents() throws NamingException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/ClaimServiceImpl!tn.esprit.macdoloan.service.interf.IClaimServiceRemote";
		Context context;
		IClaimServiceRemote proxy;
		unanswredClaimsList = new ArrayList<Claim>();
		context = new InitialContext();
		proxy = (IClaimServiceRemote)context.lookup(jndiName);
		unanswredClaimsList=(ArrayList<Claim>) proxy.findNotAnswredClaims();
		
		details.setVisible(false);
		if (unanswredClaimsList.isEmpty()) {
			box1.setVisible(false);
			box2.setVisible(false);
			box3.setVisible(false);
			vide.setVisible(true);
			paginator.setVisible(false);
		} else {
			paginator.setVisible(true);
			vide.setVisible(false);
			setNbPages();
			showPage(0);
		}
	}

	private void setNbPages() {
		if (unanswredClaimsList.size() % 3 != 0) {
			paginator.setPageCount((unanswredClaimsList.size() / 3) + 1);
		} else {
			paginator.setPageCount(unanswredClaimsList.size() / 3);
		}

		paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
			showPage(newIndex.intValue());
		});		
	}

	private void showPage(int i) {
		paginator.setCurrentPageIndex(i);
		List<Claim> threeItems = getClaimsPage(i);     
		if (threeItems.size() >= 1) {
			box1.setVisible(true);
			object1.setText("Object : "+threeItems.get(0).getObject());
			description1.setText(threeItems.get(0).getDescription());
			date1.setText(String.valueOf(threeItems.get(0).getDateSend()).substring(0, 16));
			user1.setText(threeItems.get(0).getClient().getFirstName() +" "+threeItems.get(0).getClient().getLastName().toUpperCase());
			box1.setOnMouseClicked((MouseEvent e) -> {
				initializeDetails(threeItems.get(0));
				details.setVisible(true);
				c=threeItems.get(0);
			});

		} 
		else { 
			box1.setVisible(false);
		}
		if (threeItems.size() >= 2){
			box2.setVisible(true);  
			object2.setText("Object : "+threeItems.get(1).getObject());
			description2.setText(threeItems.get(1).getDescription());
			date2.setText(String.valueOf(threeItems.get(1).getDateSend()).substring(0, 16));
			user2.setText(threeItems.get(1).getClient().getFirstName() +" "+threeItems.get(1).getClient().getLastName().toUpperCase());
			box2.setOnMouseClicked((MouseEvent e) -> {
				initializeDetails(threeItems.get(1));
				details.setVisible(true);
				c=threeItems.get(1);
			});
		} 
		else{
			box2.setVisible(false);
		}
		if (threeItems.size() >= 3){
			box3.setVisible(true);
			object3.setText("Object : "+threeItems.get(2).getObject());
			description3.setText(threeItems.get(2).getDescription());
			date3.setText(String.valueOf(threeItems.get(2).getDateSend()).substring(0, 16));
			user3.setText(threeItems.get(2).getClient().getFirstName() +" "+threeItems.get(2).getClient().getLastName().toUpperCase());
			box3.setOnMouseClicked((MouseEvent e) -> {
				initializeDetails(threeItems.get(2));
				details.setVisible(true);
				c=threeItems.get(2);
			});
		} 
		else{
			box3.setVisible(false); 
		}
	}
	private void initializeDetails(Claim claim) {
		claimDescriptionDetail.setText(claim.getDescription());
		claimDateDetail.setText(String.valueOf(claim.getDateSend()).substring(0, 16));
		claimObjectDetail.setText("Object : "+claim.getObject());
		claimNameDetail.setText("Name : "+claim.getClient().getFirstName()+" "+claim.getClient().getLastName().toUpperCase());
		claimUsernameDetail.setText("Username : "+claim.getClient().getLogin());
	}

	private List<Claim> getClaimsPage(int i) {
		int start = 3 * i;
		int fin = start + 3;
		if (unanswredClaimsList.size() > start) {
			if (unanswredClaimsList.size() > fin) {
				return unanswredClaimsList.subList(start, fin);
			} else {
				return unanswredClaimsList.subList(start, unanswredClaimsList.size());
			}
		}
		return unanswredClaimsList.subList(0, 2);  
	}
	@FXML
	private void cancel(ActionEvent event) {
		details.setVisible(false);
	}
	@FXML
	private void send(ActionEvent event) throws NamingException {
		if(adminAnswerDetail.getText().length()>5)
		{
			String jndiName = "macdoloan-ear/macdoloan-ejb/ClaimServiceImpl!tn.esprit.macdoloan.service.interf.IClaimServiceRemote";
			Context context;
			IClaimServiceRemote proxy;
			context = new InitialContext();
			proxy = (IClaimServiceRemote)context.lookup(jndiName);
			Answer a= new Answer();
			a.setAdmin((Admin)Entry.connectedUser);
			a.setDescription(adminAnswerDetail.getText());
			a.setDateSend(new Date());
			proxy.answerClaim(c, a);
			initComponents();
		}
		else{
			System.out.println("you must write a proper answer");
		}
	}
}
