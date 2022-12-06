package br.com.speedcar;

import br.com.speedcar.dao.SpeedCarDAO;
import br.com.speedcar.infra.ConnectionFactory;
import br.com.speedcar.model.Categoria;
import br.com.speedcar.model.SpeedCar;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class InitTables {
    public static void main(String[] args) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();

        SpeedCar speedCar =
                new SpeedCar(
                        "Alugar Carro",
                        LocalDate.of(2022, 11, 27),
                        95.0,
                        Categoria.A);

        SpeedCarDAO dao = new SpeedCarDAO(connection);
        dao.save(speedCar);

        connection.close();
    }
}
