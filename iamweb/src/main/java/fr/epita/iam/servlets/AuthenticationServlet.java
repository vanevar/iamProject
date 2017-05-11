package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.services.IdentityHibernateDAO;

@WebServlet(name="AuthenticationServlet", urlPatterns={"/authenticate"})
public class AuthenticationServlet extends HttpServlet{
  
  private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
  
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    LOGGER.info("tried to authenticate with this login {}", login);
  }

}
