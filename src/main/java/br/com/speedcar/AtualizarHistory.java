package br.com.speedcar;

import br.com.speedcar.dao.HistoryDAO;
import br.com.speedcar.infra.ConnectionFactory;
import br.com.speedcar.model.History;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AtualizarHistory {

	public static void main(String[] args) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();

		HistoryDAO dao = new HistoryDAO(connection);
		Optional<History> optional = dao.findById(10L);

		optional.ifPresent(it -> {
			it.setOccurrence("Ocorrência atualizada");
			dao.update(it);
		});

		connection.close();
	}
}
