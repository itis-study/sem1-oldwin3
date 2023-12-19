package ru.itis.DAO;

import ru.itis.entities.main.Recipe;
import ru.itis.Services.ImageService;
import ru.itis.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipeDAO {
    public static void saveRecipe(String name, String description, byte[] image, String ingredients,
                                  String stages, UUID user_id) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("INSERT INTO recipes (name, rating, ratingcount, description, image, ingredients,stage, userId) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, 0.0);
            preparedStatement.setDouble(3, 0.0);
            preparedStatement.setString(4, description);
            preparedStatement.setBytes(5, image);
            preparedStatement.setString(6, ingredients);
            preparedStatement.setString(7, stages);
            preparedStatement.setObject(8, user_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getAllRecipe() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes")) {
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getBestRecipe() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes ORDER BY ratingcount DESC, rating DESC LIMIT 3;")) {
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getAllRecipeWithPages(Integer startIdx, Integer endIdx) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes " +
                        "LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, startIdx);
            preparedStatement.setInt(2, endIdx);
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getAllRecipeWithPagesSortByRating(Integer startIdx, Integer endIdx) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes ORDER BY rating DESC " +
                        "LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, startIdx);
            preparedStatement.setInt(2, endIdx);
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getAllRecipeOwnedByUsername(String name) {
        UUID uuid = UserDAO.getUserByName(name).getUser_id();
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes where userid = ?")) {
            preparedStatement.setObject(1, uuid);
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getAllRecipeOwnedByUsernameWithPages(String name, Integer startIdx, Integer endIdx) {
        UUID uuid = UserDAO.getUserByName(name).getUser_id();
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes " +
                        "WHERE userid = ? " +
                        "LIMIT ? OFFSET ?")) {
            preparedStatement.setObject(1, uuid);
            preparedStatement.setInt(2, startIdx);
            preparedStatement.setInt(3, endIdx);
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Recipe> getAllRecipeOwnedByUsernameWithPagesSortByRating(String name, Integer startIdx, Integer endIdx) {
        UUID uuid = UserDAO.getUserByName(name).getUser_id();
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes " +
                        "WHERE userid = ? ORDER BY rating DESC " +
                        "LIMIT ? OFFSET ?")) {
            preparedStatement.setObject(1, uuid);
            preparedStatement.setInt(2, startIdx);
            preparedStatement.setInt(3, endIdx);
            List<Recipe> recipes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
                recipes.add(recipe);
            }
            if (recipes == null) {
                recipes = null;
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Recipe getRecipeByName(String name) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM recipes where name = ?")) {
            preparedStatement.setString(1, name);
            Recipe recipe = new Recipe();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recipe.setRecipe_id((UUID) resultSet.getObject("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setRating(resultSet.getDouble("rating"));
                recipe.setRatingCount(resultSet.getDouble("ratingcount"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImage(resultSet.getBytes("image"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setUser_id((UUID) resultSet.getObject("userId"));
                recipe.setStages(resultSet.getString("stage"));
                ImageService imageService = new ImageService();
                recipe.setImageForSrc(imageService.getImage("recipes", resultSet.getString("name")));
            }
            if (recipe.getName() == null) {
                recipe = null;
            }
            return recipe;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateRecipe(String oldName, String NewName, String description, byte[] image, String ingredients, String stages) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE recipes SET name=?, description=?, image=?, ingredients=?, stage=? WHERE name=?")) {
            preparedStatement.setString(1, NewName);
            preparedStatement.setString(2, description);
            preparedStatement.setBytes(3, image);
            preparedStatement.setString(4, ingredients);
            preparedStatement.setString(5, stages);
            preparedStatement.setString(6, oldName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRatingRecipeByName(String name, Double rating, Double ratingCount) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE recipes SET rating=?, ratingcount=? WHERE name=?")) {
            preparedStatement.setDouble(1, rating);
            preparedStatement.setDouble(2, ratingCount);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeRecipe(String name) {
        UUID uuid = RecipeDAO.getRecipeByName(name).getRecipe_id();
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement("DELETE FROM voted WHERE recipeid = ?")) {
            statement.setObject(1, uuid);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM recipes WHERE name = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeAllRecipeByUserID(UUID uuid) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("DELETE FROM recipes WHERE userid = ?")) {
            preparedStatement.setObject(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Integer countRows() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("SELECT COUNT(*) FROM recipes");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static Integer countRowsByUser(UUID userid) {
        try (PreparedStatement preparedStatement =
                     JDBCUtil.getConnection().prepareStatement("SELECT COUNT(*) FROM recipes where userid=?")) {
            preparedStatement.setObject(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
