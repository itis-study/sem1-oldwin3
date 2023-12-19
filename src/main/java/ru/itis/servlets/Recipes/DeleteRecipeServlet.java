package ru.itis.servlets.Recipes;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.RatingService;
import ru.itis.entities.main.Recipe;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteRecipe")
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerName = (String) req.getParameter("ownerName");
        String name = (String) req.getParameter("name");

        if (ownerName != null && (RecipeDAO.getRecipeByName(name).getUser_id().
                equals(UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getUser_id())
                || UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getRole().equals("ADMIN"))) {
            req.setAttribute("editingRights", true);
            String recipes = req.getParameter("name");
            req.setAttribute("recipes", recipes);
            req.getRequestDispatcher("/WEB-INF/recipe/removeRecipe.jsp").forward(req, resp);
        } else {
            List<Recipe> recipes = RecipeDAO.getAllRecipeOwnedByUsername((String) req.getSession().getAttribute("name"));
            req.setAttribute("recipes", recipes);
            req.getRequestDispatcher("/WEB-INF/recipe/removeRecipe.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipeName = req.getParameter("recipeName");
        String name = req.getParameter("name");
        User user = (UserDAO.getUserByName((String) req.getSession().getAttribute("name")));
        if (recipeName != null && !recipeName.isEmpty()) {
            Recipe recipe = RecipeDAO.getRecipeByName(recipeName);
            if(recipe.getRating() != 0.0){
                Double rating = RatingService.getSumRatingByRecipe(recipe.getRecipe_id());
                Integer countRows = RatingService.countRows(recipe.getRecipe_id());
                if(user.getRatingCount() == 0){
                    UserDAO.updateRatingUserByID(user.getUser_id(), 0.0, 0);
                }
                UserDAO.updateRatingUserByID(user.getUser_id(), (user.getRating() * user.getRatingCount()  - rating) /
                                (user.getRatingCount() - countRows),
                        user.getRatingCount() - countRows);
            }
            UserDAO.updateRecipeNumberByName(user.getName(), user.getRecipeNumber() - 1);
            RecipeDAO.removeRecipe(recipeName);
        }
        else if (name != null)  {
            Recipe recipe = RecipeDAO.getRecipeByName(name);
            if(RecipeDAO.getRecipeByName(name).getRating() != 0.0){
                Double rating = RatingService.getSumRatingByRecipe(recipe.getRecipe_id());
                Integer countRows = RatingService.countRows(recipe.getRecipe_id());
                UserDAO.updateRatingUserByID(user.getUser_id(), user.getRating() - rating,
                        user.getRatingCount() - countRows);
            }
            RecipeDAO.removeRecipe(name);
        }
        UserDAO.updateRecipeNumberByName(user.getName(), user.getRecipeNumber() - 1);
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}

