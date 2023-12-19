package ru.itis.Services;

import ru.itis.DAO.UserDAO;
import ru.itis.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RatingService {

    public static Double recalculateTheRating(Double oldArithmeticMean, Double Count, Double value) {
        System.out.println(oldArithmeticMean);
        System.out.println(Count);
        System.out.println(value);
        return (oldArithmeticMean * Count + value) / (Count + 1.0);
    }

    public static Double recalculateTheExistingRating
            (Double oldArithmeticMean, Double Count, Double value, Double oldValue) {
        System.out.println(oldArithmeticMean);
        System.out.println(Count);
        System.out.println(value);
        System.out.println(oldValue);
        return (oldArithmeticMean * Count - oldValue + value) / (Count);
    }

    public static Double getOldRating(UUID userID, UUID recipeID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT rating FROM voted WHERE (userId, recipeID )=(?, ?)")) {
            preparedStatement.setObject(1, userID);
            preparedStatement.setObject(2, recipeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Double rating = 0.0;
            while (resultSet.next()) {
                rating = (Double) resultSet.getObject("rating");
            }
            return rating;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer countRows(UUID recipeID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("SELECT COUNT(*) FROM " +
                "voted where recipeID = ?")) {
            preparedStatement.setObject(1, recipeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static Double getSumRatingByRecipe(UUID recipeID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT rating FROM voted WHERE  recipeID = ?")) {
            preparedStatement.setObject(1, recipeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Double rating = 0.0;
            while (resultSet.next()) {
                rating += (Double) resultSet.getObject("rating");
            }
            return rating;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void voteRecipe(UUID userId, UUID recipeID, Double rating, Double recipeRaitng) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("INSERT INTO voted (userid, recipeid, rating, reciperating) " +
                        "values (?, ?, ?, ?)")) {
            preparedStatement.setObject(1, userId);
            preparedStatement.setObject(2, recipeID);
            preparedStatement.setDouble(3, rating);
            preparedStatement.setDouble(4, recipeRaitng);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateRatingVote(UUID userId, UUID recipeID, Double rating, Double recipeRaitng) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE voted SET rating=?, reciperating=? WHERE (userId, recipeID )=(?, ?)")) {
            preparedStatement.setDouble(1, rating);
            preparedStatement.setDouble(2, recipeRaitng);
            preparedStatement.setObject(3, userId);
            preparedStatement.setObject(4, recipeID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Boolean isVotedRecipe(UUID userId, UUID recipeID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT recipeID FROM voted where (userid, recipeid) = (?, ?)")) {
            preparedStatement.setObject(1, userId);
            preparedStatement.setObject(2, recipeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            UUID tableRecipeID = null;


            while (resultSet.next()) {
                tableRecipeID = (UUID) resultSet.getObject("recipeID");
            }
            if (tableRecipeID == null) {
                return false;
            }
            return tableRecipeID.toString().equals(recipeID.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAllVotes(UUID usesID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM voted WHERE userid = ?")) {
            preparedStatement.setObject(1, usesID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

