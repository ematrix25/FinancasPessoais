package utilities.campo_de_texto;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFormattedTextField;

/**
 * @author Emanuel
 *
 */
public class CampoDeTextoFormatado extends JFormattedTextField implements FocusListener {

	private static final long serialVersionUID = 6268002285130562524L;

	public CampoDeTextoFormatado(String dica, AbstractFormatter formato) {
		super(formato);
		super.addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		System.out.println("FocusGained: " + this.getValue());
	}

	public void focusLost(FocusEvent e) {
		System.out.println("FocusLost: " + this.getValue());
	}
}
