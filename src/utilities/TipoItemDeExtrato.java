package utilities;

/**
 * @author Emanuel
 *
 */
public enum TipoItemDeExtrato {
	despesa("despesa"), receita("receita");

	public String tipo;

	 TipoItemDeExtrato(String tipo) {
		this.tipo = tipo.toLowerCase();
	}

}
