package filter;

import pojo.Patient;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class adminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        HttpServletResponse httpServletResponse=(HttpServletResponse) response;

        HttpSession session=httpServletRequest.getSession();
        Patient user= (Patient) session.getAttribute("user");
        if(user!=null&&user.getPatientAccount().equalsIgnoreCase("root")){
//            request.getRequestDispatcher("admin.jsp").forward(httpServletRequest,httpServletResponse);
            chain.doFilter(request,response);
        }else{
            System.out.println("Request not allowed!");
            httpServletResponse.sendRedirect("index.jsp");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
