package beans;

public class BeansJsp {

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private Long telefone;

	public BeansJsp() {}

	public BeansJsp(Long id, String login, String senha, String nome, Long telefone) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
}
