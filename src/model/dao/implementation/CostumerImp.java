package model.dao.implementation;

import db.DB;
import db.exception.DBException;
import model.dao.CostumerDAO;
import model.entites.Costumer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CostumerImp implements CostumerDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public CostumerImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Costumer costumer) {

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO costumer "
                    +"(FName, LName) "
                    +"VALUES "
                    +"(?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, costumer.getfName());
            preparedStatement.setString(2, costumer.getlName());

            int result = preparedStatement.executeUpdate();

            if(result > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    costumer.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DBException("Unexpected ERROR! No rows affected");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    private Costumer createCostumer(ResultSet resultSet) throws SQLException {
        Costumer costumer = new Costumer();

        costumer.setId(resultSet.getInt("Id"));
        costumer.setfName(resultSet.getString("FName"));
        costumer.setlName(resultSet.getString("LName"));
        return costumer;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM costumer "
                    +"WHERE Id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Costumer costumer) {
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE costumer "
                    +"SET FName = ?, LName = ? "
                    +"WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, costumer.getfName());
            preparedStatement.setString(2, costumer.getlName());
            preparedStatement.setInt(3, costumer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Costumer findById(Integer id) {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM costumer "
                    +"WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createCostumer(resultSet);
            } else {
                throw new DBException("ERROR! Id Nonexistent");
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Costumer> findAll() {
        List<Costumer> costumers = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM costumer", Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Costumer costumer = createCostumer(resultSet);
                costumers.add(costumer);
            }

        return costumers;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
