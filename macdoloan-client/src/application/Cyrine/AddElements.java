package application.Cyrine;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.macdoloan.entity.Agent;
import tn.esprit.macdoloan.entity.Appointment;
import tn.esprit.macdoloan.entity.Client;
import tn.esprit.macdoloan.entity.Governorate;
import tn.esprit.macdoloan.service.interf.IAccountServiceRemote;
import tn.esprit.macdoloan.service.interf.IAppointmentServiceRemote;

public class AddElements {

	public static void main(String[] args) throws NamingException, ParseException {
		String jndiName1 = "macdoloan-ear/macdoloan-ejb/AccountServiceImpl!tn.esprit.macdoloan.service.interf.IAccountServiceRemote";
		String jndiName2 = "macdoloan-ear/macdoloan-ejb/AppointmentServiceImpl!tn.esprit.macdoloan.service.interf.IAppointmentServiceRemote";
		Context context;
		context = new InitialContext();
		
		IAccountServiceRemote proxy1 = (IAccountServiceRemote) context.lookup(jndiName1);
		IAppointmentServiceRemote proxy2 = (IAppointmentServiceRemote) context.lookup(jndiName2);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Client c=new Client();
        
        
      /* Client c1=new Client();
		c1.setBirthDate(dateFormat.parse("01/02/1996"));
		c1.setCin("15000255");
		c1.setAdress("Tunis Beb Elkhadhra");
		c1.setAdresseMail("ManelFawzi@gmail.com");
		c1.setFirstName("Manel");
		c1.setLastName("Fawzi");
		c1.setLogin("Mannou");
		c1.setPhone("20899356");
		c1.setPassword("123abc");
		proxy1.addClient(c1);
		
        
        Client c2=new Client();
		c2.setBirthDate(dateFormat.parse("01/10/1980"));
		c2.setCin("00052369");
		c2.setAdress("Manouba Tunis");
		c2.setAdresseMail("AhlemStaghanmi@gmail.com");
		c2.setFirstName("Ahlem");
		c2.setLastName("Staghanmi");
		c2.setLogin("Halouma");
		c2.setPhone("20896345");
		c2.setPassword("azertyy");
		proxy1.addClient(c2);
		
        
        
        Client c3=new Client();
		c3.setBirthDate(dateFormat.parse("22/04/2000"));
		c3.setCin("15000400");
		c3.setAdress("Sfax");
		c3.setAdresseMail("AhmedSalah@gmail.com");
		c3.setFirstName("Ahmed");
		c3.setLastName("Salah");
		c3.setLogin("Hamouda");
		c3.setPhone("29899356");
		c3.setPassword("azert");
		proxy1.addClient(c3);
		
        
		Client c4=new Client();
		c4.setBirthDate(dateFormat.parse("12/12/2012"));
		c4.setCin("00152369");
		c4.setAdress("Mahdia Blasa");
		c4.setAdresseMail("SihemSahloul@gmail.com");
		c4.setFirstName("Sihem");
		c4.setLastName("Sahloul");
		c4.setLogin("Sousou");
		c4.setPhone("20889445");
		c4.setPassword("azertyy");
		proxy1.addClient(c4);
		
		Client c5=new Client();
		c5.setBirthDate(dateFormat.parse("23/10/1995"));
		c5.setCin("15151264");
		c5.setAdress("Tunis LE Kram");
		c5.setAdresseMail("MariemSamir@gmail.com");
		c5.setFirstName("Mariem");
		c5.setLastName("Samir");
		c5.setLogin("Maryouma");
		c5.setPhone("26889445");
		c5.setPassword("0000");
		proxy1.addClient(c5);
		
		Client c6=new Client();
		c6.setBirthDate(dateFormat.parse("05/10/1980"));
		c6.setCin("01010145");
		c6.setAdress("Tunis La Marsa");
		c6.setAdresseMail("KhaoulaJomni@gmail.com");
		c6.setFirstName("Khaoula");
		c6.setLastName("Jomni");
		c6.setLogin("Khoukha");
		c6.setPhone("29889445");
		c6.setPassword("0000");
		proxy1.addClient(c6);
		
		Client c7=new Client();
		c7.setBirthDate(dateFormat.parse("01/12/1980"));
		c7.setCin("02010145");
		c7.setAdress("Tunis Centre Ville");
		c7.setAdresseMail("HaithemSehli@gmail.com");
		c7.setFirstName("Haithem");
		c7.setLastName("Sehli");
		c7.setLogin("Haithem");
		c7.setPhone("55214789");
		c7.setPassword("0000");
		proxy1.addClient(c7);
		
		Client c8=new Client();
		c8.setBirthDate(dateFormat.parse("30/08/1983"));
		c8.setCin("05050123");
		c8.setAdress("Tataouine Rue Quelq Soit");
		c8.setAdresseMail("AdelHileli@gmail.com");
		c8.setFirstName("Adel");
		c8.setLastName("Hileli");
		c8.setLogin("Adel");
		c8.setPhone("99214587");
		c8.setPassword("0000");
		proxy1.addClient(c8);
		
		
		Agent a1=new Agent();
		a1.setBirthDate(dateFormat.parse("20/12/2000"));
		a1.setCin("00221549");
		a1.setAdress("Jandouba Rue JCP koi");
		a1.setAdresseMail("KamelSalem@gmail.com");
		a1.setFirstName("Kamel");
		a1.setLastName("Salem");
		a1.setLogin("Kammou");
		a1.setPhone("55123456");
		a1.setPassword("abcdef");
		a1.setPost("Simple Agent");
		proxy2.addAgent(a1);
		
		Agent a2=new Agent();
		a2.setBirthDate(dateFormat.parse("20/12/2000"));
		a2.setCin("12121212");
		a2.setAdress("Le Kef Palace");
		a2.setAdresseMail("HelaAouni@gmail.com");
		a2.setFirstName("Hela");
		a2.setLastName("Aouni");
		a2.setLogin("Hallou");
		a2.setPhone("99123456");
		a2.setPassword("abcdef");
		a2.setPost("Sous Directeur");
		proxy2.addAgent(a2);
		
		Agent a3=new Agent();
		a3.setBirthDate(dateFormat.parse("20/12/1978"));
		a3.setCin("00221550");
		a3.setAdress("Jandouba Rue JCP koi");
		a3.setAdresseMail("TahaHassine@gmail.com");
		a3.setFirstName("Taha");
		a3.setLastName("Hassine");
		a3.setLogin("Toutou");
		a3.setPhone("54000214");
		a3.setPassword("abcdef");
		a3.setPost("Simple Agent");
		proxy2.addAgent(a3);*/
        
         /* Appointment ap1=new Appointment();
          ap1.setCity("02 bis Le Kram");
          ap1.setDescription("I want to apply for a new loan to oppen a new Project But i don't understand the application steps that's why i'm asking for an appointment at home");
          ap1.setGovernorate(Governorate.Tunis);
          ap1.setObject("Applyingfor a Loan");
          ap1.setPlace("Home");
          ap1.setClient(proxy2.findClientById(1));
          ap1.setDate(dateFormat.parse("20/12/2019"));
          proxy2.AffectAppointmentToAgent(proxy2.addAppointment(ap1), 6);
         
        
        
          Appointment ap2=new Appointment();
          ap2.setCity("20 Rue A Place In Ariana");
          ap2.setDescription("I'm Asking for an Appointment to pay my second part of the loan at Home So Please Come Before i start spending the money");
          ap2.setGovernorate(Governorate.Ariana);
          ap2.setObject("Pay the Second Part Of the Loan");
          ap2.setPlace("Home");
          ap2.setClient(proxy2.findClientById(4));
          ap2.setDate(dateFormat.parse("18/08/2019"));
          proxy2.AffectAppointmentToAgent(proxy2.addAppointment(ap2), 6);
        
          Appointment ap3=new Appointment();
          ap3.setCity("35 Rue Sahel Nabeul");
          ap3.setDescription("I want to apply for a new loan to oppen a new Project But i don't understand the application steps that's why i'm asking for an appointment at home");
          ap3.setGovernorate(Governorate.Nabeul);
          ap3.setObject("Applyingfor a Loan");
          ap3.setPlace("Home");
          ap3.setClient(proxy2.findClientById(5));
          ap3.setDate(dateFormat.parse("04/10/2019"));
          proxy2.addAppointment(ap3);
        */
        
        Appointment ap4=new Appointment();
        ap4.setCity("35 Rue Sahel Tunis");
        ap4.setDescription("I want to apply for a new loan to oppen a new Project But i don't understand the application steps that's why i'm asking for an appointment at home");
        ap4.setGovernorate(Governorate.Tunis);
        ap4.setObject("Applyingfor a Loan");
        ap4.setPlace("Home");
        ap4.setClient(proxy2.findClientById(13));
        ap4.setDate(dateFormat.parse("10/04/2019"));
        proxy2.addAppointment(ap4);
        
        Appointment ap5=new Appointment();
        ap5.setCity("12 Rue Taieb Mhiri LA Marsa");
        ap5.setDescription("I want to apply for a new loan to oppen a new Project But i don't understand the application steps that's why i'm asking for an appointment at home");
        ap5.setGovernorate(Governorate.Tunis);
        ap5.setObject("Paying a part of a loan");
        ap5.setPlace("Home");
        ap5.setClient(proxy2.findClientById(14));
        ap5.setDate(dateFormat.parse("20/05/2019"));
        proxy2.addAppointment(ap5);
        
        Appointment ap6=new Appointment();
        ap6.setCity("15 Rue A Place In Nabeul");
        ap6.setDescription("I'm Asking for an Appointment to pay my second part of the loan at Home So Please Come Before i start spending the money");
        ap6.setGovernorate(Governorate.Nabeul);
        ap6.setObject("Pay the Second Part Of the Loan");
        ap6.setPlace("Home");
        ap6.setClient(proxy2.findClientById(15));
        ap6.setDate(dateFormat.parse("18/08/2019"));
        proxy2.addAppointment(ap6);
        
        Appointment ap7=new Appointment();
        ap7.setCity("20 Rue A Place In Ariana");
        ap7.setDescription("I'm Asking for an Appointment to signe a contract in order to be a client ");
        ap7.setGovernorate(Governorate.Ariana);
        ap7.setObject("To signe a contract");
        ap7.setPlace("Home");
        ap7.setClient(proxy2.findClientById(2));
        ap7.setDate(dateFormat.parse("02/06/2019"));
        proxy2.AffectAppointmentToAgent(proxy2.addAppointment(ap7), 17);
        
        Appointment ap8=new Appointment();
        ap8.setCity("Oued El Lil");
        ap8.setDescription("I'm Asking for an Appointment to pay my second part of the loan at Home So Please Come Before i start spending the money");
        ap8.setGovernorate(Governorate.Manouba);
        ap8.setObject("Pay the Second Part Of the Loan");
        ap8.setPlace("Home");
        ap8.setClient(proxy2.findClientById(3));
        ap8.setDate(dateFormat.parse("18/08/2019"));
        proxy2.AffectAppointmentToAgent(proxy2.addAppointment(ap8), 17);
        
        Appointment ap9=new Appointment();
        ap9.setCity("20 Rue A Place In Sousse");
        ap9.setDescription("I'm Asking for an Appointment to pay my second part of the loan at Home So Please Come Before i start spending the money");
        ap9.setGovernorate(Governorate.Sousse);
        ap9.setObject("Pay the Second Part Of the Loan");
        ap9.setPlace("Home");
        ap9.setClient(proxy2.findClientById(1));
        ap9.setDate(dateFormat.parse("01/05/2019"));
        proxy2.addAppointment(ap9);
        
    

		
		

	}

}
