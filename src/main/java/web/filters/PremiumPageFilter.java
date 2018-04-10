package web.filters;

import domain.Privilege;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/premium.jsp"})
public class PremiumPageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        Privilege currentUser = (Privilege) session.getAttribute("currentUser");

        if (hasAccesToPremiumPage(currentUser)){
            chain.doFilter(req, resp);
        } else {
            resp.getWriter().print("Brak uprawnien");
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private boolean hasAccesToPremiumPage(Privilege userType){
       if(userType == Privilege.ANONIM || userType == Privilege.REGULAR || userType == null) {
           return false;
       } else {
           return true;
       }
    }

}
