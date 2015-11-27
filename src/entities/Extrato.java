package entities;

/**
 * @author Emanuel
 *
 */
public class Extrato {
	private long id;
	private int mes;
	private int ano;
	private float valorInicial;
	private float valorFinal;
	private long idConta;

	public Extrato(long id, int mes, int ano, float valorInicial, float valorFinal, long idConta) {
		this(mes, ano, valorInicial, valorFinal, idConta);
		this.id = id;
	}

	public Extrato(int mes, int ano, float valorInicial, float valorFinal, long idConta) {
		this.mes = mes;
		this.ano = ano;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		this.idConta = idConta;
		setId();
	}

	public Extrato() {
		this(0, 0, 0.0f, 0.0f, 0);
	}

	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
		setId();
	}

	/**
	 * @return the ano
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @param ano
	 *            the ano to set
	 */
	public void setAno(int ano) {
		this.ano = ano;
		setId();
	}

	/**
	 * @return the valorInicial
	 */
	public float getValorInicial() {
		return valorInicial;
	}

	/**
	 * @param valorInicial
	 *            the valorInicial to set
	 */
	public void setValorInicial(float valorInicial) {
		this.valorInicial = valorInicial;
	}

	/**
	 * @return the valorFinal
	 */
	public float getValorFinal() {
		return valorFinal;
	}

	/**
	 * @param valorFinal
	 *            the valorFinal to set
	 */
	public void setValorFinal(float valorFinal) {
		this.valorFinal = valorFinal;
	}

	/**
	 * @return the idConta
	 */
	public long getIdConta() {
		return idConta;
	}

	/**
	 * @param idConta
	 *            the idConta to set
	 */
	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	public void setId() {
		final int primo = 31;
		id = 1;		
		id = primo * id + Integer.toString(ano).hashCode();
		id = primo * id + Integer.toString(mes).hashCode();
		id = primo * id + Long.toString(idConta).hashCode();
	}

	@Override
	public String toString() {
		return "Extrato [" + id + ", " + mes + ", " + ano + ", " + idConta + "]";
	}
}
