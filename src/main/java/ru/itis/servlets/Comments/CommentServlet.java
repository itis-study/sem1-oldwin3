package ru.itis.servlets.Comments;

import ru.itis.DAO.CommentsDAO;
import ru.itis.entities.main.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String recipeName = (String) req.getSession().getAttribute("recipeName");
//        List<Comment> comments = CommentsDAO.getAllCommentsOwnedByRecipe(recipeName);
//        System.out.println(comments.toString());
//        req.getSession().setAttribute("comments", comments);
//        req.getRequestDispatcher("/WEB-INF/Comment/comment.jsp").forward(req, resp);
//    }
}
