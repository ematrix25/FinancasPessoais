package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Emanuel
 *
 */
public final class NomesDeMes {
	private static String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
			"Setembro", "Outubro", "Novembro", "Dezembro" };
	private final static List<String> nomes = new ArrayList<String>(Arrays.asList(meses));

	public static String getNome(int numero) {
		return nomes.get(numero - 1);
	}

	public static int getNumero(String nome) {
		return nomes.indexOf(nome) + 1;
	}
}
