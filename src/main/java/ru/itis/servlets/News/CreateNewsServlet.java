package ru.itis.servlets.News;

import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
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

@WebServlet("/createNews")
@MultipartConfig
public class CreateNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        if (user.getRole().equals("ADMIN")) {
            req.getRequestDispatcher("/WEB-INF/News/createNews.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Cp1251");
        req.setCharacterEncoding("Cp1251");
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        InputStream inputStream = req.getPart("image").getInputStream();
        ImageService imageService = new ImageService();
        byte[] image = imageService.setImage(inputStream);
        NewsDAO.saveNews(user.getUser_id(), name, text, image);
        resp.sendRedirect("/RecipeWebsite/adminPanel");
    }
}
