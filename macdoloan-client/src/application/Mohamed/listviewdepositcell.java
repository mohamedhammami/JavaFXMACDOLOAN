package application.Mohamed;

import javafx.scene.control.ListCell;
import tn.esprit.macdoloan.entity.Deposit;
import tn.esprit.macdoloan.entity.Installment;

public class listviewdepositcell extends ListCell<Deposit> {
	
	 @Override
	    public void updateItem(Deposit c, boolean empty)
	    {
	        super.updateItem(c,empty);
	        if(c != null)
	        {
	            ListviewdeposititemController data = new ListviewdeposititemController();
	            data.setDeposit(c);
	            setGraphic(data.getBox());
	        }
	    } 

}
