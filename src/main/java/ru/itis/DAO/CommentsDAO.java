package ru.itis.DAO;

import ru.itis.entities.main.Comment;
import ru.itis.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentsDAO {
    public static void saveComment(UUID userID, UUID recipeID, String text) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("INSERT INTO comments (userid, recipeid, text, creatorname) " +
                        "values (?, ?, ?, ?)")) {
            preparedStatement.setObject(1, userID);
            preparedStatement.setObject(2, recipeID);
            preparedStatement.setString(3, text);
            preparedStatement.setString(4, UserDAO.getUserByID(userID).getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Comment> getAllCommentsOwnedByRecipe(String name) {
        UUID recipeID = RecipeDAO.getRecipeByName(name).getRecipe_id();
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM comments where recipeid = ?")) {
            preparedStatement.setObject(1, recipeID);
            List<Comment> comments = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setComment_id((UUID) resultSet.getObject("id"));
                comment.setUser_id((UUID) resultSet.getObject("userid"));
                comment.setRecipe_id((UUID) resultSet.getObject("recipeID"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatorName(resultSet.getString("creatorname"));
                comments.add(comment);
            }
            if (comments.isEmpty()) {
                comments = null;
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Comment> getAllComments() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM comments")) {
            List<Comment> comments = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setComment_id((UUID) resultSet.getObject("id"));
                comment.setUser_id((UUID) resultSet.getObject("userid"));
                comment.setRecipe_id((UUID) resultSet.getObject("recipeID"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatorName(resultSet.getString("creatorname"));
                comments.add(comment);
            }
            if (comments.isEmpty()) {
                comments = null;
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Comment> getAllCommentsOwnedByRecipeByUser(String recipeName, String userName) {
        UUID recipeID = RecipeDAO.getRecipeByName(recipeName).getRecipe_id();
        UUID userID = UserDAO.getUserByName(userName).getUser_id();
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM comments where (recipeid, userid) = (?, ?)")) {
            preparedStatement.setObject(1, recipeID);
            preparedStatement.setObject(2, userID);
            List<Comment> comments = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setComment_id((UUID) resultSet.getObject("id"));
                comment.setUser_id((UUID) resultSet.getObject("userid"));
                comment.setRecipe_id((UUID) resultSet.getObject("recipeID"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatorName(resultSet.getString("creatorname"));
                comments.add(comment);
            }
            if (comments.isEmpty()) {
                comments = null;
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateComment(UUID userID, UUID recipeID, String text) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE comments SET text=?, creatorname=?  WHERE (userid, recipeid) = (?, ?)")) {
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, UserDAO.getUserByID(userID).getName());
            preparedStatement.setObject(3, userID);
            preparedStatement.setObject(4, recipeID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeComment(UUID commentID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("DELETE FROM comments WHERE id = ?")) {
            preparedStatement.setObject(1, commentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
