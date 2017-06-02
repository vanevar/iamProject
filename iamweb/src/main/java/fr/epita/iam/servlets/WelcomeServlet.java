package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.utils.SessionStorageHelper;

@WebServlet(name="WelcomeServlet", urlPatterns={"/welcome"})
public class WelcomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      HttpSession session = req.getSession();

      // Check we have a user in session
      User loggedUser = SessionStorageHelper.getLoggedUser(session);

      // Not logged in
      if (loggedUser == null) {
          resp.sendRedirect(req.getContextPath() + "/authenticate");
          return;
      }

      // Store info in request attribute
      req.setAttribute("user", loggedUser);


      // Logged, forward to welcome page
      RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/welcome.jsp");
      dispatcher.forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }
}
