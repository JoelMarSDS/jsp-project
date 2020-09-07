package servelet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeansJsp;
import servelet.acoes.UsuarioServelet;

@WebServlet("/usuarioCadastro")
public class UsuarioCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String id;
	private String login;
	private String senha;
	private String nome;
	private String telefone;

	private String acaoDoPost;
	private String acao;
	private String user;

	private BeansJsp beansJsp;

	private UsuarioServelet acoesServelet = new UsuarioServelet();

	public UsuarioCadastro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			acao = request.getParameter("acao");
			user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				deletar(request, response, user);
			} else if (acao.equalsIgnoreCase("editar")) {
				editar(request, response, user);
			} else if (acao.equalsIgnoreCase("listarCadastro")) {
				listarCadastro(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		acaoDoPost = request.getParameter("acao");

		if (acaoDoPost != null && acaoDoPost.equalsIgnoreCase("reset")) {
			try {
				listarCadastro(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			try {

				id = request.getParameter("id");
				login = request.getParameter("login");
				senha = request.getParameter("senha");
				nome = request.getParameter("nome");
				telefone = request.getParameter("telefone");

				beansJsp = new BeansJsp(!id.isEmpty() ? Long.parseLong(id) : 0, login, senha, nome,
						!telefone.isEmpty() ? Long.parseLong(telefone) : 0);

				try {
					/*
					if (beansJsp == null || beansJsp.getId() == 0) {
						request.setAttribute("msg", "Dados invalidos, peencha todos os campos obrigatórios!");
					} else {
					*/
					if (id == null || id.isEmpty() && !acoesServelet.validacao(login)) {
						request.setAttribute("msg", "Usuario existente!!!");
					}

					if (id == null || id.isEmpty() && acoesServelet.validacao(login)) {

						acoesServelet.salvar(beansJsp);

					} else if (id != null && !id.isEmpty()) {
						if (!acoesServelet.validacao(login)) {
							request.setAttribute("msg", "Usuario existente!!!");
						} else {
							acoesServelet.atualizar(beansJsp);
						}
					}
					//}
					listarCadastro(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	private void deletar(HttpServletRequest request, HttpServletResponse response, String user)
			throws SQLException, ServletException, IOException {
		acoesServelet.deletar(user);
		listarCadastro(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response, String user)
			throws ServletException, IOException {
		beansJsp = acoesServelet.consultar(user);
		RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
		request.setAttribute("user", beansJsp);
		view.forward(request, response);
	}

	private void listarCadastro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
		request.setAttribute("usuarios", acoesServelet.listar());
		view.forward(request, response);
	}

}
