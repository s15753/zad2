package web.filters;

import domain.Privilege;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/index.jsp", "/registration.jsp"})
public class SwitchToSite implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        if(session.getAttribute("currentUser") == Privilege.REGULAR) {
            httpResponse.sendRedirect("user.jsp");
        } else if ( session.getAttribute("currentUser") == Privilege.PREMIUM) {
            httpResponse.sendRedirect("premium.jsp");
        } else {
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
