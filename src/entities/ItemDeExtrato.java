package entities;

/**
 * @author Emanuel
 *
 */
public class ItemDeExtrato {
	private long id;
	private String titulo;
	private float valor;
	private String observacao;
	private int dia;
	private int ocorrencia;
	private TipoItemDeExtrato tipo;
	private long idExtrato;

	public ItemDeExtrato(long id, String titulo, float valor, String observacoes, int dia, int ocorrencia,
			TipoItemDeExtrato tipo, long idExtrato) {
		this(titulo, valor, observacoes, dia, ocorrencia, tipo, idExtrato);
		this.id = id;
	}

	public ItemDeExtrato(String titulo, float valor, String observacoes, int dia, int ocorrencia,
			TipoItemDeExtrato tipo, long idExtrato) {
		this.titulo = titulo;
		this.valor = valor;
		this.observacao = observacoes;
		this.dia = dia;
		this.ocorrencia = ocorrencia;
		this.tipo = tipo;
		this.idExtrato = idExtrato;
		setId();
	}

	public ItemDeExtrato() {
		this("", 0.0f, "", 0, 0, null, 0);
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
		setId();
	}

	/**
	 * @return the valor
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao
	 *            the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the dia
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * @param dia
	 *            the dia to set
	 */
	public void setDia(int dia) {
		this.dia = dia;
		setId();
	}

	/**
	 * @return the ocorrencia
	 */
	public int getOcorrencia() {
		return ocorrencia;
	}

	/**
	 * @param ocorrencia
	 *            the ocorrencia to set
	 */
	public void setOcorrencia(int ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	/**
	 * @return the tipo
	 */
	public TipoItemDeExtrato getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoItemDeExtrato tipo) {
		this.tipo = tipo;
		setId();
	}

	/**
	 * @return the idExtrato
	 */
	public long getIdExtrato() {
		return idExtrato;
	}

	/**
	 * @param idExtrato
	 *            the idExtrato to set
	 */
	public void setIdExtrato(long idExtrato) {
		this.idExtrato = idExtrato;
		setId();
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
		id = primo * id + titulo.hashCode();
		id = primo * id + Integer.valueOf(dia).hashCode();
		id = primo * id + tipo.hashCode();
		id = primo * id + Long.valueOf(idExtrato).hashCode();
	}

	@Override
	public String toString() {
		return "ItemDeExtrato [" + id + ", " + titulo + ", " + dia + ", " + tipo + ", " + idExtrato + "]";
	}
}
