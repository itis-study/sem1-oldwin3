package ru.itis.servlets.LogAndReg;

import ru.itis.DAO.UserDAO;
import ru.itis.Services.HashService;
import ru.itis.Services.JavaScriptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/RegAndLog/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Cp1251");
        req.setCharacterEncoding("Cp1251");
        String name = req.getParameter("name");
        String password = HashService.hashPassword(req.getParameter("password"));
        String email = req.getParameter("email");

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JavaScriptService.sendJavaScriptResponse(resp, "Please fill in all fields");
        } else if (UserDAO.getUserByName(name) != null) {
            JavaScriptService.sendJavaScriptResponse(resp, "A user with this name already exists");
        } else if (UserDAO.getUserByEmail(email) != null) {
            JavaScriptService.sendJavaScriptResponse(resp, "A user with this email already exists");
        } else if (!isValidEmail(email)) {
            JavaScriptService.sendJavaScriptResponse(resp, "Please enter a valid email address");
        } else {
            UserDAO.saveUser(name, password, email);
            resp.sendRedirect("/RecipeWebsite/login");
        }
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
