package web;

import domain.User;
import domain.Privilege;
import repository.Repository;
import repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showUsers")
public class ShowUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", Privilege.ADMIN);
        Repository repo = new UserRepository();
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		printHeader(out);
		getUserValues(out,repo);
		printFooter(out);
    }

    private void printHeader(PrintWriter writer) {
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<h1>Lista uzytkownikow</h1><br/>");
		writer.println("<table border=\"2\"><tr><td>Username</td><td>Email</td><td>Privileges</td></tr>");
	}
	
	private void printFooter(PrintWriter writer) {
		writer.println("</table>");
		writer.println("</body>");
		writer.println("</html>");
	}

	private void getUserValues(PrintWriter writer, Repository repo) {
		java.util.List<User> userList = repo.getAllUsers();
		for(User user : userList) {
			writer.println("<tr><td>" + user.getUsername() + "</td><td>" + user.getEmail() + "</td><td>" + user.getUsertype() + "</td></tr>");
		}
	}
}
