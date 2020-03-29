package application.Donia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.service.interf.IClientServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class HelloServiceClient {
	
	@FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private Button ajout;

    @FXML
    void addClient(ActionEvent event) throws NamingException {
    	String jndiName = "macdoloan-ear/macdoloan-ejb/ClientService!tn.esprit.macdoloan.interfaces.IClientServiceRemote";
		Context context =new InitialContext();
		IClientServiceRemote proxy = (IClientServiceRemote) context.lookup(jndiName);
		Client c = new Client();
		c.setFirstName(firstname.getText());
		c.setLastName(lastname.getText());
		proxy.addClient(c);
		System.out.println("Client Ajouté");
		
    


	/*public static void main(String[] args) throws NamingException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/ClientService!tn.esprit.macdoloan.interfaces.IClientServiceRemote";
		Context context =new InitialContext();
		IClientServiceRemote proxy = (IClientServiceRemote) context.lookup(jndiName);
		Client c = new Client();
		c.setFirstName("firstName");
		c.setLastName("lastName");
		proxy.addClient(c);
		System.out.println("Client Ajouté");
		
		//d.setAmount(1200);
		//d.setAccount(v);
		//proxy.addDeposit(d);
		// d.setId(5);
		// proxy.AffectDepositToAccount(v, d);
		// Deposit d = new Deposit();
		// d.setId(7);

		// proxy.AffectDepositToAccount(1, 7);

		/*
		 * Account a =new Account(); /*a.setRIB(RIB.getText());
		 * a.setType(type.getText());
		 * a.setOpeningDate(Date.valueOf(oppDate.getValue())); context = new
		 * InitialContext(); IAccountServiceRemote proxy =
		 * (IAccountServiceRemote) context.lookup(jndiName);
		 * proxy.addAccount(a);
		 */
		// proxy.refuseCredit(loan);
		// proxy.AcceptCredit(loan);

		// proxy.addUser(user);
		// proxy.addUser(user1);
		// proxy.removeUser(15);
		// proxy.updateUser(user1);

		// System.out.println("Loans : " + proxy.findAllLoan());
		// System.out.println("Loan as numero ** is :" + proxy.findLoanById(1));

	}
    
    
    	
    }

