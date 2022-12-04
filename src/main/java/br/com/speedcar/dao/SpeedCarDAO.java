package br.com.speedcar.dao;

import br.com.speedcar.model.Categoria;
import br.com.speedcar.model.SpeedCar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpeedCarDAO implements ISpeedCarDAO {

    private final Connection connection;

    public SpeedCarDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SpeedCar save(SpeedCar speedCar) {

        try {
            String sql = "INSERT INTO SpeedCars (descricao, valor, data, categoria) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, speedCar.getDescricao());
            preparedStatement.setDouble(2, speedCar.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(speedCar.getData()));
            preparedStatement.setString(4, speedCar.getCategoria().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            speedCar.setId(generatedId);

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speedCar;
    }

    @Override
    public SpeedCar update(SpeedCar speedCar) {
        try {
            String sql = "UPDATE SpeedCars SET descricao = ?, valor = ?, data = ?, categoria = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, speedCar.getDescricao());
            preparedStatement.setDouble(2, speedCar.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(speedCar.getData()));
            preparedStatement.setString(4, speedCar.getCategoria().toString());
            preparedStatement.setLong(5, speedCar.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speedCar;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM SpeedCars WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<SpeedCar> findAll() {
        String sql = "SELECT id, descricao, data, valor, categoria FROM SpeedCars";

        List<SpeedCar> speedCars = new ArrayList<>();

        SpeedCar speedCar = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                speedCar = new SpeedCar(id, descricao, data, valor, categoria);
                speedCar.add(speedCar);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return (List<SpeedCar>) speedCar;
    }

    @Override
    public Optional<SpeedCar> findById(Long id) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM SpeedCar WHERE id = ?";

        SpeedCar speedCar = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                speedCar = new SpeedCar(pKey, descricao, data, valor, categoria);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(speedCar);
    }

    @Override
    public List<SpeedCar> findByCategoria(Categoria categoria) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM SpeedCars WHERE categoria = ?";

        List<SpeedCar> speedCars = new ArrayList<>();

        SpeedCar speedCar = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("Id");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria cat = Categoria.valueOf(rs.getString("categoria"));

                speedCar = new SpeedCar(id, descricao, data, valor, cat);
                speedCar.add(speedCar);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return (List<SpeedCar>) speedCar;
    }
}
