package web;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
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
import java.io.IOException;

import static domain.Privilege.*;

@WebServlet("/Log")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", ANONIM);
        Repository repo = new UserRepository();

        if(checkUserData(request,repo)) {
            changeSessionAtribute(session, repo, request);
            Privilege userType = getUserType(session);

            switch (userType){
                case REGULAR:
                    response.sendRedirect("user.jsp");
                    break;
                case PREMIUM:
                    response.sendRedirect("premium.jsp");
                    break;
                case ADMIN:
                    response.sendRedirect("changePrivilege.jsp");
                    break;
                default:
                    response.getWriter().print("Bledne dane logowanie");
            }
        } else {
            response.getWriter().print("Bledne dane");
        }
    }
    private boolean checkUserData(HttpServletRequest request, Repository repo) {
       return request.getParameter("password").equals(repo.getUserByName(request.getParameter("username")).getPassword());
    }
    private void changeSessionAtribute(HttpSession session, Repository repo, HttpServletRequest request){
        session.setAttribute("currentUser", repo.getUserByName(request.getParameter("username")).getUsertype());
    }
    private Privilege getUserType(HttpSession session){
        return (Privilege) session.getAttribute("currentUser");
    }
}
