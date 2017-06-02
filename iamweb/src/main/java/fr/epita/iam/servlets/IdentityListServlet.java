package fr.epita.iam.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet(name="IdentityListServlet", urlPatterns={"/identityList"})
public class IdentityListServlet extends HttpServlet{

  @Inject
  IdentityDataService idService;
  
  private static final long serialVersionUID = 737861606683223301L;

  private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
  
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException 
  {    
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    LOGGER.info("Identity Data SErvice : {}", idService);
    DateFormatManager dfm = new DateFormatManager();
    String search = req.getParameter("search");
    String field = req.getParameter("field");
    String msg = "";
    
    List<Identity> results = new ArrayList<Identity>();
    if( search == null || field == null || search.isEmpty() || field.isEmpty() )
    {
      results = idService.search(new Identity(null, null, null));
    }
    else
    {
      switch(field){
        case "any":
          results = idService.search(new Identity(search, search, dfm.dateFromString(search)));
          break;
        case "dispName":
          results = idService.search(new Identity(search, null, null));
          break;
        case "email":
          results = idService.search(new Identity(null, search, null));
          break;
        case "birthday":
          results = idService.search(new Identity(null, null, dfm.dateFromString(search)));
          break;
        default:
          results = idService.search(new Identity(null, null, null));
      }
      
    }
    
    if(results.isEmpty())
    {
      msg = "No identities foud.";
    }
 
    req.setAttribute("msg", msg);
    req.setAttribute("identities", results);       
   
    // Forward to list.jsp
    RequestDispatcher dispatcher = req.getServletContext()
              .getRequestDispatcher("/WEB-INF/views/list.jsp");
      dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }
}
