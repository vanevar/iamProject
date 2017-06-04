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

@WebServlet(name="SuccessServlet", urlPatterns={"/success"})
public class SuccessServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(CreateIdentityServlet.class);
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String succMsg = "Operation was completed successfully";
    req.setAttribute("succ_msg", succMsg);
    try{
      RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/identityList");
      dispatcher.forward(req, resp);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to reach the list view after a succesful operation the  doGet method. {}", e);
    } 
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      doGet(req, resp);
    }catch(Exception e){
      LOGGER.error("An error ocurred when trying to reach the list view after a succesful operation the  doPost method. {}", e);
    }
    
  }
  
}
