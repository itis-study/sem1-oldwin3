package ru.itis.servlets.LogAndReg;

import ru.itis.DAO.UserDAO;
import ru.itis.Services.HashService;
import ru.itis.Services.JavaScriptService;
import ru.itis.entities.main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/RegAndLog/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = HashService.hashPassword(req.getParameter("password"));
        User user = UserDAO.getUserByName(name);
        if (user == null) {
            JavaScriptService.sendJavaScriptResponse(resp, "you made a mistake in filling in the fields" +
                    " try again");
            return;
        }
        if (user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("role", user.getRole()); // проверка идёт не по сессии, нужно только для html отображения
            session.setAttribute("name", name);
            JavaScriptService.sendJavaScriptResponse(resp, "Congratulations you have successfully logged in");
            resp.sendRedirect("/RecipeWebsite/Profile");
        } else {
            JavaScriptService.sendJavaScriptResponse(resp, "Invalid password try again");
        }
    }
}
