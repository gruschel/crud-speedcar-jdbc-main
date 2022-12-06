package br.com.speedcar.model;

import br.com.speedcar.dao.HistoryDAO;
import br.com.speedcar.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class DesabilitarHistory {

	public static void main(String[] args) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();

		HistoryDAO dao = new HistoryDAO(connection);
		dao.delete(10L);
		connection.close();


	}
}
