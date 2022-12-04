package br.com.speedcar;

import br.com.speedcar.infra.ConnectionFactory;
import br.com.speedcar.model.SpeedCar;
import br.com.speedcar.dao.SpeedCarDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AtualizarSpeedCar {

	public static void main(String[] args) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();

		SpeedCarDAO dao = new SpeedCarDAO(connection);
		Optional<SpeedCar> optional = dao.findById(1L);

		optional.ifPresent(it -> {
			it.setDescricao("Descrição atualizada");
			it.setValor(46);
			dao.update(it);
		});

		connection.close();
	}
}
