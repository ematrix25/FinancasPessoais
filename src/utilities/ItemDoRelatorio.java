package utilities;

import java.text.DecimalFormat;

/**
 * @author Emanuel
 *
 */
public class ItemDoRelatorio {
	private String categoria;
	private float porcentagem;

	public ItemDoRelatorio(String categoria, float porcentagem) {
		this.categoria = categoria;
		this.porcentagem = porcentagem;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(float porcentagem) {
		this.porcentagem = porcentagem;
	}

	public boolean equals(String categoria) {
		return this.categoria.equals(categoria);
	}

	@Override
	public String toString() {
		DecimalFormat formato = new DecimalFormat("#.0");
		return categoria + ": " + formato.format(porcentagem) + "%";
	}
}
