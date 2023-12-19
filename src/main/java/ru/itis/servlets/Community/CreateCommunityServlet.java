package ru.itis.servlets.Community;

import ru.itis.DAO.CommentsDAO;
import ru.itis.DAO.CommunityDAO;
import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.ImageService;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/createCommunity")
@MultipartConfig
public class CreateCommunityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        if(user.getCommunity_id() != null){
            resp.getWriter().write("You cannot own more than 1 group");
            return;
        }
        else {
            req.getRequestDispatcher("/WEB-INF/Community/createCommunity.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);

        String name = req.getParameter("name");

        String description = req.getParameter("description");

        InputStream inputStream = req.getPart("image").getInputStream();
        ImageService imageService = new ImageService();
        byte[] image = imageService.setImage(inputStream);

        CommunityDAO.saveCommunity(name, description, image, user.getUser_id());
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}
