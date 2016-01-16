package utilities.campo_de_texto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Emanuel
 *
 */
public class CampoDeTextoFormatado extends CampoDeTexto implements KeyListener {

	private static final long serialVersionUID = 6268002285130562524L;
	private String formato;
	int tam;

	/**
	 * @param dica
	 * @param formato
	 *            <br>
	 *            Os tipos dos limites do formato são:<br>
	 *            -n para numeros<br>
	 *            -l para letras<br>
	 *            -s para espaços em branco<br>
	 *            -$ para caracteres especiais
	 */
	public CampoDeTextoFormatado(String dica, String formato) {
		super(dica);
		this.formato = processarFormato(formato.toUpperCase());
		super.addKeyListener(this);
	}

	private String processarFormato(String formato) {
		StringBuilder formatoAux = new StringBuilder();
		char aux;
		int pos = 0;
		boolean wasNumeric = false;
		tam = formato.length();
		for (int i = 0; i < tam; i++) {
			aux = formato.charAt(i);
			if (Character.isDigit(aux)) {
				pos = i;
				wasNumeric = true;
			} else if (wasNumeric) {
				for (int j = 0; j < Integer.parseInt(formato.substring(pos, i)); j++) {
					formatoAux.append(aux);
				}
				wasNumeric = false;
			} else
				formatoAux.append(aux);
		}
		tam = formatoAux.length();
		return formatoAux.toString();
	}

	private boolean isSpecialCharacter(char c) {
		return !(Character.isDigit(c) || Character.isLetter(c) || Character.isWhitespace(c));
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (c == KeyEvent.VK_DELETE) {
			return;
		}
		int pos = this.getCaretPosition();
		if (pos < tam && this.getText().length() != tam)
			switch (formato.charAt(pos)) {
			case 'N':
				if (Character.isDigit(c)) {
					pos++;
					return;
				}
				break;
			case 'L':
				if (Character.isLetter(c)) {
					pos++;
					return;
				}
				break;
			case 'S':
				if (Character.isWhitespace(c)) {
					pos++;
					return;
				}
				break;
			case '$':
				if (isSpecialCharacter(c)) {
					pos++;
					return;
				}
				break;
			}
		getToolkit().beep();
		arg0.consume();
	}
}
