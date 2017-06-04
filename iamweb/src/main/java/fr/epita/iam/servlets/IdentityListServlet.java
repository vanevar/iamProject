package fr.epita.iam.servlets;

import java.io.IOException;
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

  private static final Logger LOGGER = LogManager.getLogger(IdentityListServlet.class);
  
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException 
  {    
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    LOGGER.info("Identity Data Service : {}", idService);
    DateFormatManager dfm = new DateFormatManager();
    String search = req.getParameter("search");
    String field = req.getParameter("field");
    String msg = req.getParameter("msg");
    
    List<Identity> results;
    if( search == null || field == null || search.isEmpty() || field.isEmpty() )
    {
      results = idService.search(new Identity(null, null, null));
    }
    else
    {
      results = searchByField(dfm, search, field);
      
    }
    
    if(results.isEmpty())
    {
      msg = "No identities foud.";
    }
 
    req.setAttribute("msg", msg);
    req.setAttribute("identities", results);       
   
    // Forward to list.jsp
    try{
      RequestDispatcher dispatcher = req.getServletContext()
              .getRequestDispatcher("/WEB-INF/views/list.jsp");
      dispatcher.forward(req, resp);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to reach the list page. doGet method. {}", e);
    }
  }

  private List<Identity> searchByField(DateFormatManager dfm, String search, String field) {
    List<Identity> results;
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
    return results;
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try{
      doGet(request, response);
    }catch(Exception e)
    {
      LOGGER.error("An error ocurred when trying to reach the list page. doPost method. {}", e);
    }
  }
}
