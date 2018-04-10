package web;

import domain.Privilege;
import repository.Repository;
import repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changePrivilege")
public class ChangePrivilegeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Repository repo = new UserRepository();

        repo.addPremiumPrivilege(request.getParameter("username"));
        response.getWriter().print("Uprawnienia zmienione");
    }
}
