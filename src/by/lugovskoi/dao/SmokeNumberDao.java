package by.lugovskoi.dao;

import by.lugovskoi.entity.*;

import java.sql.*;

public class SmokeNumberDao implements Dao <SmokeNumber> {

    private static SmokeNumberDao INSTANCE;

    private SmokeNumberDao(){}

    public static SmokeNumberDao getInstance() {
        if (INSTANCE == null) {
            synchronized (SmokeNumberDao.class){
                if(INSTANCE == null) {
                    INSTANCE = new SmokeNumberDao();
                }
            }
        }
        return INSTANCE;
    }

    private static final String SAVE_QUERY =
            "INSERT INTO smoke_number (rooms_count, room_class, booked, allow_smoke) " +
                    "VALUES (?, ?, ?, ?);";

    private static final String FIND_QUERY =
            "SELECT * FROM smoke_number " +
                    "WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM smoke_number WHERE id = ?;";

    @Override
    public SmokeNumber save(SmokeNumber number) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, number.getRoomsCount());
            statement.setString(2, number.getRoomClass().name());
            statement.setBoolean(3, number.isBooked());
            statement.setBoolean(4, number.isAllowSmoke());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                number.setId(generatedKeys.getInt(1));
            }
            return number;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SmokeNumber find(int id) {
        SmokeNumber number = null;
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                number = new SmokeNumber(
                        resultSet.getInt("id"),
                        resultSet.getInt("room_count"),
                        RoomClass.valueOf(resultSet.getString("room_class")),
                        resultSet.getBoolean("booked"));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
