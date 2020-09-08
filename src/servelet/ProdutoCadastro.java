package servelet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Produto;
import servelet.acoes.ProdutoServelet;

@WebServlet("/produtoCadastro")
public class ProdutoCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String quantidade;
	private String valor;

	private String acaoDoPost;
	private String acao;
	private String user;

	private Produto produto;

	ProdutoServelet cadastro = new ProdutoServelet();

	public ProdutoCadastro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			acao = request.getParameter("acao");
			user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				delete(request, response, user);
			} else if (acao.equalsIgnoreCase("editar")) {
				editar(request, response, user);
			} else if (acao.equalsIgnoreCase("listarProduto")) {
				listarProduto(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		acaoDoPost = request.getParameter("acao");

		if (acaoDoPost != null && acaoDoPost.equalsIgnoreCase("reset")) {
			listarProduto(request, response);
		} else {

			try {

				id = request.getParameter("id");
				nome = request.getParameter("nome");
				quantidade = request.getParameter("quantidade");
				valor = request.getParameter("valor");

				produto = new Produto(!id.isEmpty() ? Long.parseLong(id) : 0, nome,
						!quantidade.isEmpty() ? Double.parseDouble(quantidade) : 0,
						!valor.isEmpty() ? Double.parseDouble(valor) : 0);

				if (nome == null|| nome.isEmpty()
					|| quantidade == null || quantidade.isEmpty()
					|| valor == null || valor.isEmpty()) {
					request.setAttribute("msg", "Preencha todos os campos obrigatórios");
				} else if (id == null || id.isEmpty()) {

					cadastro.salvar(produto);

				} else if (id != null && !id.isEmpty()) {
					
						cadastro.atualizar(produto);
				}
				// }
				listarProduto(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response, String user)
			throws SQLException, ServletException, IOException {
		cadastro.delete(user);
		listarProduto(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response, String user)
			throws ServletException, IOException {
		produto = cadastro.consultar(user);
		RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
		request.setAttribute("user", produto);
		view.forward(request, response);
	}

	private void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
		request.setAttribute("produto", cadastro.listar());
		view.forward(request, response);
	}

}
