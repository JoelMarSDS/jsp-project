package servelet.acoes;

import java.sql.SQLException;
import java.util.List;

import beans.BeansJsp;
import dao.DaoUsuario;

public class UsuarioServelet {

	private DaoUsuario daoUsuario = new DaoUsuario();

	public void deletar(String user) throws SQLException {
		daoUsuario.deletar(user);
	}

	public BeansJsp consultar(String user) {
		return daoUsuario.consultar(user);
	}

	public List<BeansJsp> listar() throws SQLException {
		return daoUsuario.listar();
	}

	public void salvar(BeansJsp beansJsp) {
		daoUsuario.salvar(beansJsp);
	}

	public void atualizar(BeansJsp beansJsp) {
		daoUsuario.atualizar(beansJsp);
	}

	public boolean validacao(String login) {
		if (daoUsuario.validarAtualizacao(login)) {
			return true;
		}
		return false;
	}
}
