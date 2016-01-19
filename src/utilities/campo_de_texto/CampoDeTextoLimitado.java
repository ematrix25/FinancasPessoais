package utilities.campo_de_texto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Emanuel
 *
 */
public class CampoDeTextoLimitado extends CampoDeTexto implements KeyListener {

	private static final long serialVersionUID = -8362805320052465324L;
	private final String limites;

	/**
	 * @param dica
	 * @param limites
	 *            <br>
	 *            Os limites reconhecidos são:<br>
	 *            -n para numeros<br>
	 *            -l para letras<br>
	 *            -s para espaços em branco<br>
	 *            -$ para caracteres especiais
	 */
	public CampoDeTextoLimitado(String dica, String limites) {
		super(dica);
		this.limites = limites.toUpperCase();
		super.addKeyListener(this);
	}

	public String getLimites() {
		return limites;
	}

	protected boolean isSpecialCharacter(char c) {
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
		if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)
			return;
		if (limites.contains("N") && Character.isDigit(c))
			return;
		if (limites.contains("L") && Character.isLetter(c))
			return;
		if (limites.contains("S") && Character.isWhitespace(c))
			return;
		if (limites.contains("$") && isSpecialCharacter(c))
			return;
		getToolkit().beep();
		arg0.consume();
	}
}