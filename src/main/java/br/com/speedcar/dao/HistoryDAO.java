package br.com.speedcar.dao;

import br.com.speedcar.model.Categoria;
import br.com.speedcar.model.History;
import br.com.speedcar.model.SpeedCar;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryDAO implements IHistoryDAO {

    private final Connection connection;

    public HistoryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public History save(History history) {
        try {
            String sql = "INSERT INTO History (customerFullName, phoneNumber, emailAddress, occurrence) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, history.getCustomerFullName().isEmpty() ? "Unnamed" : history.getCustomerFullName());
            preparedStatement.setString(2, history.getPhoneNumber().isEmpty() ? "(00)99999999": history.getPhoneNumber());
            preparedStatement.setString(3, history.getEmailAddress().isEmpty() ? "not informed": history.getEmailAddress());
            preparedStatement.setString(4, history.getOccurrence().isEmpty() ? "not informed" :  history.getOccurrence());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            history.setId(generatedId);

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return history;
    }

    @Override
    public History update(History history) {
        String sql = "UPDATE History SET customerFullName = ?, phoneNumber = ?, emailAddress = ?, occurrence = ? WHERE id = ?;";
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, history.getCustomerFullName());
        preparedStatement.setString(2, history.getPhoneNumber());
        preparedStatement.setString(3, history.getEmailAddress());
        preparedStatement.setString(4, history.getOccurrence());
        preparedStatement.setLong(5, history.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
        return history;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<History> findAll(boolean showInactive) {
        return null;
    }

    @Override
    public Optional<History> findById(Long id) {
        String sql = "SELECT id, customerFullName, phoneNumber, emailAddress, occurrence FROM History WHERE id = ?";

        //List<History> histories = new ArrayList<>();

        History history = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String fullName = rs.getString("customerFullName");
                String phone = rs.getString("phoneNumber");
                String email = rs.getString("emailAddress");
                String ocurr = rs.getString("occurrence");

                history = new History(pKey, fullName, phone, email, ocurr);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.ofNullable(history);
    }

    @Override
    public List<History> findByCustomer(String customerFullName, boolean showInactive) {
        return null;
    }
}
