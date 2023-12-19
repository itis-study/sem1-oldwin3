package ru.itis.servlets.Profile;

import ru.itis.DAO.RecipeDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.RatingService;
import ru.itis.Services.SubscribeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/deleteProfile")
public class RemoveProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Profile/deleteProfile.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID userID = UserDAO.getUserByName((String) req.getSession().getAttribute("name")).getUser_id();
        RecipeDAO.removeAllRecipeByUserID(userID);
        SubscribeService.unSubscribeAll(userID);
        SubscribeService.unSubscribeAllMySubs(userID);
        UserDAO.deleteUser((String) req.getSession().getAttribute("name"));
        if (req.getSession(false) != null) {
            req.getSession(false).setAttribute("name", null);
        }
        resp.sendRedirect("/RecipeWebsite");
    }
}
