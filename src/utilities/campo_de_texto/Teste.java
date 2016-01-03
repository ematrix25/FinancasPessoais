package utilities.campo_de_texto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

/**
 * @author Emanuel
 *
 */
public class Teste extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CampoDeTextoFormatado texto1;
	private JFormattedTextField texto2;
	
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
		
		try {
			texto1 = new CampoDeTextoFormatado("Número da Agencia", new MaskFormatter("####-#"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		texto1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		texto1.setColumns(10);
		texto1.setBounds(10, 10, 164, 20);
		contentPane.add(texto1);
		
		try {
			texto2 = new JFormattedTextField(new MaskFormatter("#####-#"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		texto2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		texto2.setColumns(10);
		texto2.setBounds(10, 40, 164, 20);
		contentPane.add(texto2);
		
		JButton btnTestar = new JButton("Testar");
		btnTestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println();
				System.out.println("Test");
				System.out.println("Texto 1 = "+texto1.getValue());
				System.out.println("Texto 2 = "+texto2.getValue());
				System.out.println();
			}
		});
		btnTestar.setBounds(45, 78, 89, 23);
		contentPane.add(btnTestar);
	}
}
