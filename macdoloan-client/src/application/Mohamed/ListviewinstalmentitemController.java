package application.Mohamed;

import javafx.fxml.FXML;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.service.impl.LoanServiceImpl;
import javafx.scene.control.Label;



import javafx.scene.layout.VBox;

public class ListviewinstalmentitemController {
	@FXML
	private AnchorPane anchor;
	@FXML
	private VBox vbox;
	@FXML
	private Label lab_id;
	@FXML
	private Label lab_prix;
	@FXML
	private Label lab_date;

	public ListviewinstalmentitemController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Listviewinstalmentitem.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			// throw new RuntimeException(e);
		}
	}

	public void initialize() {

	}

	int f = adminloanfController.id_loan;

	Installment c = new Installment();
	LoanServiceImpl cs = new LoanServiceImpl();

	public void setInstallment(Installment user) {
		this.c = user;
		System.out.println(c);
		if (user != null) {
			lab_id.setText(f + "");
			// lab_prix = new Label(i.getPrix()+"");
			lab_prix.setText(user.getPrix() + "");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dates = formatter.format(user.getRefunddatenddate());
			lab_date.setText(dates);
		}

	}

	public AnchorPane getBox() {
		return anchor;
	}

}
