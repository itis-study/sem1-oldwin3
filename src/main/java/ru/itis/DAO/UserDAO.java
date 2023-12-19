package ru.itis.DAO;

import ru.itis.Services.ImageService;
import ru.itis.entities.main.Community;
import ru.itis.entities.main.User;
import ru.itis.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    public static void saveUser(String name, String password, String email) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("INSERT INTO users (name, rating, email, password, role, recipenumber, ratingCount) " +
                        "values (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, 0.0);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, "regularUser");
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserByName(String name) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM users WHERE name = ?")) {
            preparedStatement.setString(1, name);
            User user = new User();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUser_id((UUID) resultSet.getObject("id"));
                user.setName(resultSet.getString("name"));
                user.setRating(resultSet.getDouble("rating"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setInformation(resultSet.getString("information"));
                user.setCommunity_id((UUID) resultSet.getObject("communityId"));
                user.setImage(resultSet.getBytes("image"));
                user.setRecipeNumber(resultSet.getInt("recipeNumber"));
                ImageService imageService = new ImageService();
                user.setImageForSrc(imageService.getImage("users", resultSet.getString("name")));
                user.setRatingCount(resultSet.getInt("ratingCount"));
            }
            if (user.getName() == null) {
                user = null;
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserByEmail(String email) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM users WHERE email = ?")) {
            preparedStatement.setString(1, email);
            User user = new User();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUser_id((UUID) resultSet.getObject("id"));
                user.setName(resultSet.getString("name"));
                user.setRating(resultSet.getDouble("rating"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setInformation(resultSet.getString("information"));
                user.setCommunity_id((UUID) resultSet.getObject("communityId"));
                user.setImage(resultSet.getBytes("image"));
                user.setRecipeNumber(resultSet.getInt("recipeNumber"));
                ImageService imageService = new ImageService();
                user.setImageForSrc(imageService.getImage("users", resultSet.getString("name")));
                user.setRatingCount(resultSet.getInt("ratingCount"));
            }
            if (user.getEmail() == null) {
                user = null;
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserByID(UUID id) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM users WHERE id = ?")) {
            preparedStatement.setObject(1, id);
            User user = new User();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUser_id((UUID) resultSet.getObject("id"));
                user.setName(resultSet.getString("name"));
                user.setRating(resultSet.getDouble("rating"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setInformation(resultSet.getString("information"));
                user.setCommunity_id((UUID) resultSet.getObject("communityId"));
                user.setImage(resultSet.getBytes("image"));
                user.setRecipeNumber(resultSet.getInt("recipeNumber"));
                ImageService imageService = new ImageService();
                user.setImageForSrc(imageService.getImage("users", resultSet.getString("name")));
                user.setRatingCount(resultSet.getInt("ratingCount"));
            }
            if (user.getEmail() == null) {
                user = null;
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getAllUsers() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM users")) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUser_id((UUID) resultSet.getObject("id"));
                user.setName(resultSet.getString("name"));
                user.setRating(resultSet.getDouble("rating"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setInformation(resultSet.getString("information"));
                user.setCommunity_id((UUID) resultSet.getObject("communityId"));
                user.setImage(resultSet.getBytes("image"));
                user.setRecipeNumber(resultSet.getInt("recipeNumber"));
                ImageService imageService = new ImageService();
                user.setImageForSrc(imageService.getImage("users", resultSet.getString("name")));
                user.setRatingCount(resultSet.getInt("ratingCount"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getAllAuthors() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM users where recipenumber != '0'")) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUser_id((UUID) resultSet.getObject("id"));
                user.setName(resultSet.getString("name"));
                user.setRating(resultSet.getDouble("rating"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setInformation(resultSet.getString("information"));
                user.setCommunity_id((UUID) resultSet.getObject("communityId"));
                user.setImage(resultSet.getBytes("image"));
                user.setRecipeNumber(resultSet.getInt("recipeNumber"));
                ImageService imageService = new ImageService();
                user.setImageForSrc(imageService.getImage("users", resultSet.getString("name")));
                user.setRatingCount(resultSet.getInt("ratingCount"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUser(String oldName, String NewName, String email, byte[] image, String password,
                                  String information) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE users SET name=?, email=?, image=?, password=?, information=? WHERE name=?")) {
            JDBCUtil.getConnection().setAutoCommit(false);
            preparedStatement.setString(1, NewName);
            preparedStatement.setString(2, email);
            preparedStatement.setBytes(3, image);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, information);
            preparedStatement.setString(6, oldName);
            preparedStatement.executeUpdate();
            JDBCUtil.getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRatingUserByID(UUID uuid, Double rating, Integer ratingCount) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE users SET rating=?, ratingcount=? WHERE id=?")) {
            preparedStatement.setDouble(1, rating);
            preparedStatement.setInt(2, ratingCount);
            preparedStatement.setObject(3, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRecipeNumberByName(String name, Integer recipeNumber) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE users SET recipenumber=? WHERE name=?")) {
            preparedStatement.setInt(1, recipeNumber);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCommunityIDByName(String name, UUID communityID, boolean isJoin,Integer usersNumber) {
        if(!isJoin){
            try(PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM " +
                    "user_community " + "WHERE (user_id, community_id) = (?, ?)")) {
                preparedStatement.setObject(1, getUserByName(name).getUser_id());
                preparedStatement.setObject(2, communityID);
                preparedStatement.executeUpdate();
                try(PreparedStatement statement = JDBCUtil.getConnection().prepareStatement("UPDATE communities SET " +
                        "usersnumber=?  WHERE (id) = ?")) {
                    statement.setInt(1, usersNumber-1);
                    statement.setObject(2, communityID);
                    statement.executeUpdate();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try(PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("INSERT INTO user_community " +
                    "(user_id, community_id) values (?, ?)")) {
                preparedStatement.setObject(1, getUserByName(name).getUser_id());
                preparedStatement.setObject(2, communityID);
                preparedStatement.executeUpdate();
                try(PreparedStatement statement = JDBCUtil.getConnection().prepareStatement("UPDATE communities SET " +
                        "usersnumber=?  WHERE (id) = ?")) {
                    statement.setInt(1, usersNumber+1);
                    statement.setObject(2, communityID);
                    statement.executeUpdate();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void deleteUser(String name) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM users WHERE name = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
