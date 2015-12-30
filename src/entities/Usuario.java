package entities;

/**
 * @author Emanuel
 *
 */
public class Usuario {
	private String nome;
	private String senha;
	private String email;

	public Usuario(String nome, String senha, String email) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	public Usuario(String nome, String senha) {
		this(nome, senha, "");
	}

	public Usuario() {
		this("", "", "");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static boolean validateNome(String nome) {
		return nome.matches("^[a-zA-Z0-9_-]+$");
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static boolean validateSenha(String senha) {
		return senha.matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public static boolean validateEmail(String email) {
		return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
	}
}
