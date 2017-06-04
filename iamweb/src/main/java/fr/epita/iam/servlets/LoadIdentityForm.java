  package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name="LoadIdentityFormServlet", urlPatterns={"/identityForm"})
public class LoadIdentityForm extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(LoadIdentityForm.class);

  public LoadIdentityForm() {
    super();
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try{
      RequestDispatcher dispatcher = request.getServletContext()
          .getRequestDispatcher("/WEB-INF/views/createIdentity.jsp");
      dispatcher.forward(request, response);
    }catch(Exception e)
    {
      LOGGER.error("Error ocurred when trying to reach the Identity Form view. doGet, {}", e);
    } 
  }
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try{
      doGet(request, response);
  
    }catch(Exception e)
    {
      LOGGER.error("Error ocurred when trying to reach the Identity Form view. doPost, {}", e);
    }
  }

}
