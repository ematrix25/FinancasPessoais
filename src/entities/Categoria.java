package entities;

/**
 * @author Danilo
 */

public class Categoria {
	private String nome;
	
	public Categoria(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static boolean validateNome(String nome) {
		return nome.matches("^[a-zA-Z]+$");
	}
}
