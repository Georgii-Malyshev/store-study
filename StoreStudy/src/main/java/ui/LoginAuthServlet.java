package ui;

import domain.auth.AuthenticationObject;
import domain.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginAuthServlet")
public class LoginAuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String email = request.getParameter("email");
	String password = request.getParameter("password");

	AuthenticationObject authenticationObject = new AuthenticationObject();
	User user = authenticationObject.getUserByCredentials(email, password);
	if (user.getId() != -1) {
	    HttpSession session = request.getSession();
	    session.setAttribute("user", user);
	    response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
	} else {
	    response.sendError(401);
	}
    }
}