package application.Mohamed;



import javafx.scene.control.ListCell;
import tn.esprit.macdoloan.entity.Installment;

public class listviewinstcell extends ListCell<Installment> {
	
	 @Override
	    public void updateItem(Installment c, boolean empty)
	    {
	        super.updateItem(c,empty);
	        if(c != null)
	        {
	            ListviewinstalmentitemController data = new ListviewinstalmentitemController();
	            data.setInstallment(c);
	            setGraphic(data.getBox());
	        }
	    } 
	
	
	
	
}
