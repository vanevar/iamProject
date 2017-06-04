package fr.epita.iam.utils;

import javax.servlet.http.HttpSession;

import fr.epita.iam.datamodel.User;

public class SessionStorageHelper {

  private SessionStorageHelper() {
    throw new IllegalAccessError("SessionStorageHelper class");
  }
  
  /*
   * Store user info in Session.
   */
  public static void storeLoggedUser(HttpSession session, User loggedUser) {    
      session.setAttribute("loggedUser", loggedUser);
  }


  // Get the user information stored in the session.
  public static User getLoggedUser(HttpSession session) {
      return (User) session.getAttribute("loggedUser");
  }
  
  //Remove User info from session 
  public static void removeLoggedUser(HttpSession session){
    session.removeAttribute("loggedUser");
  }
  
}
