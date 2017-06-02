package fr.epita.iam.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.AuthenticationDataService;
import fr.epita.iam.utils.SessionStorageHelper;

@WebServlet(name="AuthenticationServlet", urlPatterns={"/authenticate"})
public class AuthenticationServlet extends HttpServlet{
  
  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
  
  @Inject
  AuthenticationDataService authDS;
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    LOGGER.info("Auth instance is: {}" , authDS);
    
    String msg = "";
    User user = null;
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    
    if (login == null || password == null
        || login.length() == 0 || password.length() == 0) {
      msg = "Required username and password!";
    } else {
      user = authDS.authenticate(login, password);
      if (user == null) {
        msg = "User Name or password invalid";
       }
    } 

    // If error, go to index page again
    if (!msg.isEmpty()) {
      // Store error in request attribute, before forward.
      req.setAttribute("msg", msg);
      RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
      dispatcher.forward(req, resp);
    }
 
    // If no error
    // Store user information in Session
    // And redirect to welcome page.
    else {
        HttpSession session = req.getSession();
        SessionStorageHelper.storeLoggedUser(session, user);   
        resp.sendRedirect(req.getContextPath() + "/welcome");
    }
  }
  
}
