package utilities.campo_de_texto;

import java.awt.event.KeyEvent;

/**
 * @author Emanuel
 *
 */
public class CampoDeSenha extends CampoDeTextoLimitado {

	private static final long serialVersionUID = 6268002285130562524L;
	private String senha = "";

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
	public CampoDeSenha(String dica, String limites) {
		super(dica, limites);
	}

	private boolean isKeyCharValid(char c) {
		String limites = super.getLimites();
		if (limites.contains("N") && Character.isDigit(c))
			return true;
		if (limites.contains("L") && Character.isLetter(c))
			return true;
		if (limites.contains("S") && Character.isWhitespace(c))
			return true;
		if (limites.contains("$") && super.isSpecialCharacter(c))
			return true;
		return false;
	}

	public void keyTyped(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		int pos = -1;
		if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE)
			if (!isKeyCharValid(c)) {
				getToolkit().beep();
				arg0.consume();
			} else {
				senha = senha + arg0.getKeyChar();
			}
		else {
			pos = super.getCaretPosition();
			System.out.println("#1 - " + pos);
			if (pos < senha.length())
				senha = senha.substring(0, pos) + senha.substring(pos + 1);
			else
				senha = senha.substring(0, pos);
		}
		if (pos > -1) {
			pos = super.getCaretPosition();
			System.out.println("#2a - " + pos);
			super.setText(new String(new char[senha.length()]).replace('\0', '*'));
			super.setCaretPosition(pos);
			System.out.println("#2b - " + pos);
		} else {
			pos = super.getCaretPosition();
			System.out.println("#3a - " + pos);
			super.setText(new String(new char[senha.length()]).replace('\0', '*'));
			super.setCaretPosition(pos + 1);
			System.out.println("#3b - " + pos);
		}
		arg0.consume();
	}

	public String getText() {
		if (isMostrandoDica())
			return "";
		else
			return senha;
	}
}
