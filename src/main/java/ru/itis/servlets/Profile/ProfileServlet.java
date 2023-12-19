package ru.itis.servlets.Profile;

import ru.itis.DAO.CommunityDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.ImageService;
import ru.itis.Services.SubscribeService;
import ru.itis.entities.main.Community;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getSession().getAttribute("name");
        List<User> userList = SubscribeService.getAuthorsByUser(UserDAO.getUserByName(name).getUser_id());
        if (userList.isEmpty()){
            req.setAttribute("users", null);
        }
        else {
            req.setAttribute("users", userList);
        }
        req.setAttribute("user", UserDAO.getUserByName(name));
        User user = UserDAO.getUserByName(name);
        List<Community> communities = CommunityDAO.getAllCommunityByUserIn(user.getUser_id());
        req.setAttribute("communities", communities);
        ImageService imageService = new ImageService();
        req.setAttribute("image", imageService.getImage("users", name));

        req.getRequestDispatcher("/WEB-INF/Profile/profile.jsp").forward(req,resp);
    }

}
