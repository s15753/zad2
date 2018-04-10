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
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", Privilege.ANONIM);

        Repository repo = new UserRepository();
        String[] parameters = {"username","password", "passwordCheck","email"};

        if(doFileAreFilled(request,parameters) || !checkIfUserPasswordOk(request)) {
            response.getWriter().print("Bledne dane");
        }else {
            repo.addUser(createUserFromRequest(request));
            response.getWriter().print("Uzytkownik zarejestowany");
        }
    }

    private boolean doFileAreFilled(HttpServletRequest request, String[] parameters){
        for(int i = 0; i < parameters.length; i++) {
            if (request.getParameter(parameters[i]).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    private boolean checkIfUserPasswordOk(HttpServletRequest request) {
        return request.getParameter("password").equalsIgnoreCase(request.getParameter("passwordCheck"));
    }

    private User createUserFromRequest(HttpServletRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setUsertype(Privilege.REGULAR);
        return newUser;
    }
}
