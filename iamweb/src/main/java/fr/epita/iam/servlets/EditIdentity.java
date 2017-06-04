package fr.epita.iam.servlets;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
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

@WebServlet(name="EditIdentity", urlPatterns={"/doEditIdentity"})
public class EditIdentity extends HttpServlet{

  @Inject
  IdentityDataService idDS;
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(EditIdentity.class);
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    Identity updatedId = getIdentity(req);
    try{
      idDS.update(updatedId);  
      resp.sendRedirect(req.getContextPath() + "/success");
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to update the identity. doGet method. {}", e);
    }
    
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      doGet(req, resp);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to update the identity. doPost method. {}", e);
    }
  }

  private Identity getIdentity(HttpServletRequest req){
    DateFormatManager dfm = new DateFormatManager();
    Long uid = Long.parseLong(req.getParameter("uid"));
    String displayName = req.getParameter("displayName");
    String email = req.getParameter("email");
    Date birthdate = dfm.dateFromString(req.getParameter("date"));
    Identity id = new Identity(displayName, email, birthdate);
    id.setUid(uid);
    return id;
  }
  
}
