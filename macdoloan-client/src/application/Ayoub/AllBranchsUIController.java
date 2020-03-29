package application.Ayoub;


import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tn.esprit.macdoloan.entity.Admin;
import tn.esprit.macdoloan.entity.Agent;
import tn.esprit.macdoloan.entity.Branch;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.service.interf.IBranchServiceRemote;

public class AllBranchsUIController implements Initializable{
	@FXML
	private Pane windowPane;
	@FXML
	private Pane details;
	@FXML
	private StackPane stack;
	@FXML
	private JFXTextField search;
	@FXML
	private TableView<Branch> tableView;
	@FXML
	private TableColumn<Branch, String> nameCol;
	@FXML
	private TableColumn<Branch, String> cityCol;
	@FXML
	private Label nameLB;
	@FXML
	private Label streetLB;
	@FXML
	private Label postalCodeLB;
	@FXML
	private Label cityLB;
	@FXML
	private Label clientsLB;
	@FXML
	private Label adminsLB;
	@FXML
	private Label agentsLB;
	@FXML
	private JFXTextField nameEdit;

	@FXML
	private JFXTextField streetEdit;

	@FXML
	private JFXTextField postalCodeEdit;

	@FXML
	private JFXTextField cityEdit;
	@FXML
	private JFXButton changebtn;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadPage();
	}


	private void loadPage() {
		String jndi = "macdoloan-ear/macdoloan-ejb/BranchServiceImpl!tn.esprit.macdoloan.service.interf.IBranchServiceRemote";
		Context context;

		try {
			context = new InitialContext();
			IBranchServiceRemote proxy = (IBranchServiceRemote) context.lookup(jndi);
			ObservableList<Branch> oblist=FXCollections.observableArrayList(proxy.findAllBranchs());

			nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
			tableView.setItems(oblist);
			search.textProperty().addListener((observable, oldValue, newValue) -> {
				try {
					filterBranch(oldValue, newValue);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			tableView.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> showDetails(newValue));
			showDetails(null);
			details.setVisible(false);
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	private void showDetails(Branch newValue) {
		if(newValue!=null){
			nameLB.setText(newValue.getName());
			streetLB.setText(newValue.getStreet());
			postalCodeLB.setText(newValue.getPostalCode()+"");
			cityLB.setText(newValue.getCity()+"");
			clientsLB.setText(newValue.getBranchUsers().stream().filter(e->e instanceof Client).count()+"");
			adminsLB.setText(newValue.getBranchUsers().stream().filter(e->e instanceof Admin).count()+"");
			agentsLB.setText(newValue.getBranchUsers().stream().filter(e->e instanceof Agent).count()+"");
		}
		else{
			nameLB.setText("");
			streetLB.setText("");
			postalCodeLB.setText("");
			cityLB.setText("");
			clientsLB.setText("");
			adminsLB.setText("");
			agentsLB.setText("");
		}
	}


	private void filterBranch(String oldValue, String newValue) throws NamingException {
		String jndi = "macdoloan-ear/macdoloan-ejb/BranchServiceImpl!tn.esprit.macdoloan.service.interf.IBranchServiceRemote";
		Context context = new InitialContext();
		IBranchServiceRemote proxy = (IBranchServiceRemote) context.lookup(jndi);

		ObservableList<Branch> filteredList = FXCollections.observableArrayList();
		if (search.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
			ObservableList<Branch> oblist=FXCollections.observableArrayList(proxy.findAllBranchs());
			tableView.setItems(oblist) ;
		}
		else {
			newValue = newValue.toUpperCase();
			for (Branch e : tableView.getItems()) {
				if (String.valueOf(e.getName()).toUpperCase().contains(newValue) || String.valueOf(e.getStreet()).toUpperCase().contains(newValue)|| String.valueOf(e.getCity()).toUpperCase().contains(newValue)) {
					filteredList.add(e);
				}
			}
			tableView.setItems(filteredList);
		}
	}

	@FXML
	private void edit(ActionEvent event) {
		Branch selected = tableView.getSelectionModel().getSelectedItem();
		if (selected != null) {
			showEdit(selected);
		} else {
			promptNothingSelectedDialog();
		}
	}


	private void showEdit(Branch selected) {
		details.setVisible(true);
		nameEdit.setText(selected.getName());
		streetEdit.setText(selected.getStreet());
		postalCodeEdit.setText(selected.getPostalCode()+"");
		cityEdit.setText(selected.getCity()+"");
		changebtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isPostalCodeValid()&& nameEdit.getText().length()>0 && streetEdit.getText().length()>0)
				{
					String jndi = "macdoloan-ear/macdoloan-ejb/BranchServiceImpl!tn.esprit.macdoloan.service.interf.IBranchServiceRemote";
					Context context;
					try {
						context = new InitialContext();
						IBranchServiceRemote proxy = (IBranchServiceRemote) context.lookup(jndi);
						selected.setStreet(streetEdit.getText());
						selected.setName(nameEdit.getText());
						selected.setPostalCode(postalCodeEdit.getText());
						selected.setCity(cityEdit.getText());
						proxy.updateBranch(selected);
						promptSuccessDialog();
						loadPage();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(nameEdit.getText().length()>0 && cityEdit.getText().length()>0 && streetEdit.getText().length()>0){
					promptCheckPostalCodeDialog();
				}
				else{
					promptCheckInputsDialog();
				}
				
			}
		});
	}

	private boolean isPostalCodeValid() {
		return Integer.parseInt(postalCodeEdit.getText())>=1000 && Integer.parseInt(postalCodeEdit.getText())<=9183;
	}

	private void promptNothingSelectedDialog() {
		JFXDialogLayout cont = new JFXDialogLayout();
		cont.setBody(new Text("You need to select a branch in order to \n"
				+ "edit it"));
		cont.setHeading(new Text("No branch selected"));
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
	private void promptCheckInputsDialog() {
		JFXDialogLayout cont = new JFXDialogLayout();
		cont.setBody(new Text("You need to fill in the branch informations \n"));
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

	private void promptSuccessDialog() {
		JFXDialogLayout cont = new JFXDialogLayout();
		cont.setBody(new Text("Changes saved"));
		cont.setHeading(new Text("success"));
		JFXDialog dialog = new JFXDialog(stack, cont, JFXDialog.DialogTransition.CENTER);
		JFXButton close = new JFXButton("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});

		cont.setActions(close);
		dialog.show();
	}
	@FXML
	void cancel(ActionEvent event) {
		details.setVisible(false);
	}
	


}