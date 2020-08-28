package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeansJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeansJsp beansJsp) {

		try {
			String sql = "INSERT INTO usuario (login, senha, nome, telefone) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, beansJsp.getLogin());
			statement.setString(2, beansJsp.getSenha());
			statement.setString(3, beansJsp.getNome());
			statement.setLong(4, beansJsp.getTelefone());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void atualizar(BeansJsp beansJsp) {
		try {
			String sql = "UPDATE usuario SET (login, senha, nome, telefone) = (?, ?, ?, ?) WHERE id = " + beansJsp.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, beansJsp.getLogin());
			statement.setString(2, beansJsp.getSenha());
			statement.setString(3, beansJsp.getNome());
			statement.setLong(4, beansJsp.getTelefone());
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BeansJsp> listar() throws SQLException {
		
		List<BeansJsp> lista = new ArrayList<BeansJsp>();
		
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeansJsp beansJsp = new BeansJsp();
			
			beansJsp.setId(resultSet.getLong("id"));
			beansJsp.setLogin(resultSet.getString("login"));
			beansJsp.setNome(resultSet.getString("nome"));
			beansJsp.setTelefone(resultSet.getLong("telefone"));
			
			lista.add(beansJsp);
		}
		
		return lista;
	}

	public void deletar(String id) throws SQLException {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {			
			try {
			connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public BeansJsp consultar(String id) {

		try {
		String sql = "select * from usuario u where id = '"+ id +"'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
			BeansJsp beansJsp = new BeansJsp();
			
			beansJsp.setId(resultSet.getLong("id"));
			beansJsp.setLogin(resultSet.getString("login"));
			beansJsp.setNome(resultSet.getString("nome"));
			
			return beansJsp;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public boolean validarAtualizacao(String login) {
		
		try {
			String sql = "select count(1) as qtd from usuario u where login = '"+ login +"'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				return resultSet.getInt("qtd") <= 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
	public boolean validarSenha(String senha) {
		
		try {
			String sql = "select count(1) as qtd from usuario u where senha = '"+ senha +"'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				return resultSet.getInt("qtd") <= 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}
}
