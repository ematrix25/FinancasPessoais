package entities;

public class Conta {
	private int id;
	private String banco;
	private String agencia;
	private String numero;
	
	public Conta(String banco, String agencia, String numero) {
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		setId();
	}	

	public Conta(int id, String banco, String agencia, String numero) {
		this(banco, agencia, numero);
		this.id = id;
	}



	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
		setId();
	}

	/**
	 * @return the agencia
	 */
	public String getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia the agencia to set
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
		setId();
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param conta the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
		setId();
	}
	
	public int getId() {
		return id;
	}
	
	private void setId() {
		id = agencia.hashCode() + banco.hashCode() + numero.hashCode();
	}

}
