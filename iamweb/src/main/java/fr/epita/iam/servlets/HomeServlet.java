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

import fr.epita.iam.utils.SessionStorageHelper;

@WebServlet(urlPatterns = { "/home"})
public class HomeServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(HomeServlet.class);

  public HomeServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse response)
        throws ServletException, IOException {

    // Forward to Welcome page if logged, or index if not logged
    HttpSession session = req.getSession();
    RequestDispatcher dispatcher ;
    if(SessionStorageHelper.getLoggedUser(session) != null )
    {
      dispatcher= this.getServletContext().getRequestDispatcher("/welcome");
    }
    else
    {
      dispatcher= this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
    }
    try{
    dispatcher.forward(req, response);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to reach the authentication page. doGet method. {}", e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try{
      doGet(request, response);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to execute doPost method. {}", e);
    }
  }

  
}
