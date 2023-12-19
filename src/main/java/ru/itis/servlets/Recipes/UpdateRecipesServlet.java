package ru.itis.servlets.Recipes;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.Recipe;
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

@WebServlet("/UpdateRecipes")
@MultipartConfig
public class UpdateRecipesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String ownerName = (String) req.getParameter("ownerName");

        if (ownerName != null && (RecipeDAO.getRecipeByName(name).getUser_id().
                equals(UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getUser_id())
                || UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getRole().equals("ADMIN"))) {
            req.setAttribute("editingRights", true);
            String recipes = req.getParameter("name");
            req.setAttribute("recipes", recipes);
            req.getRequestDispatcher("/WEB-INF/recipe/updateRecipe.jsp").forward(req, resp);
        } else {
            List<Recipe> recipes = RecipeDAO.getAllRecipeOwnedByUsername((String) req.getSession().getAttribute("name"));
            req.setAttribute("recipes", recipes);
            req.getRequestDispatcher("/WEB-INF/recipe/updateRecipe.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipeName = req.getParameter("recipeName");
        if(recipeName == null){
            recipeName = (String) req.getParameter("name");
        }
        Recipe recipe = RecipeDAO.getRecipeByName(recipeName);

        String oldName = recipe.getName();
        String description = recipe.getDescription();
        byte[] image = recipe.getImage();
        InputStream inputStream = req.getPart("image").getInputStream();
        String ingredients = recipe.getIngredients();
        String stages = recipe.getStages();
        String newName = oldName;
        if (!req.getParameter("newName").isEmpty()) {
            newName = req.getParameter("newName");
        }
        if (!req.getParameter("description").isEmpty()) {
            description = req.getParameter("description");
        }
        if(req.getPart("image").getInputStream().available() != 0){
            ImageService imageService = new ImageService();
            image = imageService.setImage(inputStream);
        }
        if (!req.getParameter("ingredients").isEmpty()) {
            ingredients = req.getParameter("ingredients");
        }
        if (!req.getParameter("stages").isEmpty()) {
            stages = req.getParameter("stages");
        }
        RecipeDAO.updateRecipe(oldName, newName, description, image, ingredients, stages);
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}
