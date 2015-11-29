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

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
		setId();
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
		setId();
	}

	public float getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(float valorInicial) {
		this.valorInicial = valorInicial;
	}

	public float getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(float valorFinal) {
		this.valorFinal = valorFinal;
	}

	public long getIdConta() {
		return idConta;
	}

	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}

	public long getId() {
		return id;
	}

	public void setId() {
		final int primo = 31;
		id = 1;		
		id = primo * id + Integer.toString(ano).hashCode()*100;
		id = primo * id + Integer.toString(mes).hashCode();
		id = primo * id + Long.toString(idConta).hashCode();
	}

	@Override
	public String toString() {
		return "Extrato [" + id + ", " + mes + ", " + ano + ", " + idConta + "]";
	}
}
