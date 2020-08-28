package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	
	Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validadorConexao(String login, String senha) throws Exception {
		
		String sql = "SELECT * FROM usuario WHERE (login, senha) = ('"+ login + "', '"+ senha + "')";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (!resultSet.next()) {
			return false;
		} else {
			return true;
		}
	}
	
}
