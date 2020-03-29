package application.Mohamed;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import tn.esprit.macdoloan.entity.Deposit;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.service.impl.DepositServiceImpl;
import tn.esprit.macdoloan.service.impl.LoanServiceImpl;

public class ListviewdeposititemController {
	@FXML
	private AnchorPane anchor;
	@FXML
	private VBox vbox;
	@FXML
	private Label lab_id;
	@FXML
	private Label lab_amount;
	@FXML
	private Label lab_date;
	public ListviewdeposititemController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Listviewdeposititem.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			// throw new RuntimeException(e);
		}
	}

	public void initialize() {

	}

	int f = deposit_withdrawelController.id_account;

	Deposit c = new Deposit();
	DepositServiceImpl cs = new DepositServiceImpl();

	public void setDeposit(Deposit user) {
		this.c = user;
		System.out.println(c);
		if (user != null) {
			lab_id.setText(f + "");
			// lab_prix = new Label(i.getPrix()+"");
			lab_amount.setText(user.getAmount() + "");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dates = formatter.format(user.getWithdrawalDate());
			lab_date.setText(dates);
		}

	}

	public AnchorPane getBox() {
		return anchor;
	}

}



