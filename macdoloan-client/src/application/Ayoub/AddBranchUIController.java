package application.Ayoub;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tn.esprit.macdoloan.entity.Branch;
import tn.esprit.macdoloan.service.interf.IBranchServiceRemote;



public class AddBranchUIController implements Initializable{
	@FXML
	private Pane windowPane;
	@FXML
	private StackPane stack;

	@FXML
	private JFXTextField nameTF;
	@FXML
	private JFXTextField streetTF;
	@FXML
	private JFXTextField postalCodeTF;
	@FXML
	private JFXTextField cityTF;

	@Override
	public void initialize(URL url, ResourceBundle rb) {


	}

	@FXML
	public void add (ActionEvent event) {
		if(isPostalCodeValid()
				&& nameTF.getText().length()>0 
				&& 
				cityTF.getText().length()>0 
				&& streetTF.getText().length()>0)
		{
			String jndiName = "macdoloan-ear/macdoloan-ejb/BranchServiceImpl!tn.esprit.macdoloan.service.interf.IBranchServiceRemote";
			Context context;
			try {
				context = new InitialContext();
				IBranchServiceRemote proxy = (IBranchServiceRemote)context.lookup(jndiName);
				Branch branch = new Branch();
				branch.setStreet(streetTF.getText());
				branch.setName(nameTF.getText());
				branch.setPostalCode(postalCodeTF.getText());
				branch.setCity(cityTF.getText());
				proxy.addBranch(branch);
				nameTF.setText("");
				streetTF.setText("");
				postalCodeTF.setText("");
				cityTF.setText("");
				promptDialog();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(nameTF.getText().length()>0 && cityTF.getText().length()>0 && streetTF.getText().length()>0){
			promptCheckPostalCodeDialog();
		}
		else{
			promptCheckInputsDialog();
		}

	}
	private boolean isPostalCodeValid() {
		try{
			return (Integer.parseInt(postalCodeTF.getText())>=1000 && Integer.parseInt(postalCodeTF.getText())<=9183);

		}
		catch (Exception e) {
			return false;
		}
	}

	private void promptCheckInputsDialog() {
		JFXDialogLayout cont = new JFXDialogLayout();
		cont.setBody(new Text("You need to fill in all the branch informations \n"));
		cont.setHeading(new Text("Missing informations"));
		JFXDialog dialog = new JFXDialog(stack, cont, JFXDialog.DialogTransition.CENTER);
		JFXButton close = new JFXButton("close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});

		cont.setActions(close);
		dialog.show();

	}

	private void promptDialog() {
		JFXDialogLayout cont = new JFXDialogLayout();
		cont.setBody(new Text("You've added a new branch successfuly, do you wish \n"
				+ "to add another branch or to consult all branchs ?"));
		cont.setHeading(new Text("Branch added"));
		JFXDialog dialog = new JFXDialog(stack, cont, JFXDialog.DialogTransition.CENTER);
		JFXButton addbranch = new JFXButton("Add another branch");
		addbranch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
		JFXButton allbranchs = new JFXButton("Consult all branchs");
		allbranchs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
				try {
					Pane newLoadedPane = FXMLLoader.load(getClass().getResource("AllBranchsUI.fxml"));
					windowPane.getChildren().setAll(newLoadedPane); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		cont.setActions(addbranch,allbranchs);
		dialog.show();
	}

	private void promptCheckPostalCodeDialog() {
		JFXDialogLayout cont = new JFXDialogLayout();
		cont.setBody(new Text("You need to enter a valid postal code\n"));
		cont.setHeading(new Text("check postal code"));
		JFXDialog dialog = new JFXDialog(stack, cont, JFXDialog.DialogTransition.CENTER);
		JFXButton close = new JFXButton("close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});

		cont.setActions(close);
		dialog.show();
	}
}
