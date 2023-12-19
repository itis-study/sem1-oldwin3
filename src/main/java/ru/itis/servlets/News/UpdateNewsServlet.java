package ru.itis.servlets.News;

import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.News;
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
import java.util.List;

@WebServlet("/updateNews")
@MultipartConfig
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        if (user.getRole().equals("ADMIN")) {
            List<News> news = NewsDAO.getAllNews();
            System.out.println(news);
            req.setAttribute("news", news);
            req.getRequestDispatcher("/WEB-INF/News/updateNews.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newsName = req.getParameter("news");

        News news = NewsDAO.getNewsByName(newsName);

        String oldName = news.getName();
        String text = news.getText();
        byte[] image = news.getImage();
        InputStream inputStream = req.getPart("image").getInputStream();
        String newName = oldName;
        if (!req.getParameter("newName").isEmpty()) {
            newName = req.getParameter("newName");
        }
        if (!req.getParameter("text").isEmpty()) {
            text = req.getParameter("text");
        }
        if(req.getPart("image").getInputStream().available() != 0){
            ImageService imageService = new ImageService();
            image = imageService.setImage(inputStream);
        }
        NewsDAO.updateNews(oldName, newName, text, image);
        resp.sendRedirect("/RecipeWebsite/adminPanel");
    }
}
