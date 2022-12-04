package br.com.speedcar;

import br.com.speedcar.dao.SpeedCarDAO;
import br.com.speedcar.infra.ConnectionFactory;
import br.com.speedcar.model.SpeedCar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListarTodasSpeedCar {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();

		SpeedCarDAO dao = new SpeedCarDAO(connection);
		List<SpeedCar> speedCars = dao.findAll();

		for (SpeedCar speedCar : speedCars) {
			System.out.println("ID => " + speedCar.getId());
			System.out.println("Data => " + speedCar.getData());
			System.out.println("Descricao => " + speedCar.getDescricao());
			System.out.println("Valor => " + speedCar.getValor());
			System.out.println("Categoria => " + speedCar.getCategoria());
		}

		connection.close();
	}
}
