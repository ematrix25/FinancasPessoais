package entities;

import utilities.TipoItemDeExtrato;

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
	private String categoria;

	public ItemDeExtrato(long id, String titulo, float valor, String observacoes, int dia, int ocorrencia,
			TipoItemDeExtrato tipo, long idExtrato, String categoria) {
		this(titulo, valor, observacoes, dia, ocorrencia, tipo, idExtrato, categoria);
		this.id = id;
	}

	public ItemDeExtrato(String titulo, float valor, String observacoes, int dia, int ocorrencia,
			TipoItemDeExtrato tipo, long idExtrato, String categoria) {
		this.titulo = titulo;
		this.valor = valor;
		this.observacao = observacoes;
		this.dia = dia;
		this.ocorrencia = ocorrencia;
		this.tipo = tipo;
		this.idExtrato = idExtrato;
		this.categoria = categoria;
		setId();
	}

	public ItemDeExtrato() {
		this("", 0.0f, "", 0, 0, null, 0, "");
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		setId();
	}
	
	public static boolean validateTitulo(String titulo) {
		return titulo.matches("^[\\p{L} .'-]+$");
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
		setId();
	}

	public int getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(int ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public TipoItemDeExtrato getTipo() {
		return tipo;
	}

	public void setTipo(TipoItemDeExtrato tipo) {
		this.tipo = tipo;
		setId();
	}

	public long getIdExtrato() {
		return idExtrato;
	}
	
	public void setIdExtrato(long idExtrato) {
		this.idExtrato = idExtrato;
		setId();
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public long getId() {
		return id;
	}

	public void setId() {
		final int primo = 31;
		id = 1;
		id = primo * id + titulo.hashCode();
		id = primo * id + Integer.valueOf(dia).hashCode();
		id = primo * id + tipo.toString().hashCode();
		id = primo * id + Long.valueOf(idExtrato).hashCode();
	}

	@Override
	public String toString() {
		return "ItemDeExtrato [" + id + ", " + titulo + ", " + dia + ", " + tipo + ", " + idExtrato + "]";
	}
}
