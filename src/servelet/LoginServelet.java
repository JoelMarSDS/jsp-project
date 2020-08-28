package servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLogin;

@WebServlet("/loginServelet")
public class LoginServelet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	DaoLogin daoLogin = new DaoLogin();
	
    public LoginServelet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
			if (!daoLogin.validadorConexao(login, senha)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
