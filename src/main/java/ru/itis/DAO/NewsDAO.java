package ru.itis.DAO;

import ru.itis.entities.main.News;
import ru.itis.Services.ImageService;
import ru.itis.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NewsDAO {
    public static void saveNews(UUID user_id, String name, String text, byte[] image){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().
                prepareStatement("INSERT INTO news ( userid, name, text, image) values (?, ?, ?, ?) ")){
            preparedStatement.setObject(1, user_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, text);
            preparedStatement.setBytes(4, image);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static News getNewsByName(String name){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().
                prepareStatement("SELECT * FROM news WHERE name=?")){
            preparedStatement.setString(1, name);
            News news = new News();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                news.setNews_id((UUID) resultSet.getObject("id"));
                news.setUser_id((UUID) resultSet.getObject("userId"));
                news.setName(resultSet.getString("name"));
                news.setText(resultSet.getString("text"));
                news.setImage(resultSet.getBytes("image"));
                ImageService imageService = new ImageService();
                news.setImageForSrc(imageService.getImage("news", resultSet.getString("name")));
            }
            if(news.getName() == null){
                news = null;
            }
            return news;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<News> getAllNews() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM news")) {
            List<News> newsList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setNews_id((UUID) resultSet.getObject("id"));
                news.setUser_id((UUID) resultSet.getObject("userId"));
                news.setName(resultSet.getString("name"));
                news.setText(resultSet.getString("text"));
                news.setImage(resultSet.getBytes("image"));
                ImageService imageService = new ImageService();
                news.setImageForSrc(imageService.getImage("news", resultSet.getString("name")));
                newsList.add(news);
            }
            if (newsList == null) {
                newsList = null;
            }
            return newsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateNews(String oldName, String newName, String text, byte[] image){
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE news SET name=?, text=?, image=? WHERE name=?")) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, text);
            preparedStatement.setBytes(3, image);
            preparedStatement.setString(4, oldName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteNews(String name){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM news WHERE name = ?")){
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
