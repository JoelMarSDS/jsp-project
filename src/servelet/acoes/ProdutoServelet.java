package servelet.acoes;

import java.util.List;

import beans.Produto;
import dao.DaoProduto;

public class ProdutoServelet {

	DaoProduto daoProduto = new DaoProduto();

	public void salvar(Produto produto) {
		daoProduto.salvar(produto);
	}
	
	public List<Produto> listar() {
		return daoProduto.listar();
	}
	
	public Produto consultar(String id) {
		return daoProduto.consultar(id);
	}
	
	public void delete(String user) {
		daoProduto.delete(user);
	}
	
	public void atualizar(Produto produto) {
		daoProduto.atualizar(produto);
	}
	
	public boolean validacao(String nome) {
		if (daoProduto.validarAtualizacao(nome)) {
			return true;
		}
		return false;
	}
}
