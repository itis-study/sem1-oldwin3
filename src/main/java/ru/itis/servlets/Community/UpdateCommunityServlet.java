package ru.itis.servlets.Community;

import ru.itis.DAO.CommunityDAO;
import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.ImageService;
import ru.itis.entities.main.Community;
import ru.itis.entities.main.News;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/updateCommunity")
@MultipartConfig
public class UpdateCommunityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        Community community = CommunityDAO.getCommunityByUUID(user.getCommunity_id());
        req.setAttribute("community", community);
        req.getRequestDispatcher("/WEB-INF/Community/UpdateCommunity.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String communityName = req.getParameter("community");

        Community community = CommunityDAO.getCommunityByName(communityName);

        String oldName = community.getName();
        String description = community.getDescription();
        byte[] image = community.getImage();
        InputStream inputStream = req.getPart("image").getInputStream();
        String newName = oldName;
        if (!req.getParameter("newName").isEmpty()) {
            newName = req.getParameter("newName");
        }
        if (!req.getParameter("description").isEmpty()) {
            description = req.getParameter("description");
        }
        if (req.getPart("image").getInputStream().available() != 0) {
            ImageService imageService = new ImageService();
            image = imageService.setImage(inputStream);
        }
        CommunityDAO.updateCommunity(oldName, newName, description, image);
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}
