package fr.epita.iam.servlets;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.DateFormatManager;
import fr.epita.iam.services.IdentityDataService;

@WebServlet(name="CreateIdentityServlet", urlPatterns={"/doCreateIdentity"})
public class CreateIdentityServlet extends HttpServlet{

  @Inject
  IdentityDataService idDS;
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(CreateIdentityServlet.class);
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    
    String warMsg="";
    Identity newId = getNewIdentity(req);
    if(newId.getDisplayName() == null || newId.getDisplayName().isEmpty())
    {
      warMsg = "An identity must have a display name.";
    }
    if(!warMsg.isEmpty())
    {
      req.setAttribute("war_msg", warMsg);
      try{
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createIdentity.jsp");
        dispatcher.forward(req, resp);
      }catch(Exception e)
      {
        LOGGER.error("An error ocurred when trying to refresh the  doGett method. {}", e);
        req.setAttribute("errorString", e.getMessage());
        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/_errorPage.jsp");
        dispatcher.forward(req, resp);
      }      
    }
    else{
      try{
        idDS.write(newId);  
        resp.sendRedirect(req.getContextPath() + "/success");
      }catch(Exception e)
      {
        LOGGER.error("An error ocurred when trying write the identity. doGet method. {}", e);
      }
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
    doGet(req, resp);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying write the identity. doPost method. {}", e);
    }
  }

  private Identity getNewIdentity(HttpServletRequest req){
    DateFormatManager dfm = new DateFormatManager();
    String dispName = req.getParameter("displayName");
    String email = req.getParameter("email");
    Date birthdate = dfm.dateFromString(req.getParameter("date"));
    return new Identity(dispName, email, birthdate);
  }
  
}
