package ru.itis.servlets.Community;

import ru.itis.DAO.CommunityDAO;
import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.Community;
import ru.itis.entities.main.News;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteCommunity")
public class DeleteCommunityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        Community community = CommunityDAO.getCommunityByUUID(user.getCommunity_id());
        req.setAttribute("community", community);
        req.getRequestDispatcher("/WEB-INF/Community/deleteCommunity.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String community = req.getParameter("community");
        if (community != null && !community.isEmpty()) {
            CommunityDAO.removeCommunity(CommunityDAO.getCommunityByName(community).getCommunity_id());
        }
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}
