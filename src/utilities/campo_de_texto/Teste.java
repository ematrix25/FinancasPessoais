package utilities.campo_de_texto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Emanuel
 *
 */
public class Teste extends JFrame {

	private static final long serialVersionUID = -4814814510626621697L;
	private JPanel contentPane;
	private CampoDeTextoLimitado usuario;
	private CampoDeSenha senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Teste() {
		setTitle("Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usuario = new CampoDeTextoLimitado("Usuario", "nl$");
		usuario.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		usuario.setColumns(10);
		usuario.setBounds(10, 10, 164, 20);
		contentPane.add(usuario);

		senha = new CampoDeSenha("Senha", "nl$");
		senha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		senha.setColumns(10);
		senha.setBounds(10, 40, 164, 20);
		contentPane.add(senha);

		JButton btnTestar = new JButton("Testar");
		btnTestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println();
				System.out.println("Conta");
				System.out.println("Usuario = " + usuario.getText());
				System.out.println("Senha = " + senha.getText());
				System.out.println();
			}
		});
		btnTestar.setBounds(45, 78, 89, 23);
		contentPane.add(btnTestar);
	}
}
