package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/javaavancadojsp";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	private SingleConnection() {
		conectar();
	}
	
	private static void conectar() {

		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conexão foi realizada!!!");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro de conexão no banco de dados!!!");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
