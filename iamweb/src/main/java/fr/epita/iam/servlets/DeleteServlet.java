package fr.epita.iam.servlets;

import java.io.IOException;

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
import fr.epita.iam.services.IdentityDataService;

@WebServlet(name="DeleteServlet", urlPatterns={"/delete"})
public class DeleteServlet extends HttpServlet{
  @Inject
  IdentityDataService idDS;
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(LoadIdentityForm.class);
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    String uid = req.getParameter("id");
    String errorString = "";
    
    if(uid != null && !uid.isEmpty())
    {
      Identity id = new Identity(null, null, null);
      
      try{
        id.setUid(Integer.parseInt(uid));
        idDS.delete(id); 
      }catch(Exception e)
      {
        LOGGER.error("Error ocurred when running the doGet method {}", e);
        errorString = e.getMessage();
      }
      
      if(errorString.isEmpty()) {
        try{
          resp.sendRedirect(req.getContextPath() + "/success");
        }catch(Exception e)
        {
          LOGGER.error("Error ocurred when forwarding to the productlist page. doGet method {}", e);
        }
      }
    }
    else
    {
      if (!errorString.isEmpty()) {
        req.setAttribute("errorString", errorString);
      }
      RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/_errorPage.jsp");
      try{
        dispatcher.forward(req, resp);
      }catch(Exception e)
      {
        LOGGER.error("Error ocurred when forwarding to error page the doGet method {}", e);
      }
    }
  }
    
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      doGet(req, resp);
    }catch(Exception e){
      LOGGER.error("An exception ocurred when trying to execute the doPost {}", e);
    }
  }

  
}
