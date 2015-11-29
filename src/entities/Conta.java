package entities;

public class Conta {
	private long id;
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

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
		setId();
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
		setId();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
		setId();
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public long getId() {
		return id;
	}
	
	public boolean equals(Conta conta) {
		return this.id == conta.id;
	}

	private void setId() {
		final int primo = 31;
		id = 1;
		id = primo * id + banco.hashCode();
		id = primo * id + agencia.hashCode();
		id = primo * id + numero.hashCode();
	}

	@Override
	public String toString() {
		return "Conta [" + id + ", " + banco + ", " + agencia + ", " + numero + "]";
	}
}
