package application.Mohamed;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Account;
import tn.esprit.macdoloan.entity.Deposit;
import tn.esprit.macdoloan.entity.Withdrawal;
import tn.esprit.macdoloan.service.interf.IdepositServiceRemote;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.Slider;

import javafx.scene.control.DatePicker;

public class deposit_withdrawelController implements Initializable {
	@FXML
	private DatePicker DatePickerStart;
	@FXML
	private Button btnUpload;
	@FXML
	private AnchorPane anachrop;
	@FXML
	private Slider sliderTotalRaised;
	@FXML
	private TextField Valeurchange1;
	@FXML
	private TextField TxtRib;
	@FXML
	private DatePicker DatePickerStart1;
	@FXML
	private Button btnUpload1;
	@FXML
	private Slider sliderTotalRaised1;
	@FXML
	private TextField Valeurchange11;
	@FXML
	private TextField TxtRib1;
	public static int id_account;

	// private static final float ValeurInitial1 = 0;

	public void initialize(URL location, ResourceBundle resources) {

		DatePickerStart.setValue(LocalDate.now());
		DatePickerStart1.setValue(LocalDate.now());
		// slider Total Raised
		// sliderTotalRaised.setValue(ValeurInitial1);
		// sliderTotalRaised.setMax(2000.0);

		/*
		 * sliderTotalRaised.valueProperty().addListener(new
		 * ChangeListener<Number>() { public void changed(ObservableValue<?
		 * extends Number> ov, Number old_val, Number new_val) {
		 * System.out.println(new_val.doubleValue()); //
		 * ValeurInitial.setText(String.format("%.2f", new_val)); } });
		 * 
		 * // ValeurInitial.setText(new Double(ValeurInitial1).toString());
		 * Valeurchange1.textProperty().bindBidirectional(sliderTotalRaised.
		 * valueProperty(), NumberFormat.getNumberInstance());
		 * 
		 * // Fin Slider Total Raised // slider Total Raised1
		 * sliderTotalRaised1.setValue(ValeurInitial1);
		 * sliderTotalRaised.setMax(2000.0);
		 * sliderTotalRaised1.valueProperty().addListener(new
		 * ChangeListener<Number>() { public void changed(ObservableValue<?
		 * extends Number> ov, Number old_val, Number new_val) {
		 * System.out.println(new_val.doubleValue()); //
		 * ValeurInitial.setText(String.format("%.2f", new_val)); } });
		 */

		// ValeurInitial.setText(new Double(ValeurInitial1).toString());
		/*
		 * Valeurchange11.textProperty().bindBidirectional(sliderTotalRaised1.
		 * valueProperty(), NumberFormat.getNumberInstance());
		 */

		// Fin Slider Total Raised

	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@SuppressWarnings("static-access")
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert.now());
	}

	// ***************************
	@FXML
	void updateNoteSlider(MouseEvent event) {
		sliderTotalRaised.valueProperty()
				.addListener((obs, oldval, newVal) -> sliderTotalRaised.setValue(newVal.intValue()));
		Valeurchange1.setText(String.valueOf(sliderTotalRaised.getValue()));

	}

	@FXML
	void updateNoteSlider1(MouseEvent event) {
		sliderTotalRaised1.valueProperty()
				.addListener((obs, oldval, newVal) -> sliderTotalRaised1.setValue(newVal.intValue()));
		Valeurchange11.setText(String.valueOf(sliderTotalRaised1.getValue()));

	}

	// *****************
	// Event Listener on Button[#btnUpload].onAction
	@FXML
	public void upload(ActionEvent event) throws IOException, NamingException {

		String jndiName = "macdoloan-ear/macdoloan-ejb/DepositServiceImpl!tn.esprit.macdoloan.service.interf.IdepositServiceRemote";
		Context context = new InitialContext();
		IdepositServiceRemote proxy = (IdepositServiceRemote) context.lookup(jndiName);
		Deposit c = new Deposit();
		Account a = new Account();
		a.setId((proxy.RecupererIDAccount("TN59 10 006 " + TxtRib.getText() + " 31")).getId());
		id_account = a.getId();
		c.setWithdrawalDate(convertToDateViaSqlDate(DatePickerStart.getValue()));
		c.setAmount(Float.valueOf(Valeurchange1.getText()));
		c.setAccount(a);

		proxy.addDeposit(c);
		// Affiche du liste des deposit
		JOptionPane.showMessageDialog(null, "Consult now the Deposit   ", "Display Message",
				JOptionPane.INFORMATION_MESSAGE);

		//***
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("ListViewdepositCell.fxml"));
			anachrop.getChildren().setAll(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		//***
	}

	@FXML
	public void upload1(ActionEvent event) throws IOException, NamingException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/DepositServiceImpl!tn.esprit.macdoloan.service.interf.IdepositServiceRemote";
		Context context = new InitialContext();
		IdepositServiceRemote proxy = (IdepositServiceRemote) context.lookup(jndiName);
		Withdrawal i = new Withdrawal();
		Account l = new Account();
		l.setId((proxy.RecupererIDAccount("TN59 10 006 " + TxtRib1.getText() + " 31")).getId());

		i.setWithdrawalDate(convertToDateViaSqlDate(DatePickerStart1.getValue()));
		i.setAmount(Float.valueOf(Valeurchange11.getText()));
		i.setAccount(l);

		proxy.addwithdraw(i);
	}
}
