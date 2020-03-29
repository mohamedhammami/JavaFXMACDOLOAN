package application.Ayoub;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tn.esprit.macdoloan.entity.Admin;
import tn.esprit.macdoloan.entity.Claim;
import tn.esprit.macdoloan.service.interf.IClaimServiceRemote;

public class StatisticsClaimsUIController implements Initializable{
	@FXML
	private Pane windowPane;
	@FXML
	private PieChart chart;
	@FXML
	private Label caption;

	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private BarChart<String, Integer> barChart1;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		caption.setVisible(false);
		// TODO Auto-generated method stub
		String jndiName = "macdoloan-ear/macdoloan-ejb/ClaimServiceImpl!tn.esprit.macdoloan.service.interf.IClaimServiceRemote";
		Context context;
		IClaimServiceRemote proxy;
		ArrayList<Claim> answredClaimsList = new ArrayList<Claim>();
		ArrayList<Claim> unanswredClaimsList = new ArrayList<Claim>();
		ArrayList<Admin> adminsList = new ArrayList<Admin>();
		Map<String,Integer> mymap =new HashMap();
		try {
			context = new InitialContext();
			proxy = (IClaimServiceRemote)context.lookup(jndiName);
			answredClaimsList=(ArrayList<Claim>) proxy.findAnswredClaims();
			unanswredClaimsList=(ArrayList<Claim>) proxy.findNotAnswredClaims();
			adminsList=(ArrayList<Admin>) proxy.findAllAdmins();
			mymap=proxy.CountByObject();
			ObservableList<PieChart.Data> pieChartData =
					FXCollections.observableArrayList(
							new PieChart.Data("Answred Claims", answredClaimsList.size()),
							new PieChart.Data("Unanswred Claims", unanswredClaimsList.size()));

			chart.getData().setAll(pieChartData);

			for (final PieChart.Data data : chart.getData()) {
				data.getNode().addEventHandler(
						MouseEvent.MOUSE_MOVED,
						new EventHandler<MouseEvent>() {
							@Override public void handle(MouseEvent e) {
								caption.setVisible(true);	
								caption.setLayoutX(e.getScreenX()-300);
								caption.setLayoutY(e.getScreenY()-80);
								caption.setText(String.valueOf(data.getPieValue()));	
							}
						});
				data.getNode().addEventHandler(
						MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
							@Override public void handle(MouseEvent e) {
								caption.setVisible(false);	
							}
						});
			}
			XYChart.Series dataSeries1 = new XYChart.Series();
			dataSeries1.setName("Admin name");

			for (Admin a : adminsList) {
				dataSeries1.getData().add(new XYChart.Data(a.getFirstName()+" "+a.getLastName().toUpperCase(), a.getAdminAnswers().size()));
			}
			barChart.getData().add(dataSeries1);
			for (Series<String, Integer> serie: barChart.getData()){
	            for (XYChart.Data<String, Integer> item: serie.getData()){
	                item.getNode().setOnMouseMoved((MouseEvent e) -> {
	                	caption.setVisible(true);	
						caption.setLayoutX(e.getScreenX()-300);
						caption.setLayoutY(e.getScreenY()-80);
						caption.setText(String.valueOf(item.getYValue()));
	                    //System.out.println("you clicked "+item.toString()+serie.toString());
	                });
	                item.getNode().setOnMouseExited((MouseEvent event) -> {
	                	caption.setVisible(false);
	                });
	            }
	        }
			XYChart.Series dataSeries11 = new XYChart.Series();
			dataSeries11.setName("Object");

			 for (Map.Entry<String, Integer> entry : mymap.entrySet())
			 {
					dataSeries11.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));

			 }
			
			barChart1.getData().add(dataSeries11);
			for (Series<String, Integer> serie: barChart1.getData()){
	            for (XYChart.Data<String, Integer> item: serie.getData()){
	                item.getNode().setOnMouseMoved((MouseEvent e) -> {
	                	caption.setVisible(true);	
						caption.setLayoutX(e.getScreenX()-300);
						caption.setLayoutY(e.getScreenY()-80);
						caption.setText(String.valueOf(item.getYValue()));
	                    //System.out.println("you clicked "+item.toString()+serie.toString());
	                });
	                item.getNode().setOnMouseExited((MouseEvent event) -> {
	                	caption.setVisible(false);
	                });
	            }
	        }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
