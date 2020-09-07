package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Produto produto) {

		try {
			String sql = "INSERT INTO produto (nome, quantidade, valor) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
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

	public List<Produto> listar() {
		List<Produto> listar = new ArrayList<Produto>();
		try {
			String sql = "select * from produto";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setId(resultSet.getLong("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getDouble("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));

				listar.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void atualizar(Produto produto) {
		try {
			String sql = "UPDATE produto SET (nome, quantidade, valor) = (?, ?, ?) WHERE id = " + produto.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Produto consultar(String id) {
		String sql = "select * from produto p where id = '" + id + "'";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Produto produto = new Produto();

				produto.setId(resultSet.getLong("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getDouble("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));

				return produto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean validarAtualizacao(String nome) {
		
		try {
			String sql = "select count(1) as qtd from produto u where nome = '"+ nome +"'";
			
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
