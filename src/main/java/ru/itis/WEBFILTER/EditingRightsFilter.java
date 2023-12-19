package ru.itis.WEBFILTER;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/recipe")
public class EditingRightsFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String name = request.getParameter("name");
        String userName = (String) req.getSession().getAttribute("name");
        if (name != null && userName != null) {
            if (RecipeDAO.getRecipeByName(name).getUser_id().
                    equals(UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getUser_id())) {
                req.setAttribute("editingRights", true);
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
