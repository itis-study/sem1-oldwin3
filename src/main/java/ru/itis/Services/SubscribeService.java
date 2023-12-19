package ru.itis.Services;

import ru.itis.DAO.UserDAO;
import ru.itis.entities.main.User;
import ru.itis.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubscribeService {
    public static Boolean isSubscribed(UUID authorID, UUID userId) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT user_id FROM user_author where (author_id, user_id) = (?, ?)")) {
            preparedStatement.setObject(1, authorID);
            preparedStatement.setObject(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            UUID tableUserID = null;
            while (resultSet.next()) {
                tableUserID = (UUID) resultSet.getObject("user_id");
            }
            if (tableUserID == null) {
                return false;
            }
            return tableUserID.toString().equals(userId.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getSubscribers(UUID authorID){
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT user_id FROM user_author where author_id=?")) {
            preparedStatement.setObject(1, authorID);
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(UserDAO.getUserByID((UUID) resultSet.getObject("user_id")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getAuthorsByUser(UUID userID){
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT author_id FROM user_author where user_id=?")) {
            preparedStatement.setObject(1, userID);
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(UserDAO.getUserByID((UUID) resultSet.getObject("author_id")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void subscribe(UUID authorID, UUID userID){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().
                prepareStatement("INSERT INTO user_author (author_id, user_id) values (?, ?)")){
            preparedStatement.setObject(1, authorID);
            preparedStatement.setObject(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void unSubscribe(UUID authorID, UUID userID){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM user_author " +
                "WHERE (author_id, user_id) = (?, ?)")){
            preparedStatement.setObject(1, authorID);
            preparedStatement.setObject(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unSubscribeAll (UUID userID){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM user_author " +
                "WHERE  user_id = ?")){
            preparedStatement.setObject(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void unSubscribeAllMySubs (UUID authorID){
        try(PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM user_author " +
                "WHERE  author_id = ?")){
            preparedStatement.setObject(1, authorID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


