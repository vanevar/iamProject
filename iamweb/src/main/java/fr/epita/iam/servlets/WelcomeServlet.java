package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.utils.SessionStorageHelper;

@WebServlet(name="WelcomeServlet", urlPatterns={"/welcome"})
public class WelcomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(WelcomeServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      HttpSession session = req.getSession();

      // Check we have a user in session
      User loggedUser = SessionStorageHelper.getLoggedUser(session);

      // Not logged in
      if (loggedUser == null) {
        try{
          resp.sendRedirect(req.getContextPath() + "/Home");
          return;
        }catch(Exception e){
          LOGGER.error("An error ocurred when trying to go back to the authentication page. doGet method. {}", e);
        }
      }

      // Store info in request attribute
      req.setAttribute("user", loggedUser);


      // Logged, forward to welcome page
      try{
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/welcome.jsp");
        dispatcher.forward(req, resp);
      }catch(Exception e){
        LOGGER.error("An error ocurred when trying to reach the welcome page. doGet method. {}", e);
      }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try{
      doGet(request, response);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to reach the welcome page. doPost method. {}", e);
    }
  }
}
