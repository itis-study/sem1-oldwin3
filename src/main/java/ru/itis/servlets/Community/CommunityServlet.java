package ru.itis.servlets.Community;

import ru.itis.DAO.CommunityDAO;
import ru.itis.DAO.NewsDAO;
import ru.itis.DAO.UserDAO;
import ru.itis.Services.ImageService;
import ru.itis.entities.main.Community;
import ru.itis.entities.main.News;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/community")
public class CommunityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String userName = (String) req.getSession().getAttribute("name");
        String userNameParametr = (String) req.getParameter("userName");
        if (name != null) {
            Community community = CommunityDAO.getCommunityByName(name);

            if(UserDAO.getUserByName(userName).getCommunity_id() != null &&
                    UserDAO.getUserByName(userName).getCommunity_id()
                            .toString().equals(community.getCommunity_id().toString())){
                Boolean isCreator = true;
                req.setAttribute("isCreator", true);
            }
            else {
                req.setAttribute("isCreator", false);
            }
            List<User> users = CommunityDAO.getAllUserCommunity(community.getCommunity_id());
            Boolean join = false;
            for (User user : users) {
                if (user.getUser_id().toString().equals(UserDAO.getUserByName(userName).getUser_id().toString())) {
                    join = true;
                    break;
                }
            }
            if(join){
                req.setAttribute("join", join);
            }
            req.setAttribute("users", users);

            ImageService imageService = new ImageService();
            req.setAttribute("image", imageService.getImage("communities", name));
            req.setAttribute("community", community);
            req.getRequestDispatcher("/WEB-INF/Community/community.jsp").forward(req, resp);
        } else if (userNameParametr != null) {
            User user = UserDAO.getUserByName(userNameParametr);
            List<Community> communities = CommunityDAO.getAllCommunityByUserIn(user.getUser_id());
            req.setAttribute("communities", communities);
            req.getRequestDispatcher("/WEB-INF/Community/communitys.jsp").forward(req, resp);
        } else {
            List<Community> communities = CommunityDAO.getAllCommunity();
            req.setAttribute("communities", communities);
            req.getRequestDispatcher("/WEB-INF/Community/communitys.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userName = (String) req.getSession().getAttribute("name");
        String name = (String) req.getParameter("name");
        Community community = CommunityDAO.getCommunityByName(name);
        if (action != null) {
            if (action.equals("join")) {
                List<User> users = CommunityDAO.getAllUserCommunity(community.getCommunity_id());
                // users.contains(UserDAO.getUserByName(userName)) не сработало из-за разных объектов user
                for (User user : users) {
                    if (user.getUser_id().toString().equals(UserDAO.getUserByName(userName).getUser_id().toString())) {
                        resp.getWriter().write("you are already a member of the");
                        return;
                    }
                }
                UserDAO.updateCommunityIDByName(userName, community.getCommunity_id(), true, community.getUsersNumber());
            } else if (action.equals("leave")) {
                if(UserDAO.getUserByName(userName).getCommunity_id() != null &&
                        UserDAO.getUserByName(userName).getCommunity_id()
                                .toString().equals(community.getCommunity_id().toString())){
                    resp.getWriter().write("you can't leave the group you created, you can only delete it");
                    return;
                }
                UserDAO.updateCommunityIDByName(userName, community.getCommunity_id(), false, community.getUsersNumber());
            }
        }
        resp.sendRedirect("/RecipeWebsite/community");
    }
}
