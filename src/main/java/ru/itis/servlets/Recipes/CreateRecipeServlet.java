package ru.itis.servlets.Recipes;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.EmailService;
import ru.itis.Services.SubscribeService;
import ru.itis.entities.main.User;
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

@WebServlet("/CreateRecipe")
@MultipartConfig
public class CreateRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/recipe/createRecipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Cp1251");
        req.setCharacterEncoding("Cp1251");

        String username = (String) req.getSession().getAttribute("name");
        User user = UserDAO.getUserByName(username);
        UserDAO.updateRecipeNumberByName(username, user.getRecipeNumber() + 1);
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        InputStream inputStream = req.getPart("image").getInputStream();
        ImageService imageService = new ImageService();
        byte[] image = imageService.setImage(inputStream);

        String[] ingredients = req.getParameterValues("ingredients");
        StringBuilder ingredientsString = new StringBuilder("");
        if (ingredients != null && ingredients.length > 0) {
             ingredientsString = new StringBuilder();
            for (String ingredient : ingredients) {
                ingredientsString.append(ingredient).append("\n");
            }
        }
        String stages = req.getParameter("stages");
        RecipeDAO.saveRecipe(name, description, image, ingredientsString.toString(), stages, user.getUser_id());

        List<User> subs = SubscribeService.getSubscribers(UserDAO.getUserByName(username).getUser_id());
        for (User suber: subs) {
            EmailService emailService = new EmailService(suber.getEmail(), "Your favorite author has a new recipe",
                    "Here is a link to this recipe called " + name + " http://localhost:8080/RecipeWebsite/recipe?name="
                            + name);
            Thread thread = new Thread(emailService);
            thread.start();
        }
        resp.sendRedirect("/RecipeWebsite/Profile");
    }
}
