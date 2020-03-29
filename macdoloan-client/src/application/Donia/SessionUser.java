package application.Donia;

import tn.esprit.macdoloan.entity.User;

public class SessionUser {
	
	private static User user = null;
	
	
	/*
	 * This function allows us to set user credentials to a static User instance 
	 * */
	public static boolean createSession(User user) {
		SessionUser.user = user;
		return true;
	}
	
	
	 /*
	  * This function allows us to set user to a null value and log out  
	  * */
	public static boolean dropSession() {
		SessionUser.user =null ; 
		return true;
	}

	
	 /*
	  * This function allows us to see whether a session is open or not  
	  * */
	public static boolean requestSession() {
		return (SessionUser.user==null);	
	}

	
	/*
	  * This function allows us to get the connceted user credentials 
	  * */
	public static User getUser() {
		return user;
	}

	
	


}
