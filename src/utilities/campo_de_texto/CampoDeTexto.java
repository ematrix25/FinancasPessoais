package utilities.campo_de_texto;

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

	public String getDica() {
		return dica;
	}

	public boolean isMostrandoDica() {
		return mostrandoDica;
	}

	public void setMostrandoDica(boolean mostrandoDica) {
		this.mostrandoDica = mostrandoDica;
	}

	public void focusGained(FocusEvent e) {
		if (super.getText().equals(dica)) {
			super.setText("");
			mostrandoDica = false;
		}
	}

	public void focusLost(FocusEvent e) {
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
