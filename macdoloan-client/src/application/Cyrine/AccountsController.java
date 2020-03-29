package application.Cyrine;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.controlsfx.control.textfield.TextFields;

import application.Donia.SessionUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Account;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.service.interf.IAccountServiceRemote;

public class AccountsController implements Initializable {

	private static final String jndiName = "macdoloan-ear/macdoloan-ejb/AccountServiceImpl!tn.esprit.macdoloan.service.interf.IAccountServiceRemote";
	Context context;

	@FXML
	private Label LabelSearch;
	@FXML 
	private Pane PaneSearch;
	
	@FXML
	private Pane PaneAccount;
	@FXML
	private VBox VBox1;
	@FXML
	private HBox Hbox1;
	@FXML
	private HBox Hbox4;
	@FXML
	private HBox Hbox5;
	@FXML
	private ScrollPane CenterScrollPane;
	
	Stage primaryStage;
    
    public static int ConnectedAgent;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SessionUser sessionUser=new SessionUser();
		ConnectedAgent=sessionUser.getUser().getId();
		
		LabelSearch.setText("Search Client By CIN");
		TextField textFieldCin = new TextField();
		Button searchAccount = new Button();
		Button addAccount = new Button();
		Button AllAccounts= new Button();
		VBox1.setStyle("-fx-background-color: #000000;");
		

		// ****** TEXTFIELD OF SEARCH BY CIN STYLE ******
		textFieldCin.prefHeight(25.0);
		textFieldCin.prefWidth(143.0);
		textFieldCin.setPromptText("Search");
		textFieldCin.setStyle("-fx-border-color: #2471A3;");
		textFieldCin.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED,numeric_Validation());

		// ****** BUTTON STYLE OF SEARCHING ******
		searchAccount.setMnemonicParsing(false);
		searchAccount.setStyle("-fx-background-color: #26B99A;");
		searchAccount.setText("Search");
		searchAccount.setTextFill(Color.WHITE);
		searchAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					context = new InitialContext();
					IAccountServiceRemote proxy = (IAccountServiceRemote) context.lookup(jndiName);
					
					ObservableList<String> cin = FXCollections.observableArrayList();
					for (int i = 0; i < proxy.getCinClients().size(); i++) {
						cin.add(proxy.getCinClients().get(i));
					}
					
					if (textFieldCin.getText().length()!=8) {
						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setContentText(" THIS CIN IS NOT VALID ! \n CIN Number Should have 8 Digits ");
						alert.showAndWait();
					}
					
					else if (cin.contains(textFieldCin.getText())==false) {
						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setContentText(" CIN DOESN'T EXIST ");
						alert.showAndWait();
					}else {
					CenterScrollPane.setContent(ListofAccountssearched(textFieldCin.getText()));
					}
				} catch (NamingException e) {
					System.out.println(" PROBLEM IN setContent(ListofAccountssearched()) ");
					e.printStackTrace();
				}

			}
		});
		
		
		// ****** BUTTON STYLE OF ALL Accounts******
		AllAccounts.setMnemonicParsing(true);
		AllAccounts.setStyle("-fx-background-color: #26B99A;");
		AllAccounts.setText("Display All Accounts");
		AllAccounts.setTextFill(Color.WHITE);
		AllAccounts.setPrefSize(200, 25);
		AllAccounts.setAlignment(Pos.CENTER);
		AllAccounts.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					CenterScrollPane.setContent(ListofAllAccounts());
				} catch (NamingException e) {
					System.out.println("******PROBLEME ALL ACCOUNTS PANE *****");
					e.printStackTrace();
				}
				
			}
		});
		
		
		// ****** BUTTON STYLE OF SEARCHING ******
		addAccount.setMnemonicParsing(true);
		addAccount.setStyle("-fx-background-color: #26B99A;");
		addAccount.setText("Add New Account");
		addAccount.setTextFill(Color.WHITE);
		addAccount.setPrefSize(200, 25);
		addAccount.setAlignment(Pos.CENTER);
		addAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {	
				try {
					CenterScrollPane.setContent(AddNewAccount());
				} catch (NamingException e) {
					System.out.println("PROBLEEEEEMEE WITH CONTEXT INITIALISATION IN ADDNEWACCOUNT NODE ");
					e.printStackTrace();
				}
				
			}});
		

		Hbox1.getChildren().addAll(textFieldCin, searchAccount);
		Hbox4.getChildren().add(addAccount);
		Hbox4.setAlignment(Pos.CENTER);
		Hbox5.getChildren().add(AllAccounts);
		Hbox5.setAlignment(Pos.CENTER);

		try {
			context = new InitialContext();

			IAccountServiceRemote proxy = (IAccountServiceRemote) context.lookup(jndiName);

			ObservableList<String> cin = FXCollections.observableArrayList();
			for (int i = 0; i < proxy.getCinClients().size(); i++) {
				cin.add(proxy.getCinClients().get(i));
			}
			TextFields.bindAutoCompletion(textFieldCin, cin);
		} catch (NamingException e1) {
			e1.printStackTrace();
			System.out.println("PROBLEM IN CONTEXT");
		}

		CenterScrollPane.setStyle("-fx-background-color: white");

		try {
			CenterScrollPane.setContent(ListofAllAccounts());
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("PROBLEM IN NODE ListofAllAccounts");
		}

	}
	
	
	
	

	public Node ListofAllAccounts() throws NamingException {
		PaneSearch.setVisible(true);
		Hbox1.setVisible(true);
		Hbox4.setVisible(true);
		Hbox5.setVisible(false);


		context = new InitialContext();
		IAccountServiceRemote proxy = (IAccountServiceRemote) context.lookup(jndiName);

		VBox root0 = new VBox(8);

		ObservableList<Account> account = FXCollections.observableArrayList();
		for (int i = 0; i < proxy.findAllAccounts().size(); i++) {
			account.add(proxy.findAllAccounts().get(i));
		}

		for (int i = 0; i < account.size(); i++) {

			Account a = account.get(i);
			Client c = proxy.findClientById(a.getClient().getId());

			HBox root = new HBox(8);
			root.setAlignment(Pos.CENTER_LEFT);
			root.setStyle("-fx-Border-color:  #2471A3");
			root.setPadding(new Insets(5, 5, 5, 5));
			root.setStyle("-fx-background-color: white");
			try {
				VBox root2 = new VBox(6);
				root2.prefWidthProperty().set(1000);
				root2.prefHeightProperty().set(200);
				root2.setPadding(new Insets(4, 4, 4, 4));
				root2.setStyle("-fx-background-color: FFFFCC");

				// les information des Accounts
				// *****Client name
				Label ClientName = new Label(c.getFirstName() + " " + c.getLastName());
				ClientName.setFont(new Font("Arial", 25));
				ClientName.setTextFill(Paint.valueOf("#17202A"));
				// *******CiIN
				Label cin = new Label("Cin Number: " + c.getCin());
				cin.setTextFill(Paint.valueOf("#17202A"));
				VBox root3 = new VBox(3);
				Separator sep2 = new Separator(Orientation.HORIZONTAL);
				// *****Balance
				Label rib = new Label("RIB Account: " + a.getRIB());
				rib.setFont(new Font("Arial", 20));
				rib.setTextFill(Paint.valueOf("#17202A"));
				
				// **** les info
				Label Type = new Label("Account Type: " + a.getType());
				Type.setTextFill(Paint.valueOf("#17202A"));
				Label balance = new Label("The Current Balance:  " + a.getBalance());
				balance.setTextFill(Paint.valueOf("#17202A"));
				Label oppdate = new Label("Oppening Date:  " + a.getOpeningDate());
				oppdate.setTextFill(Paint.valueOf("#17202A"));
				Button btn = new Button();
				btn.setText("Close Account");
				btn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						if (a.getBalance()!=0){JOptionPane.showMessageDialog(null,"Account Can't Be Closed !\n Withdraw the money then close the Account");}
						else {proxy.AffectAgentToCloseAccount(a.getId(), ConnectedAgent);
						JOptionPane.showMessageDialog(null,"Account Successfully Closed");
						
						try {
							CenterScrollPane.setContent(ListofAllAccounts());
						} catch (NamingException e) {
							System.out.println("*****Probleme IN RELOADING ACCOUNT LIST *****");
							e.printStackTrace();
						}
						
					}
						}
				});
				
				
				
				root.getChildren().addAll(root2);
				root2.getChildren().addAll(ClientName, rib, root3);
				root3.getChildren().addAll(sep2, cin, Type, balance, oppdate, btn);

			} catch (Exception e) {
				System.out.println("Error Displaying Accounts");
			}

			
			root0.getChildren().add(root);
			root0.setPrefSize(720, 200);
			root0.setPadding(new Insets(0, 0, 0, 0));
			

		}
		return root0;

	}
	
	
	
	

	public Node ListofAccountssearched(String cinpassed) throws NamingException {
		PaneSearch.setVisible(true);
		Hbox1.setVisible(true);
		Hbox4.setVisible(true);
		Hbox5.setVisible(true);
		context = new InitialContext();
		IAccountServiceRemote proxy = (IAccountServiceRemote) context.lookup(jndiName);
		String FindCin = cinpassed;
		System.out.println(FindCin);
		Client clientsearched = proxy.findClientByCin(FindCin);
		System.out.println(clientsearched.getId());
		System.out.println("size :" + proxy.findAccountsByClient(clientsearched).size());
		VBox root0 = new VBox(8);

		ObservableList<Account> account = FXCollections.observableArrayList();
		for (int i = 0; i < proxy.findAccountsByClient(clientsearched).size(); i++) {
			account.add(proxy.findAccountsByClient(clientsearched).get(i));
			System.out.println(proxy.findAccountsByClient(clientsearched).get(i).getId());
		}

		for (int i = 0; i < account.size(); i++) {

			Account a = account.get(i);
			Client c = proxy.findClientById(a.getClient().getId());

			HBox root = new HBox(8);
			root.setAlignment(Pos.CENTER_LEFT);
			root.setStyle("-fx-Border-color:  #2471A3");
			root.setPadding(new Insets(5, 5, 5, 5));
			root.setStyle("-fx-background-color: white");
			try {
				VBox root2 = new VBox(6);
				root2.prefWidthProperty().set(1000);
				root2.prefHeightProperty().set(200);
				root2.setPadding(new Insets(4, 4, 4, 4));
				root2.setStyle("-fx-background-color: FFFFCC");

				// les information des Accounts
				// *****Client name
				Label ClientName = new Label(c.getFirstName() + " " + c.getLastName());
				ClientName.setFont(new Font("Arial", 30));
				ClientName.setTextFill(Paint.valueOf("#17202A"));
				// *******CiIN
				Label cin = new Label("Cin Number: " + c.getCin());
				cin.backgroundProperty().set(Background.EMPTY);
				cin.setTextFill(Paint.valueOf("#17202A"));
				VBox root3 = new VBox(3);
				Separator sep2 = new Separator(Orientation.HORIZONTAL);
				// *****Balance
				Label rib = new Label("RIB Account: " + a.getRIB());
				rib.setFont(new Font("Arial", 20));
				rib.setTextFill(Paint.valueOf("#17202A"));
				// **** les info
				Label Type = new Label("Account Type: " + a.getType());
				Type.setTextFill(Paint.valueOf("#17202A"));
				Label balance = new Label("The Current Balance:  " + a.getBalance());
				balance.setTextFill(Paint.valueOf("#17202A"));
				Label oppdate = new Label("Oppening Date:  " + a.getOpeningDate());
				oppdate.setTextFill(Paint.valueOf("#17202A"));
				Button btn = new Button();
				btn.setText("Close Account");
				btn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						if (a.getBalance()!=0){JOptionPane.showMessageDialog(null,"Account Can't Be Closed !\n Withdraw the money then close the Account");}
						else {proxy.AffectAgentToCloseAccount(a.getId(), ConnectedAgent);
						JOptionPane.showMessageDialog(null,"Account Successfully Closed");
						
							
								try {
									CenterScrollPane.setContent(ListofAllAccounts());
								} catch (NamingException e) {
									System.out.println("*****Probleme IN RELOADING ACCOUNT LIST *****");
									e.printStackTrace();
								}
					}
						}
				});
				root.getChildren().addAll(root2);
				root2.getChildren().addAll(ClientName, rib, root3);
				root3.getChildren().addAll(sep2, cin, Type, balance, oppdate,btn);

			} catch (Exception e) {
				System.out.println("Error Displaying Accounts");
			}

			root0.getChildren().add(root);
			root0.setPrefSize(720, 200);
			root0.setPadding(new Insets(0, 0, 0, 0));

		}
		return root0;

	}
	
	
	
	public Node AddNewAccount() throws NamingException{
		PaneSearch.setVisible(false);
		Hbox1.setVisible(false);
		Hbox4.setVisible(false);
		Hbox5.setVisible(true);
		
		NumRandom RandomNum =new NumRandom();
		
		context = new InitialContext();
		IAccountServiceRemote proxy= (IAccountServiceRemote) context.lookup(jndiName);
		ObservableList<String> cin = FXCollections.observableArrayList();
		for (int i = 0; i < proxy.getCinClients().size(); i++) {
			cin.add(proxy.getCinClients().get(i));
		}
		
		Pane newPane= new Pane();
		newPane.setPrefSize(632,560);
		newPane.setPadding(new Insets(0,0,0,0));
		
		DropShadow dropShadow = new DropShadow();
		// ****** IMAGE BACKGROUND ******
		File file = new File("src/main/resources/LoanImage.png");
		Image image = new Image(file.toURI().toString());
		ImageView img =new ImageView();
		img.setImage(image);
		img.setFitHeight(625.0);
		img.setFitWidth(882.0);
		img.setLayoutX(-77.0);
		img.setLayoutY(-14.0);
		img.setPickOnBounds(true);
		img.setPreserveRatio(true);
		
		// ****** ADD BUTTON ACOUNT ****** //
		Button AddBtn =new Button();
		AddBtn.setLayoutX(438.0);
		AddBtn.setTranslateY(418.0);
		AddBtn.setPrefHeight(58.0);
		AddBtn.prefWidth(200.0);
		AddBtn.setMnemonicParsing(false);
		AddBtn.setStyle("-fx-background-color: Black");
		AddBtn.setTextFill(Color.WHITE);
		AddBtn.setText("Add Account");
		
		// ***** LABEL ACCOUNT TYPE ****** //
		Label label1=new Label("Type of Account");
		label1.setLayoutX(164.0);
		label1.setLayoutY(95.0);
		label1.setTextFill(Color.WHITE);
		label1.setEffect(dropShadow);
		label1.setFont(new Font("System Bold",15));
		
		// ***** LABEL OPPENING DATE ****** //
		Label label2=new Label("Oppening Date of Account");
		label2.setLayoutX(96.0);
		label2.setLayoutY(161.0);
		label2.setTextFill(Color.WHITE);
		label2.setEffect(dropShadow);
		label2.setFont(new Font("System Bold",15));
		
		// ***** LABEL RIB ****** //
		Label label3=new Label("RIB: Account Number");
		label3.setLayoutX(123.0);
		label3.setLayoutY(236.0);
		label3.setTextFill(Color.WHITE);
		label3.setEffect(dropShadow);
		label3.setFont(new Font("System Bold",15));
		
		// ***** LABEL CIN ****** //
		Label label4=new Label("CIN Client Number");
		label4.setLayoutX(146.0);
		label4.setLayoutY(317.0);
		label4.setTextFill(Color.WHITE);
		label4.setEffect(dropShadow);
		label4.setFont(new Font("System Bold",15));

		
		// ****** CHICEBOX TYPE ****** //
		ChoiceBox type=new ChoiceBox<>();
		type.setLayoutX(313.0);
		type.setLayoutY(92.0);
		type.prefWidth(150.0);

		// ****** DatePicker oppDate ****** //
		DatePicker oppDate= new DatePicker();
		oppDate.setLayoutX(313.0);
		oppDate.setLayoutY(158.0);
		
		// ****** Label Random Number RIB ****** //
		Label RIB= new Label();
		RIB.setText(RandomNum.ValidUniqueNumber());
		RIB.setFont(new Font("System Bold",13));
		RIB.setTextFill(Color.WHITE);
		RIB.setLayoutX(313.0);
		RIB.setLayoutY(233.0);
		
		// ****** ChoiceBox AffectClient ****** //

		TextField AffectClient= new TextField();
		AffectClient.setLayoutX(313.0);
		AffectClient.setLayoutY(314.0);
		AffectClient.setPrefWidth(150.0);
		TextFields.bindAutoCompletion(AffectClient, cin);
		AffectClient.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED,numeric_Validation());
		
		
		ObservableList<String> typeAcc =FXCollections.observableArrayList("Current Account","Saving Account");
		type.setItems(typeAcc);
		
		   oppDate.setValue(LocalDate.now());
		   
		   AddBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					context = new InitialContext();
				IAccountServiceRemote proxy= (IAccountServiceRemote) context.lookup(jndiName);
				Account a =new Account();
				if (type.getValue() == null) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setContentText(" Please Choose a Type For your Account ");
					alert.showAndWait();
				}  else if (LocalDate.now().toEpochDay() > oppDate.getValue().toEpochDay()) {
					Alert b = new Alert(Alert.AlertType.WARNING);
					b.setContentText("The Oppening date of the Account should be starting from Todya ");
					b.showAndWait();
				} else if (AffectClient.getText() == null || AffectClient.getText() == "") {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setContentText("Please Choose the CIN Of Your Client ");
					alert.showAndWait();
				} else if (cin.contains(AffectClient.getText())==false){
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setContentText("THIS CIN DOESN'T EXIST!");
					alert.showAndWait();
				} else if (AffectClient.getText().length()!=8) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setContentText(" THIS CIN IS NOT VALID ! \n CIN Number Should have 8 Digits ");
					alert.showAndWait();
				}else {
					a.setRIB(RIB.getText());
					RandomNum.Writenumber(RIB.getText());
					a.setType(""+type.getValue());
					a.setOpeningDate(Date.valueOf(oppDate.getValue()));
					System.out.println(a);
					Client client = proxy.findClientByCin(""+AffectClient.getText());
					System.out.println("CIIIIIIIINNNNNNN: " + client.getCin());
					System.out.println("IDDDDDDDDD: " + client.getId());
					int idAccount = proxy.addAccount(a);
					proxy.AffectAccountToClient(idAccount, client.getId());
					proxy.AffectAgentToOppenAccount(idAccount, ConnectedAgent);
					JOptionPane.showMessageDialog(null,"Account Successfully Created");
					CenterScrollPane.setContent(ListofAllAccounts());
				}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}); 
		   
		   
		
		
		newPane.getChildren().addAll(img,label1, label2, label3, label4, type, oppDate, RIB, AffectClient,AddBtn);
		return newPane;
	}
	
	public EventHandler<javafx.scene.input.KeyEvent> numeric_Validation() {
        return new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (e.getCharacter().matches("[0-9]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }
	

}
