package ru.itis.servlets.Recipes;

import ru.itis.DAO.CommentsDAO;
import ru.itis.DAO.CommunityDAO;
import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.RatingService;
import ru.itis.entities.main.Comment;
import ru.itis.entities.main.Recipe;
import ru.itis.Services.ImageService;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String name = req.getParameter("name");
        Boolean bestrecipe = Boolean.valueOf(req.getParameter("bestrecipe"));
        String userGlobalName = (String) req.getSession().getAttribute("name");
        req.setAttribute("bestrecipe", bestrecipe);
        if (name != null) {
            Recipe recipe = RecipeDAO.getRecipeByName(name);
            recipe.setIngredients(recipe.getIngredients().replace("\n", "<br/>"));
            Double inputDouble = recipe.getRating();
            Integer rating = (int) Math.round(inputDouble);
            ImageService imageService = new ImageService();
            if (userGlobalName != null) {
                UUID userId = UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getUser_id();
                UUID recipeId = recipe.getRecipe_id();
                if (RatingService.isVotedRecipe(userId,
                        recipeId)) {
                    req.setAttribute("voteText", "You have already voted, your score: " +
                            RatingService.getOldRating(userId, recipeId) + ". You can re-vote");
                } else {
                    req.setAttribute("voteText", "Rate the recipe:");
                }
            }
            List<Comment> comments = CommentsDAO.getAllCommentsOwnedByRecipe(name);
            req.getSession().setAttribute("comments", comments);

            req.getSession().setAttribute("recipeName", recipe.getName());


            req.setAttribute("absRating", inputDouble);
            req.setAttribute("countRating", recipe.getRatingCount());
            req.setAttribute("rating", rating);
            req.setAttribute("recipe", recipe);
            req.setAttribute("image", imageService.getImage("recipes", name));
            req.getRequestDispatcher("/WEB-INF/recipe/recipe.jsp").forward(req, resp);

        } else if (userName != null) {
            int recipesPerPage = 3;
            Integer currentPage = Integer.valueOf(req.getParameter("page"));
            int totalPageCount = (int) Math.ceil((double) RecipeDAO.countRows() / recipesPerPage);
            int startIdx = (currentPage - 1) * recipesPerPage;
            List<Recipe> recipes;
            if (bestrecipe) {
                recipes = RecipeDAO.getAllRecipeOwnedByUsernameWithPagesSortByRating(userName, recipesPerPage, startIdx);
            } else {
                recipes = RecipeDAO.getAllRecipeOwnedByUsernameWithPages(userName, recipesPerPage, startIdx);
            }
            req.setAttribute("userName", userName);
            req.setAttribute("recipes", recipes);
            req.setAttribute("totalPageCount", totalPageCount);
            req.setAttribute("currentPage", currentPage);
            req.getRequestDispatcher("/WEB-INF/recipe/recipes.jsp").forward(req, resp);
        } else {
            int recipesPerPage = 3;
            Integer currentPage = Integer.valueOf(req.getParameter("page"));
            int totalPageCount = (int) Math.ceil((double) RecipeDAO.countRows() / recipesPerPage);
            int startIdx = (currentPage - 1) * recipesPerPage;
            List<Recipe> recipes;
            if (bestrecipe) {
                recipes = RecipeDAO.getAllRecipeWithPagesSortByRating(recipesPerPage, startIdx);
            } else {
                recipes = RecipeDAO.getAllRecipeWithPages(recipesPerPage, startIdx);
            }
            List<Recipe> bestRecipe = RecipeDAO.getBestRecipe();
            req.setAttribute("bestRecipe", bestRecipe);
            req.setAttribute("recipes", recipes);
            req.setAttribute("totalPageCount", totalPageCount);
            req.setAttribute("currentPage", currentPage);
            req.getRequestDispatcher("/WEB-INF/recipe/recipes.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double rating = Double.valueOf(req.getParameter("rating"));
        String name = req.getParameter("name");
        Recipe recipe = RecipeDAO.getRecipeByName(name);
        User user = UserDAO.getUserByName((String) req.getSession().getAttribute("name"));
        UUID userId = user.getUser_id();
        UUID recipeId = recipe.getRecipe_id();
        if (recipe.getUser_id().equals(userId)) {
            resp.sendRedirect("/RecipeWebsite/recipe?name=" + recipe.getName());
        } else if (!RatingService.isVotedRecipe(userId,
                recipeId)) {
            RecipeDAO.updateRatingRecipeByName(name,
                    RatingService.recalculateTheRating(recipe.getRating(), recipe.getRatingCount(), rating),
                    recipe.getRatingCount() + 1.0);
            RatingService.voteRecipe(userId, recipe.getRecipe_id(), rating, RecipeDAO.getRecipeByName(name).getRating());


            User owner = UserDAO.getUserByID(recipe.getUser_id());
            UserDAO.updateRatingUserByID(owner.getUser_id(),
                    RatingService.recalculateTheRating(owner.getRating(), Double.valueOf(owner.getRatingCount()), rating),
                    owner.getRatingCount() + 1);
            resp.sendRedirect("/RecipeWebsite/recipe?name=" + recipe.getName());
        } else {
            Double getOldRating = RatingService.getOldRating(userId, recipeId);
            RecipeDAO.updateRatingRecipeByName(name,
                    RatingService.recalculateTheExistingRating(recipe.getRating(), recipe.getRatingCount(), rating,
                            getOldRating),
                    recipe.getRatingCount());
            RatingService.updateRatingVote(userId, recipeId, rating, RecipeDAO.getRecipeByName(name).getRating());
            System.out.println("========================");
            User owner = UserDAO.getUserByID(recipe.getUser_id());
            UserDAO.updateRatingUserByID(owner.getUser_id(),
                    RatingService.recalculateTheExistingRating(owner.getRating(), Double.valueOf(owner.getRatingCount()),
                            rating, getOldRating),
                    owner.getRatingCount());

            resp.sendRedirect("/RecipeWebsite/recipe?name=" + recipe.getName());
        }

    }
}
