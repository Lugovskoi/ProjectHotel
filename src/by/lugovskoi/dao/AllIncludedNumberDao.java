package by.lugovskoi.dao;

import by.lugovskoi.entity.AllIncludedNumber;
import by.lugovskoi.entity.RoomClass;

import java.sql.*;

public class AllIncludedNumberDao implements Dao <AllIncludedNumber> {

    private static AllIncludedNumberDao INSTANCE;

    private AllIncludedNumberDao(){}

    public static AllIncludedNumberDao getInstance() {
        if (INSTANCE == null) {
            synchronized (AllIncludedNumberDao.class){
                if(INSTANCE == null) {
                    INSTANCE = new AllIncludedNumberDao();
                }
            }
        }
        return INSTANCE;
    }

    private static final String SAVE_QUERY =
            "INSERT INTO all_included_number (rooms_count, room_class, booked, allow_smoke, allow_pet) " +
                    "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_QUERY =
            "SELECT * FROM all_included_number " +
                    "WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM all_included_number WHERE id = ?;";

    @Override
    public AllIncludedNumber save(AllIncludedNumber number) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, number.getRoomsCount());
            statement.setString(2, number.getRoomClass().name());
            statement.setBoolean(3, number.isBooked());
            statement.setBoolean(4, number.isAllowSmoke());
            statement.setBoolean(5, number.isAllowPets());
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
    public AllIncludedNumber find(int id) {
        AllIncludedNumber number = null;
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                number = new AllIncludedNumber(
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
