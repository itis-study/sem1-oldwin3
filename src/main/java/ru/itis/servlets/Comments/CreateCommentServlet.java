package ru.itis.servlets.Comments;

import ru.itis.DAO.CommentsDAO;
import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.ImageService;
import ru.itis.entities.main.Recipe;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createComment")
public class CreateCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Comment/CreateComment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        String text = req.getParameter("text");
        String recipeName = (String) req.getSession().getAttribute("recipeName");
        Recipe recipe = RecipeDAO.getRecipeByName(recipeName);
        CommentsDAO.saveComment(user.getUser_id(), recipe.getRecipe_id(), text);
        resp.sendRedirect("/RecipeWebsite/recipe?name=" + recipeName);
    }
}
