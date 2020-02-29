package by.lugovskoi.dao;

import by.lugovskoi.entity.*;
import by.lugovskoi.entity.Number;

import java.sql.*;

public class RequestSmokeNumberDao implements Dao<Request> {
    private static RequestSmokeNumberDao INSTANCE;

    private RequestSmokeNumberDao(){}

    public static RequestSmokeNumberDao getInstance() {
        if (INSTANCE == null) {
            synchronized (RequestSmokeNumberDao.class){
                if(INSTANCE == null) {
                    INSTANCE = new RequestSmokeNumberDao();
                }
            }
        }
        return INSTANCE;
    }

    private static final String SAVE_QUERY =
            "INSERT INTO reqest (number_id, start_date, end_date, user_id) " +
                    "VALUES (?, ?, ?, ?);";

    private static final String FIND_QUERY =
            "SELECT * FROM request " +
                    "JOIN user ON request.user_id = user.id " +
                    "JOIN smoke_number ON request.number_id = smoke_number.id " +
                    "WHERE request.user_id = ?";

    private static final String DELETE_QUERY = "DELETE FROM request WHERE user_id = ?;";


    @Override
    public Request save(Request request) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY);
            statement.setInt(1, request.getNumber().getId());
            statement.setDate(2, request.getStartDate());
            statement.setDate(3, request.getStartDate());
            statement.setInt(4, request.getUser().getId());
            statement.execute();

            statement.close();
            return request;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Request find(int id) {
        Request request = null;
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                request = new Request(new AllIncludedNumber(resultSet.getInt("smoke_number.id"),
                        resultSet.getInt("smoke_number.rooms_count"),
                        RoomClass.valueOf(resultSet.getString("smoke_number.room_class")),
                        resultSet.getBoolean("smoke_number.booked")),

                        resultSet.getDate("request.start_date"),
                        resultSet.getDate("request.end_date"),

                        new User(resultSet.getInt("user.id"),
                                resultSet.getString("user.name"),
                                resultSet.getString("user.last_name"),
                                resultSet.getString("user.passport_id"),
                                Role.valueOf(resultSet.getString("user.role")),
                                resultSet.getInt("user.age")));


            }
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionManager.newConnection()) {
            connection.prepareStatement(DELETE_QUERY).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
