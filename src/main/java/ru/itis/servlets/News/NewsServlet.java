package ru.itis.servlets.News;

import ru.itis.DAO.NewsDAO;
import ru.itis.entities.main.News;
import ru.itis.Services.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/News")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        if(name != null) {
            News news = NewsDAO.getNewsByName(name);
            ImageService imageService = new ImageService();
            req.setAttribute("image", imageService.getImage("news",name));
            req.setAttribute("news",news);
            req.getRequestDispatcher("/WEB-INF/News/getNews.jsp").forward(req, resp);
        }
        else {
            List<News> news = NewsDAO.getAllNews();
            req.getSession().setAttribute("news",news);
            req.getRequestDispatcher("/WEB-INF/News/News.jsp").forward(req, resp);
        }
    }
}
