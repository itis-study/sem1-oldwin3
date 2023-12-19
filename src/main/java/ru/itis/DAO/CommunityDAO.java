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

public class CommunityDAO {
    public static void saveCommunity(String name, String description, byte[] image, UUID creatorID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("INSERT INTO communities (name, description, image, creatoruserid, usersnumber) " +
                        "values (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBytes(3, image);
            preparedStatement.setObject(4, creatorID);
            preparedStatement.setInt(5, 1);
            preparedStatement.executeUpdate();
            try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement("INSERT INTO user_community " +
                    "(user_id, community_id) values (?, ?)")) {
                statement.setObject(1, creatorID);
                statement.setObject(2, getCommunityByName(name).getCommunity_id());
                statement.executeUpdate();
                try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
                        "UPDATE users SET communityid=? WHERE name=?")) {
                    ps.setObject(1, getCommunityByName(name).getCommunity_id());
                    ps.setString(2, UserDAO.getUserByID(creatorID).getName());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Community getCommunityByName(String name) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().
                prepareStatement("SELECT * FROM communities WHERE name=?")) {
            preparedStatement.setString(1, name);
            Community community = new Community();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                community.setCommunity_id((UUID) resultSet.getObject("id"));
                community.setName(resultSet.getString("name"));
                community.setDescription(resultSet.getString("description"));
                community.setImage(resultSet.getBytes("image"));
                community.setUsersNumber(resultSet.getInt("usersnumber"));
            }
            if (community.getName() == null) {
                community = null;
            }
            return community;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Community getCommunityByUUID(UUID uuid) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().
                prepareStatement("SELECT * FROM communities WHERE id=?")) {
            preparedStatement.setObject(1, uuid);
            Community community = new Community();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                community.setCommunity_id((UUID) resultSet.getObject("id"));
                community.setName(resultSet.getString("name"));
                community.setDescription(resultSet.getString("description"));
                community.setImage(resultSet.getBytes("image"));
                community.setUsersNumber(resultSet.getInt("usersnumber"));
            }
            if (community.getName() == null) {
                community = null;
            }
            return community;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Community getCommunityByUserID(UUID id) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().
                prepareStatement("SELECT community_id FROM user_community WHERE user_id=?")) {
            preparedStatement.setObject(1, id);
            Community community = new Community();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                community = getCommunityByUUID((UUID) resultSet.getObject("community_id"));
            }
            if (community.getName() == null) {
                community = null;
            }
            return community;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Community> getAllCommunity() {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT * FROM communities")) {
            List<Community> communityList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Community community = new Community();
                community.setCommunity_id((UUID) resultSet.getObject("id"));
                community.setName(resultSet.getString("name"));
                community.setDescription(resultSet.getString("description"));
                community.setImage(resultSet.getBytes("image"));
                ImageService imageService = new ImageService();
                community.setImageForSrc(imageService.getImage("communities", resultSet.getString("name")));
                community.setUsersNumber(resultSet.getInt("usersnumber"));
                communityList.add(community);
            }
            if (communityList.isEmpty()) {
                communityList = null;
            }
            return communityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Community> getAllCommunityByUserIn(UUID userID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT c.* FROM communities c JOIN user_community uc ON c.id = uc.community_id WHERE " +
                        "uc.user_id = ?")) {
            preparedStatement.setObject(1, userID);
            List<Community> communityList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Community community = new Community();
                community.setCommunity_id((UUID) resultSet.getObject("id"));
                community.setName(resultSet.getString("name"));
                community.setDescription(resultSet.getString("description"));
                community.setImage(resultSet.getBytes("image"));
                ImageService imageService = new ImageService();
                community.setImageForSrc(imageService.getImage("communities", resultSet.getString("name")));
                community.setUsersNumber(resultSet.getInt("usersnumber"));
                communityList.add(community);
            }
            if (communityList.isEmpty()) {
                communityList = null;
            }
            return communityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getAllUserCommunity(UUID communityID) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT user_id FROM user_community where community_id=?")) {
            preparedStatement.setObject(1, communityID);
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

    public static List<Community> getAllCommunityUser(UUID userId) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                ("SELECT community_id FROM user_community where user_id=?")) {
            preparedStatement.setObject(1, userId);
            List<Community> communities = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                communities.add(CommunityDAO.getCommunityByUUID((UUID) resultSet.getObject("community_id")));
            }
            return communities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCommunity(String oldName, String newName, String description, byte[] image) {
        try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(
                "UPDATE communities SET name=?, description=? , image=?  WHERE (name) = (?)")) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, description);
            preparedStatement.setBytes(3, image);
            preparedStatement.setObject(4, oldName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeCommunity(UUID CommunityID) {
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement
                ("UPDATE users SET communityid=? WHERE (CommunityID) =?")) {
            statement.setObject(1, null);
            statement.setObject(2, CommunityID);
            statement.executeUpdate();
            try (PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement
                    ("DELETE FROM communities WHERE id = ?")) {
                preparedStatement.setObject(1, CommunityID);
                preparedStatement.executeUpdate();
                try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement
                        ("DELETE FROM user_community WHERE community_id = ?")) {
                    ps.setObject(1, CommunityID);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
