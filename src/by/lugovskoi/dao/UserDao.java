package by.lugovskoi.dao;

import by.lugovskoi.entity.Role;
import by.lugovskoi.entity.User;

import java.sql.*;

public class UserDao implements Dao <User> {

    private static UserDao INSTANCE;

    private UserDao(){}

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class){
                if(INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    private static final String SAVE_QUERY =
            "INSERT INTO user (name, last_name, passport_id, role, age) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_QUERY =
            "SELECT * FROM user " +
                    "WHERE user.id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM user WHERE id = ?;";

    @Override
    public User save(User user) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassportId());
            statement.setString(4, user.getRoleAsString());
            statement.setInt(5, user.getAge());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(int id) {
        User user = null;
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("passport_id"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getInt("age"));
            }
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
