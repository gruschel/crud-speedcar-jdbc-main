package br.com.speedcar.dao;

import br.com.speedcar.infra.ConnectionFactory;
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
            String sql = "INSERT INTO History (customerFullName, phoneNumber, emailAddress, occurrence, vehicle, active) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, history.getCustomerFullName().isEmpty() ? "Unnamed" : history.getCustomerFullName());
            preparedStatement.setString(2, history.getPhoneNumber().isEmpty() ? "(00)99999999": history.getPhoneNumber());
            preparedStatement.setString(3, history.getEmailAddress().isEmpty() ? "not informed": history.getEmailAddress());
            preparedStatement.setString(4, history.getOccurrence().isEmpty() ? "not informed" :  history.getOccurrence());
            preparedStatement.setString(5, history.getVehicle().isEmpty() ? "not informed" :  history.getVehicle());
            preparedStatement.setBoolean(6, history.isActive());

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
        String sql = "UPDATE History SET customerFullName = ?, phoneNumber = ?, emailAddress = ?, occurrence = ?, vehicle = ?, active = ? WHERE id = ?;";
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, history.getCustomerFullName());
        preparedStatement.setString(2, history.getPhoneNumber());
        preparedStatement.setString(3, history.getEmailAddress());
        preparedStatement.setString(4, history.getOccurrence());
        preparedStatement.setString(5, history.getVehicle());
        preparedStatement.setBoolean(6, history.isActive());
        preparedStatement.setLong(7, history.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
        return history;
    }

    @Override
    public void delete(Long id) {
        Optional<History> his = findById(id);
        his.ifPresent( it -> {
            it.setActive(false);
            update(it);
        });
    }

    @Override
    public List<History> findAll(boolean showInactive) {

        String sql = "SELECT id, customerFullName, phoneNumber, emailAddress, occurrence, vehicle FROM History" + (showInactive? "":" WHERE  active = true");

        List<History> histories = new ArrayList<>();

        History hist = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String fullName = rs.getString("customerFullName");
                String phone = rs.getString("phoneNumber");
                String email = rs.getString("emailAddress");
                String occurr = rs.getString("occurrence");
                String vh = rs.getString("vehicle");
                boolean act = rs.getBoolean( "active");

                hist = new History(id, fullName, phone, email, occurr, vh, act);
                histories.add(hist);
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return histories;
    }

    @Override
    public Optional<History> findById(Long id) {
        String sql = "SELECT id, customerFullName, phoneNumber, emailAddress, occurrence, vehicle, active FROM History WHERE id = ?";

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
                String vh = rs.getString("vehicle");
                Boolean act = rs.getBoolean("active");

                history = new History(pKey, fullName, phone, email, ocurr, vh,act);
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

    @Override
    public void InitTable(boolean forceInit) {

        String sql = "SELECT 1 from History";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String fullName = rs.getString("customerFullName");
                String phone = rs.getString("phoneNumber");
                String email = rs.getString("emailAddress");
                String ocurr = rs.getString("occurrence");
                //history = new History(pKey, fullName, phone, email, ocurr);
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
