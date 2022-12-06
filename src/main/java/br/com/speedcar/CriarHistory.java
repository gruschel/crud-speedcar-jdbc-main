package br.com.speedcar;

import br.com.speedcar.dao.HistoryDAO;
import br.com.speedcar.infra.ConnectionFactory;
import br.com.speedcar.model.History;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class CriarHistory {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();

		History history =
				new History(
						"Edson Arantes do Nascimento",
						"(11)91234567",
						"filmedopele@netflix.com",
						"Me chamo Pelé e preciso do Gol mais bonito que vocês oferecem",
						"Golzinho amarelo",
						true);

		HistoryDAO dao = new HistoryDAO(connection);
		dao.save(history);

		connection.close();
	}
}
