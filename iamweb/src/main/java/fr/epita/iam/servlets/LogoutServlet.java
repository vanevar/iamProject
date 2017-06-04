package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.utils.SessionStorageHelper;

@WebServlet(name="LogoutServlet", urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    try{
      SessionStorageHelper.removeLoggedUser(session);
      resp.sendRedirect(req.getContextPath() + "/home");
    }catch(Exception e)
    {
      LOGGER.error("Problem encountered when trying to log out {}", e);
    }
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      doGet(req, resp);
    }catch(Exception e)
    {
      LOGGER.error("Problem encountered when trying to log out {}", e);
    }
  }

}
