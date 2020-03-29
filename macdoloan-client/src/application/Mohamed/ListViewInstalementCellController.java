package application.Mohamed;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.service.interf.ILoanServiceRemote;
//*********
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXListView;

public class ListViewInstalementCellController {
	@FXML
	private JFXListView<Installment> list;


//*************



private ObservableList<Installment> data;

public void initialize()
{
	data = FXCollections.observableArrayList();
	String jndiName = "macdoloan-ear/macdoloan-ejb/LoanServiceImpl!tn.esprit.macdoloan.service.interf.ILoanServiceRemote";
	Context context;
	try {
		context = new InitialContext();
		ILoanServiceRemote proxy = (ILoanServiceRemote) context.lookup(jndiName);
		int f = adminloanfController.id_loan;
		List<Installment> listeUsers = proxy.findAllinstallementByloanId(f);

		listeUsers.forEach((j)->{
			data.add(j);
		});
		list.setItems(data);
		list.setCellFactory(new Callback<ListView<Installment>, ListCell<Installment>>() {
			
			@Override
			public ListCell<Installment> call(ListView<Installment> param) {
				// TODO Auto-generated method stub
				return new listviewinstcell();
			}
		});
		
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


}
