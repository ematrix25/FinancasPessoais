package entities;

/**
 * @author Emanuel
 *
 */
public class Extrato {
	private int id;
	private int mes;
	private int ano;
	private float valorInicial;
	private float valorFinal;

	public Extrato(int id, int mes, int ano, float valorInicial, float valorFinal) {
		this(mes, ano, valorInicial, valorFinal);
		this.id = id;
	}

	public Extrato(int mes, int ano, float valorInicial, float valorFinal) {
		this.mes = mes;
		this.ano = ano;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		setId();
	}

	public Extrato() {
		this(0, 0, 0.0f, 0.0f);
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void setId() {
		final int primo = 31;
		id = 1;
		id = primo * id + Integer.toString(ano).hashCode();
		id = primo * id + Integer.toString(mes).hashCode();
	}

	@Override
	public String toString() {
		return "Extrato [" + id + ", " + mes + ", " + ano + "]";
	}
}
