package entities;

public class Conta {
	private int id;
	private String banco;
	private String agencia;
	private String numero;
	private float saldo;
	
	public Conta(int id, String banco, String agencia, String numero, float saldo) {
		this(banco, agencia, numero, saldo);
		this.id = id;
	}
	
	public Conta(String banco, String agencia, String numero, float saldo) {
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
		setId();
	}
	
	public Conta() {
		this("", "", "", 0);
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
	
	/**
	 * @return the saldo
	 */
	public float getSaldo() {
		return saldo;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	private void setId() {
		this.id = agencia.hashCode() + banco.hashCode() + numero.hashCode();
	}

	@Override
	public String toString() {
		return "Conta [" + id + ", " + banco + ", " + agencia + ", " + numero + "]";
	}

}
