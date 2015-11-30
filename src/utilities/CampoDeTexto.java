package utilities;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * @author Emanuel
 *
 */
public class CampoDeTexto extends JTextField implements FocusListener {

	private static final long serialVersionUID = 6268002285130562524L;
	private final String dica;
	private boolean mostrandoDica;

	public CampoDeTexto(String dica) {
		super(dica);
		this.dica = dica;
		super.addFocusListener(this);
		mostrandoDica = true;
	}

	public void focusGained(FocusEvent e) {
		System.out.println("Antes: " + this.getText());
		if (super.getText().equals(dica)) {
			super.setText("");
			mostrandoDica = false;
		}
	}

	public void focusLost(FocusEvent e) {
		System.out.println("Depois: " + this.getText());
		if (super.getText().equals("")) {
			super.setText(dica);
			mostrandoDica = true;
		}
	}

	public String getText() {
		if (mostrandoDica)
			return "";
		else
			return super.getText();
	}
}
