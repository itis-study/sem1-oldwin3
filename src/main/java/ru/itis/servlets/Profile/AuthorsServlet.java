package ru.itis.servlets.Profile;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.SubscribeService;
import ru.itis.Services.ImageService;
import ru.itis.entities.main.Recipe;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String userName = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(userName);
        User author = UserDAO.getUserByName(name);
        if(name != null) {
            if(SubscribeService.isSubscribed(author.getUser_id(), user.getUser_id())){
                req.setAttribute("subscribe", true);
            }
            else {
                req.setAttribute("subscribe", false);
            }

            int recipesPerPage = 3;
            Integer currentPage = Integer.valueOf(req.getParameter("page"));
            int totalPageCount = (int) Math.ceil((double) RecipeDAO.countRowsByUser(author.getUser_id()) / recipesPerPage);
            int startIdx = (currentPage - 1) * recipesPerPage;

            List<Recipe> recipes = RecipeDAO.getAllRecipeOwnedByUsernameWithPages(name, recipesPerPage, startIdx);
            ImageService imageService = new ImageService();
            req.setAttribute("totalPageCount", totalPageCount);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("recipes", recipes);
            req.setAttribute("image", imageService.getImage("users",name));
            req.setAttribute("user", author);
            req.getRequestDispatcher("/WEB-INF/Profile/author.jsp").forward(req, resp);
        }
        else {
            List<User> users = UserDAO.getAllAuthors();
            req.setAttribute("users",users);
            req.getRequestDispatcher("/WEB-INF/Profile/authors.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userName = (String) req.getSession().getAttribute("name");
        String name = (String) req.getParameter("name");
        User user = UserDAO.getUserByName(userName);
        User author = UserDAO.getUserByName(name);
        if (action != null) {
            if (action.equals("subscribe")) {
                if(SubscribeService.isSubscribed(author.getUser_id(), user.getUser_id())){
                    resp.sendRedirect("RecipeWebsite/authors?name=" + name + "&page=1");
                    return;
                }
                SubscribeService.subscribe(author.getUser_id(), user.getUser_id());
            }
            if (action.equals("unsubscribe")) {
                if(!SubscribeService.isSubscribed(author.getUser_id(), user.getUser_id())){
                    resp.sendRedirect("RecipeWebsite/authors?name=" + name + "&page=1");
                    return;
                }
                SubscribeService.unSubscribe(author.getUser_id(), user.getUser_id());
            }
        }
    }
}
