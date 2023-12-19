package ru.itis.servlets.Recipes;

import ru.itis.DAO.RecipeDAO;
import ru.itis.entities.main.Recipe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BestRecipesServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recipe> bestRecipe = RecipeDAO.getBestRecipe();
        req.setAttribute("bestRecipe", bestRecipe);
        req.getRequestDispatcher("/WEB-INF/recipe/topRecipes.jsp").forward(req,resp);
    }
}
