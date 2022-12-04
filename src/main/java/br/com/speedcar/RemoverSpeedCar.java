package br.com.speedcar;

import br.com.speedcar.dao.SpeedCarDAO;
import br.com.speedcar.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoverSpeedCar {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		SpeedCarDAO dao = new SpeedCarDAO(connection);
		dao.delete(1L);

		connection.close();
	}
}
