package ru.itis.WEBFILTER;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/adminPanel", "/createNews", "/deleteNews", "/updateNews"})
public class AdminFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        if (!user.getRole().equals("ADMIN")) {
            resp.sendRedirect("/RecipeWebsite");
        }


        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}