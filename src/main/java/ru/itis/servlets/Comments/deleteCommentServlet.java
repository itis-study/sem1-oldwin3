package ru.itis.servlets.Comments;

import ru.itis.DAO.CommentsDAO;
import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.Comment;
import ru.itis.entities.main.News;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/deleteComments")
public class deleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        if (user.getRole().equals("ADMIN")) {
            List<Comment> comments = CommentsDAO.getAllComments();
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/WEB-INF/Comment/deleteComments.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID commentsId = UUID.fromString(req.getParameter("comments"));
        if (commentsId.toString() != null && !commentsId.toString().equals(null)) {
            CommentsDAO.removeComment(commentsId);
        }
        resp.sendRedirect("/RecipeWebsite/adminPanel");
    }
}
