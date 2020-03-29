package application.Mohamed;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.entity.Loan;
import tn.esprit.macdoloan.entity.User;
import tn.esprit.macdoloan.service.interf.ILoanServiceRemote;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class adminloanfController implements Initializable {

	@FXML
	private TableView<Loan> Table;
	@FXML
	private TableColumn<User, String> client;
	@FXML
	private TableColumn<?, ?> amount;
	@FXML
	private TableColumn<?, ?> startDate;
	@FXML
	private TableColumn<?, ?> RefundDate;
	@FXML
	private TableColumn<?, ?> EndDate;
	@FXML
	private TableColumn<?, ?> Reason;
	@FXML
	private TableColumn<?, ?> State;
	@FXML
	private Button Btn_ConsulterLoan;
	@FXML
	private Button btn_loanAccpted;
	@FXML
	private Button instalmentloan;
	@FXML
	private TextField txt_rech;
	@FXML
	private AnchorPane dtails;
	@FXML
	private Label lab_nomPrenom;
	@FXML
	private Label lab_startdate;
	@FXML
	private Label lab_enddate1;

	@FXML
	private Label lab_state;
	@FXML
	private Button btn_accepter;
	@FXML
	private Button btn_refuser;
	@FXML
	private Label lab_amount;
	public static int id_loan;
	public static float amounttt;
	public static Date startd;
	public static Date endd;
	public static Loan l;

	private void showPLoanDetails(Loan a) {

		if (a != null) {
			lab_nomPrenom.setText(a.getClient().getLastName() + " " + a.getClient().getFirstName());

			id_loan = a.getId();
			amounttt = a.getAmount();
			startd = a.getStartdate();
			endd = a.getEnddate();
			l = a;

			lab_state.setText(a.getName());
			String strAmount = String.valueOf(a.getAmount());
			lab_amount.setText(strAmount);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dates = formatter.format(a.getStartdate());
			lab_startdate.setText(dates);
			String dates1 = formatter.format(a.getEnddate());
			lab_enddate1.setText(dates1);

		} else {

			lab_nomPrenom.setText("");
			lab_amount.setText("");
			lab_startdate.setText("");
			lab_state.setText("");
			lab_enddate1.setText("");
		}

	}

	// Event Listener on Button[#Btn_ConsulterLoan].onAction

	@FXML
	public void ConsulterLoan(ActionEvent event) throws NamingException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/LoanServiceImpl!tn.esprit.macdoloan.service.interf.ILoanServiceRemote";
		Context context = new InitialContext();
		ILoanServiceRemote proxy = (ILoanServiceRemote) context.lookup(jndiName);
		System.out.println("Loans : " + proxy.findAllLoan());
		ObservableList<Loan> data;
		List<Loan> liste = proxy.findAllLoan();
		System.out.println(liste.toString());
		data = FXCollections.observableArrayList();
		liste.stream().forEach((j) -> {
			String nom = j.getClient().getFirstName();
			String prenom = j.getClient().getLastName();

			data.add(j);
		});

		client.setCellValueFactory(new PropertyValueFactory<>(client.getId()));
		amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		startDate.setCellValueFactory(new PropertyValueFactory<>("startdate"));
		RefundDate.setCellValueFactory(new PropertyValueFactory<>("refunddate"));

		EndDate.setCellValueFactory(new PropertyValueFactory<>("enddate"));
		Reason.setCellValueFactory(new PropertyValueFactory<>("name"));
		State.setCellValueFactory(new PropertyValueFactory<>("State"));

		Table.setItems(data);

		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).

		FilteredList<Loan> filteredData = new FilteredList<>(data, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.

		txt_rech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(loan -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter
				// text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (loan.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Loan> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(Table.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		Table.setItems(sortedData);
		showPLoanDetails(null);

		Table.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPLoanDetails(newValue));
		btn_accepter.setVisible(true);
		btn_refuser.setVisible(true);
		instalmentloan.setVisible(false);
	}
	
	// Event Listener on Button[#btn_loanAccpted].onAction

	@FXML
	public void loanAccepted(ActionEvent event) throws NamingException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/LoanServiceImpl!tn.esprit.macdoloan.service.interf.ILoanServiceRemote";
		Context contextt = new InitialContext();
		ILoanServiceRemote proxy1 = (ILoanServiceRemote) contextt.lookup(jndiName);
		System.out.println("Loans : " + proxy1.LoanInProcess());

		System.out.println("test");
		ObservableList<Loan> data;
		List<Loan> liste = proxy1.LoanInProcess();
		System.out.println(liste.toString());
		data = FXCollections.observableArrayList();
		liste.stream().forEach((j) -> {
			String nom = j.getClient().getFirstName();
			String prenom = j.getClient().getLastName();

			data.add(j);
		});

		client.setCellValueFactory(new PropertyValueFactory<>(client.getId()));
		amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		startDate.setCellValueFactory(new PropertyValueFactory<>("startdate"));
		RefundDate.setCellValueFactory(new PropertyValueFactory<>("refunddate"));

		EndDate.setCellValueFactory(new PropertyValueFactory<>("enddate"));
		Reason.setCellValueFactory(new PropertyValueFactory<>("name"));
		State.setCellValueFactory(new PropertyValueFactory<>("State"));

		Table.setItems(data);
		// fonction pour rechercher
		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).

		FilteredList<Loan> filteredData = new FilteredList<>(data, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.

		txt_rech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(loan -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter
				// text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (loan.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Loan> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(Table.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		Table.setItems(sortedData);
		showPLoanDetails(null);

		Table.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPLoanDetails(newValue));
		// end fonction
		btn_accepter.setVisible(false);
		btn_refuser.setVisible(false);
		instalmentloan.setVisible(true);

	}

	// fonction pour recuperer la date d'aujourd'huit

	@SuppressWarnings("static-access")
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert.now());
	}

	// Event Listener on Button[#btn_accepter].onAction

	@FXML
	public void accepter(ActionEvent event) throws NamingException, ParseException, IOException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/LoanServiceImpl!tn.esprit.macdoloan.service.interf.ILoanServiceRemote";
		Context context = new InitialContext();
		ILoanServiceRemote proxy = (ILoanServiceRemote) context.lookup(jndiName);
		@SuppressWarnings("deprecation")
		Date h = new SimpleDateFormat("yyyy-MM-dd").parse("2019-5-1");
		Installment i = new Installment();
		Loan a1 = new Loan();
		a1.setId(id_loan);
		a1.setAmount(amounttt);
		a1.setStartdate(startd);
		a1.setEnddate(endd);
		i.setLoan(a1);
		int nbmois = ((a1.getEnddate().getYear() - a1.getStartdate().getYear()) * 12)
				+ (a1.getEnddate().getMonth() - a1.getStartdate().getMonth());
		System.out.println(nbmois);
		i.setPrix(proxy.getprix(a1.getStartdate(), a1.getEnddate(), a1.getAmount(), 0.12f, 12));
		// i.setRefunddatenddate(convertToDateViaSqlDate(LocalDate.now()));
		Calendar c = Calendar.getInstance();
		c.setTime(convertToDateViaSqlDate((LocalDate.now())));
		//
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		// DateFormat k = new SimpleDateFormat("yyyy-MM-dd");

		Date k = new Date();
		k = c.getTime();
		String strDate = dateFormat.format(k);
		i.setRefunddatenddate(k);

		Date newDate = (c.getTime());
		System.out.println("Date Incremented by One: " + newDate);
		for (int j = 0; j < nbmois; j++) {
			proxy.addInstalement(i);
			c.add(Calendar.MONTH, 1);
			i.setRefunddatenddate(c.getTime());
		}
		proxy.AcceptCredit(a1);
		JOptionPane.showMessageDialog(null, "Hey the credit is accepted , now you can see the installment  ",
				"Display Message", JOptionPane.INFORMATION_MESSAGE);

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("ListViewInstalementCell.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Event Listener on Button[#btn_refuser].onAction
	@FXML
	public void refuser(ActionEvent event) throws NamingException, ParseException {
		String jndiName = "macdoloan-ear/macdoloan-ejb/LoanServiceImpl!tn.esprit.macdoloan.service.interf.ILoanServiceRemote";
		Context context = new InitialContext();
		ILoanServiceRemote proxy = (ILoanServiceRemote) context.lookup(jndiName);
		Loan loan = new Loan();
		loan.setId(id_loan);
		proxy.refuseCredit(loan);
	}
	@FXML
	public void instalmentloan(ActionEvent event) throws NamingException, ParseException {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("ListViewInstalementCell.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
