package ru.itis.servlets.News;

import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.News;
import ru.itis.entities.main.Recipe;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/deleteNews")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        if (user.getRole().equals("ADMIN")) {
            List<News> news = NewsDAO.getAllNews();
            req.setAttribute("news", news);
            req.getRequestDispatcher("/WEB-INF/News/deleteNews.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String news = req.getParameter("news");
        if (news != null && !news.isEmpty()) {
            NewsDAO.deleteNews(news);
        }
        resp.sendRedirect("/RecipeWebsite/adminPanel");
    }
}
