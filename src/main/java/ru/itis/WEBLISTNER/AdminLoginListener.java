package ru.itis.WEBLISTNER;

import ru.itis.DAO.UserDAO;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class AdminLoginListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = (String) event.getSession().getAttribute("name");
        if (UserDAO.getUserByName(name).getRole().equals("ADMIN")) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(
                        "C:\\Users\\ogrin\\IdeaProjects\\RecipeWebsite\\src\\main\\resources\\AdminLogins.txt",
                        true))) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    writer.println(name + " " + dateFormat.format(date));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}

