package fr.epita.iam.utils;

import javax.servlet.http.HttpSession;

import fr.epita.iam.datamodel.User;

public class SessionStorageHelper {

//Store user info in Session.
  public static void storeLoggedUser(HttpSession session, User loggedUser) {

      // On the JSP can access ${loggedUser}
      session.setAttribute("loggedUser", loggedUser);
      session.setAttribute("loggedUserDisplayName", loggedUser.getIdentity().getDisplayName());
  }


  // Get the user information stored in the session.
  public static User getLoggedUser(HttpSession session) {
      User loggedUser = (User) session.getAttribute("loggedUser");
      return loggedUser;
  }
  
}
