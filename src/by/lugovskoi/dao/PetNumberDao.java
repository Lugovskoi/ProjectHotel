package by.lugovskoi.dao;

import by.lugovskoi.entity.*;

import java.sql.*;

public class PetNumberDao implements Dao <PetNumber> {

    private static PetNumberDao INSTANCE;

    private PetNumberDao(){}

    public static PetNumberDao getInstance() {
        if (INSTANCE == null) {
            synchronized (PetNumberDao.class){
                if(INSTANCE == null) {
                    INSTANCE = new PetNumberDao();
                }
            }
        }
        return INSTANCE;
    }

    private static final String SAVE_QUERY =
            "INSERT INTO pet_number (rooms_count, room_class, booked, allow_pet) " +
                    "VALUES (?, ?, ?, ?);";

    private static final String FIND_QUERY =
            "SELECT * FROM pet_number " +
                    "WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM pet_number WHERE id = ?;";

    @Override
    public PetNumber save(PetNumber number) {
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, number.getRoomsCount());
            statement.setString(2, number.getRoomClass().name());
            statement.setBoolean(3, number.isBooked());
            statement.setBoolean(4, number.isAllowPets());
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
    public PetNumber find(int id) {
        PetNumber number = null;
        try(Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                number = new PetNumber(
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
