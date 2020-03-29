package application.Mohamed;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import tn.esprit.macdoloan.entity.Deposit;
import tn.esprit.macdoloan.entity.Installment;
import tn.esprit.macdoloan.service.interf.ILoanServiceRemote;
import tn.esprit.macdoloan.service.interf.IdepositServiceRemote;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXListView;

public class ListViewdepositCellController {
	@FXML
	private JFXListView <Deposit>list;
	
	private ObservableList<Deposit> data;

	public void initialize()
	{
		data = FXCollections.observableArrayList();
		String jndiName = "macdoloan-ear/macdoloan-ejb/DepositServiceImpl!tn.esprit.macdoloan.service.interf.IdepositServiceRemote";
		Context context;
		try {
			context = new InitialContext();
			IdepositServiceRemote proxy = (IdepositServiceRemote) context.lookup(jndiName);
			int f = deposit_withdrawelController.id_account;
			List<Deposit> listeUsers = proxy.findAlldepositByaccountId(f);

			listeUsers.forEach((j)->{
				data.add(j);
			});
			list.setItems(data);
			list.setCellFactory(new Callback<ListView<Deposit>, ListCell<Deposit>>() {
				
				@Override
				public ListCell<Deposit> call(ListView<Deposit> param) {
					// TODO Auto-generated method stub
					return new listviewdepositcell();
				}
			});
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	}



