package application.Cyrine;

import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import application.Donia.SessionUser;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.EnumUtils;
import org.controlsfx.control.textfield.TextFields;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import tn.esprit.macdoloan.entity.Appointment;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.entity.Governorate;
import tn.esprit.macdoloan.service.interf.IAppointmentServiceRemote;


public class AppointmentsController implements Initializable{
	
	private static final String jndiName = "macdoloan-ear/macdoloan-ejb/AppointmentServiceImpl!tn.esprit.macdoloan.service.interf.IAppointmentServiceRemote";
	Context context;
	
	@FXML
	private Label LabelSearch;
	@FXML 
	private Pane PaneSearch;
	
	@FXML
    private TableColumn<Appointment,String> GovTab;
    @FXML
    private TableColumn<Appointment,String> CityTab;
    @FXML
    private TableColumn<Appointment,String> PlaceTab;
    @FXML
    private TableColumn<Appointment,Date> DateTab;
    @FXML
    private TableColumn<Appointment,String> ObjectTab;
    @FXML
    private TableColumn<Appointment,String> DescriptionTab;  
    @FXML
    private TableColumn<Appointment,String> idApp;
    @FXML
    private TableView<Appointment> TableApp;

    @FXML
    private Label ClientName;
    @FXML
    private Label ClientCin;
    @FXML
    private Label ClientPhone;
    @FXML
    private Label ClientMail;
    @FXML
    private Label ClientAdress;
    
    @FXML
    private Label AppObj;
    @FXML
    private Label ClientAdressApp;
    @FXML
    private TextArea DescriptionApp;
    
    @FXML
    private Button btnDone;
    @FXML
    private Button btnAccept;
    @FXML
    private Button Closebtn;
    @FXML
    private Button Closebtn1;
    
	
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
	
	@FXML
	private Pane PaneAccount;
	@FXML
	private Pane PaneAppointment;
	@FXML
	private Pane PaneMyApp;
	@FXML
	private Pane ClientInfo;
	@FXML
	private Pane AppointmentInfo;
	
	public static int ConnectedAgent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		SessionUser sessionUser = new SessionUser();
		ConnectedAgent=sessionUser.getUser().getId();
		
		PaneMyApp.setVisible(false);
		ClientInfo.setVisible(false);
		idApp.setVisible(false);
		AppointmentInfo.setVisible(false);
		
		LabelSearch.setText("Search by Governorate");

		TextField textFieldGovern = new TextField();
		Button searchAppointments = new Button();
		Button ConsultmyAppointments = new Button();
		Button AllAppointments = new Button();
		VBox1.setStyle("-fx-background-color: #000000;");

		// ****** TEXTFIELD OF SEARCH BY Governorate STYLE ******
		textFieldGovern.prefHeight(25.0);
		textFieldGovern.prefWidth(143.0);
		textFieldGovern.setPromptText("Search by Governorate");
		textFieldGovern.setStyle("-fx-border-color: #2471A3;");

		// ****** BUTTON STYLE OF SEARCHING ******
		searchAppointments.setMnemonicParsing(false);
		searchAppointments.setStyle("-fx-background-color: #26B99A;");
		searchAppointments.setText("Search");
		searchAppointments.setTextFill(Color.WHITE);
		searchAppointments.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					
					if (!EnumUtils.isValidEnum(Governorate.class, textFieldGovern.getText())) {
						JOptionPane.showMessageDialog(null,"THE GOVERNORATE YOU ENTERED DOESN'T EXIST");}
					
					   CenterScrollPane.setContent(ListofAppointmenttssearched(textFieldGovern.getText()));
					    
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		// ****** BUTTON STYLE OF SEARCHING ******
		ConsultmyAppointments.setMnemonicParsing(true);
		ConsultmyAppointments.setStyle("-fx-background-color: #26B99A;");
		ConsultmyAppointments.setText("My Appointments");
		ConsultmyAppointments.setTextFill(Color.WHITE);
		ConsultmyAppointments.setPrefSize(200, 25);
		ConsultmyAppointments.setAlignment(Pos.CENTER);
		ConsultmyAppointments.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				textFieldGovern.setVisible(false);
				searchAppointments.setVisible(false);
				ConsultmyAppointments.setVisible(false);
				AppointmentInfo.setVisible(false);
				PaneAppointment.setVisible(false);
				PaneMyApp.setVisible(true);
				try {
					CenterScrollPane.setContent(ConsultMyAppointments());
				} catch (NamingException e) {
					System.out.println("***** PROBLEM CONSULTMY APPOINTMENTS");
					e.printStackTrace();
				}
				
			}});
		

		// ****** BUTTON STYLE OF ALL Appointments******
		AllAppointments.setMnemonicParsing(true);
		AllAppointments.setStyle("-fx-background-color: #26B99A;");
		AllAppointments.setText("Display All Appointments");
		AllAppointments.setTextFill(Color.WHITE);
		AllAppointments.setPrefSize(200, 25);
		AllAppointments.setAlignment(Pos.CENTER);
		AllAppointments.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					CenterScrollPane.setContent(ListofAllAppointments());
				} catch (NamingException e) {
					System.out.println("******PROBLEME ALL Appointments PANE *****");
					e.printStackTrace();
				}
				
			}
		});
		
		

		Hbox1.getChildren().addAll(textFieldGovern, searchAppointments);
		Hbox4.getChildren().add(ConsultmyAppointments);
		Hbox4.setAlignment(Pos.CENTER);
		Hbox5.getChildren().add(AllAppointments);
		Hbox5.setAlignment(Pos.CENTER);

			TextFields.bindAutoCompletion(textFieldGovern, Governorate.values());


		CenterScrollPane.setStyle("-fx-background-color: white");

		try {
			CenterScrollPane.setContent(ListofAllAppointments());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public Node ListofAllAppointments() throws NamingException{
		PaneSearch.setVisible(true);
		Hbox1.setVisible(true);
		Hbox4.setVisible(true);
		Hbox5.setVisible(false);
		
		context = new InitialContext();
		IAppointmentServiceRemote proxy = (IAppointmentServiceRemote) context.lookup(jndiName);

		VBox root0 = new VBox(8);

		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		for (int i = 0; i < proxy.findAllAppointments().size(); i++) {
			appointments.add(proxy.findAllAppointments().get(i));
		}

		for (int i = 0; i < appointments.size(); i++) {

			Appointment a = appointments.get(i);
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
				cin.setTextFill(Paint.valueOf("#17202A"));
				VBox root3 = new VBox(3);
				Separator sep2 = new Separator(Orientation.HORIZONTAL);
				// *****Governorate
				Label gov = new Label("Governorate: " + a.getGovernorate());
				gov.setFont(new Font("Arial", 20));
				gov.setTextFill(Paint.valueOf("#17202A"));
				// **** les info
				Label Type = new Label("The City: " + a.getCity());
				Type.setTextFill(Paint.valueOf("#17202A"));
				Label balance = new Label("The Place of The Appointment:  " + a.getPlace());
				balance.setTextFill(Paint.valueOf("#17202A"));
				Label oppdate = new Label("Appointment Date:  " + a.getDate());
				oppdate.setTextFill(Paint.valueOf("#17202A"));
				Button btn = new Button();
				btn.setText("More Details");
				btn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						AppointmentInfo.setVisible(true);
						AppObj.setText(a.getObject());
						ClientAdressApp.setText(c.getAdress());
						DescriptionApp.setText(a.getDescription());
						Closebtn1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								AppointmentInfo.setVisible(false);
								
							}
						});
						btnAccept.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								proxy.AffectAppointmentToAgent(a.getId(), ConnectedAgent);;

								JOptionPane.showMessageDialog(null,"Appointment Successfully Accepted");	
                      
							      String mailClient =c.getAdresseMail();
							       System.out.println(mailClient);
							         
							        String Text = "Dear "+c.getFirstName()+" "+c.getLastName()+"\n"+
							        "We have the pleasure to tell you that your Appointment have been Accepted by our Agent "+ 
							        proxy.findAgentById(ConnectedAgent).getFirstName()+" "+proxy.findAgentById(ConnectedAgent).getLastName()+
							        " on "+a.getDate()+" in "+a.getGovernorate()+" "+a.getCity()+" at your home which is located in "+c.getAdress()+"\n"+
							        "We will Call You Soon For More Details "+"\n"+"Cordially.";
							        
							        final String username = "contact.macdoloan@gmail.com";
							        final String password = "AZERTY123";

							        Properties props = new Properties();
							        props.put("mail.smtp.host", "smtp.gmail.com");
							        props.put("mail.smtp.socketFactory.port", "465");
							        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
							       
							        props.put("mail.smtp.auth", "true");
							        props.put("mail.smtp.port", "465");

							        Session sessionUser = Session.getDefaultInstance(props,
							                new javax.mail.Authenticator(){
							                    @Override
							                    protected PasswordAuthentication getPasswordAuthentication() {
							                    	System.out.println("*********** Pass Verif");
							                        return new PasswordAuthentication(username,password);
							                       }
							                    });

							         Message message = new MimeMessage(sessionUser);
							         try {
										message.setFrom(new InternetAddress(username));
										 message.setRecipients(Message.RecipientType.TO,
										 InternetAddress.parse(mailClient));
										 message.setSubject("Appointment Accepted");
			                             message.setText(Text);
					                    System.out.println("*********** Send");

										 Transport.send(message);
									} catch (MessagingException e) {
										System.out.println("PROBLEME DE MAIIIIIIIIIIIIILL");
										e.printStackTrace();
									}
							        
							         
									   try {
										CenterScrollPane.setContent(ListofAllAppointments());
									} catch (NamingException e) {
										System.out.println("PROBLEM WHITH RELOADING ALL APPOINTMENT NODE");
										e.printStackTrace();
									}

							
							}
							});
						}
					});
								

				root.getChildren().addAll(root2);
				root2.getChildren().addAll(ClientName, gov, root3);
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
	
	
	
	
	public Node ListofAppointmenttssearched(String Governpassed) throws NamingException{
		PaneSearch.setVisible(true);
		Hbox1.setVisible(true);
		Hbox4.setVisible(true);
		Hbox5.setVisible(true);
		
		context = new InitialContext();
		System.out.println(Governpassed);
		IAppointmentServiceRemote proxy = (IAppointmentServiceRemote) context.lookup(jndiName);
		VBox root0 = new VBox(8);
		System.out.println("hedhaaaaaaa" +proxy.findAppointmentByGover(Governpassed));

		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		for (int i = 0; i < proxy.findAppointmentByGover(Governpassed).size(); i++) {
			appointments.add(proxy.findAppointmentByGover(Governpassed).get(i));
			System.out.println(proxy.findAppointmentByGover(Governpassed).get(i).getId());
		}

		for (int i = 0; i < appointments.size(); i++) {

			Appointment a = appointments.get(i);
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
				cin.setTextFill(Paint.valueOf("#17202A"));
				VBox root3 = new VBox(3);
				Separator sep2 = new Separator(Orientation.HORIZONTAL);
				// *****Governorate
				Label rib = new Label("Governorate: " + a.getGovernorate());
				rib.setFont(new Font("Arial", 20));
				rib.setTextFill(Paint.valueOf("#17202A"));
				// **** les info
				Label Type = new Label("The City: " + a.getCity());
				Type.setTextFill(Paint.valueOf("#17202A"));
				Label balance = new Label("The Place of The Appointment:  " + a.getPlace());
				balance.setTextFill(Paint.valueOf("#17202A"));
				Label oppdate = new Label("Appointment Date:  " + a.getDate());
				oppdate.setTextFill(Paint.valueOf("#17202A"));
				Button btn = new Button();
				btn.setText("More Details");
				btn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						AppointmentInfo.setVisible(true);
						AppObj.setText(a.getObject());
						ClientAdressApp.setText(c.getAdress());
						DescriptionApp.setText(a.getDescription());
						Closebtn1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								AppointmentInfo.setVisible(false);
								
							}
						});
                 btnAccept.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								proxy.AffectAppointmentToAgent(a.getId(), ConnectedAgent);;
								JOptionPane.showMessageDialog(null,"Appointment Successfully Accepted");	
                      
							      String mailClient =c.getAdresseMail();
							       System.out.println(mailClient);
							         
							        String Text = "Dear "+c.getFirstName()+" "+c.getLastName()+"\n"+
							        "We have the pleasure to tell you that your Appointment have been Accepted by our Agent "+ 
							        proxy.findAgentById(ConnectedAgent).getFirstName()+" "+proxy.findAgentById(ConnectedAgent).getLastName()+
							        " on "+a.getDate()+" in "+a.getGovernorate()+" "+a.getCity()+" at your home which is located in "+c.getAdress()+"\n"+
							        "We will Call You Soon For More Details "+"\n"+"Cordially.";
							        
							        final String username = "contact.macdoloan@gmail.com";
							        final String password = "AZERTY123";

							        Properties props = new Properties();
							        props.put("mail.smtp.host", "smtp.gmail.com");
							        props.put("mail.smtp.socketFactory.port", "465");
							        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
							       
							        props.put("mail.smtp.auth", "true");
							        props.put("mail.smtp.port", "465");

							        Session session = Session.getDefaultInstance(props,
							                new javax.mail.Authenticator(){
							                    @Override
							                    protected PasswordAuthentication getPasswordAuthentication() {
							                    	System.out.println("*********** Pass Verif");
							                        return new PasswordAuthentication(username,password);
							                       }
							                    });

							         Message message = new MimeMessage(session);
							         try {
										message.setFrom(new InternetAddress(username));
										 message.setRecipients(Message.RecipientType.TO,
										 InternetAddress.parse(mailClient));
										 message.setSubject("Appointment Accepted");
			                             message.setText(Text);
					                    System.out.println("*********** Send");

										 Transport.send(message);
									} catch (MessagingException e) {
										System.out.println("PROBLEME DE MAIIIIIIIIIIIIILL");
										e.printStackTrace();
									}
							        
							         
							         try {
											CenterScrollPane.setContent(ListofAllAppointments());
										} catch (NamingException e) {
											System.out.println("PROBLEM WHITH RELOADING ALL APPOINTMENT LIST NODE");
											e.printStackTrace();
										}

							
							}
							});
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
	
	
	
	
	public Node ConsultMyAppointments() throws NamingException {
		PaneSearch.setVisible(false);
		Hbox1.setVisible(false);
		Hbox4.setVisible(false);
		Hbox5.setVisible(false);
		context = new InitialContext();
		IAppointmentServiceRemote proxy = (IAppointmentServiceRemote) context.lookup(jndiName);
		
        ObservableList<Appointment> data=FXCollections.observableArrayList();
        for(Appointment myApp : proxy.findAppointmentByAgent(proxy.findAgentById(ConnectedAgent)))
        {
         data.add(myApp);
        }
        
        System.out.println(data.toString());
        TableApp.setItems(data);
        
        GovTab.setCellValueFactory(new PropertyValueFactory<>("governorate"));
        CityTab.setCellValueFactory(new PropertyValueFactory<>("city"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObjectTab.setCellValueFactory(new PropertyValueFactory<>("object"));
        DescriptionTab.setCellValueFactory(new PropertyValueFactory<>("description"));
        idApp.setCellValueFactory(new PropertyValueFactory<>("id"));
       /* Appointment selectedApp = TableApp.getSelectionModel().getSelectedItem();
        System.out.println(selectedApp.getId());*/

        
        TableApp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
            	int index = TableApp.getSelectionModel().getSelectedIndex();
                Appointment selectedApp = TableApp.getItems().get(index); 
                Client InfClient=new Client();
                InfClient=proxy.findClientById(selectedApp.getClient().getId());
                  ClientName.setText(InfClient.getFirstName()+" "+InfClient.getLastName());;
                  ClientCin.setText(InfClient.getCin());
                  ClientPhone.setText(InfClient.getPhone());  
                  ClientMail.setText(InfClient.getAdresseMail());
                  ClientAdress.setText(InfClient.getAdress());
                  ClientInfo.setVisible(true);
                  Closebtn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						ClientInfo.setVisible(false);
						
					}
				});
                  btnDone.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						proxy.updateAppointmentStatus(selectedApp);
						JOptionPane.showMessageDialog(null,"Appointment Successfully Done");
						
						try {
							CenterScrollPane.setContent(ListofAllAppointments());
						} catch (NamingException e) {
							System.out.println("PROBLEM WHITH RELOADING ALL APPOINTMENT LISTE NODE AGAIN");
							e.printStackTrace();
						}

						
						
					}
				});
                  
            }
        });
        
		return null;
	}

}
