package ru.itis.servlets.Profile;

import ru.itis.DAO.UserDAO;
import ru.itis.Services.HashService;
import ru.itis.entities.main.User;
import ru.itis.Services.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/UpdateProfile")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Profile/updateProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserDAO.getUserByName((String) req.getSession().getAttribute("name"));

        String oldName = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String information = user.getInformation();
        String newName = oldName;
        byte[] image = user.getImage();
        InputStream inputStream = req.getPart("image").getInputStream();

        if (!req.getParameter("name").isEmpty()) {
            newName = req.getParameter("name");
        }
        if (!req.getParameter("email").isEmpty()) {
            email = req.getParameter("email");
        }
        if(req.getPart("image").getInputStream().available() != 0){
            ImageService imageService = new ImageService();
            image = imageService.setImage(inputStream);
        }
        if (!req.getParameter("information").isEmpty()) {
            information = req.getParameter("information");
        }
        if (!req.getParameter("password").isEmpty()) {
            password = HashService.hashPassword(req.getParameter("password"));
        }
        UserDAO.updateUser(oldName, newName, email, image, password, information);
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}
