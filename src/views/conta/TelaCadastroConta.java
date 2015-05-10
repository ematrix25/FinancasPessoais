package views.conta;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//Ambiente de cadastro da conta
public class TelaCadastroConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBanco;	
	private JTextField txtAgencia;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaCadastroConta();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroConta() {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblCadastrarConta = new JLabel("Cadastrar Conta");
		lblCadastrarConta.setBounds(17, 10, 230, 20);
		lblCadastrarConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarConta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblCadastrarConta);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(17, 50, 230, 140);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBanco.setBounds(20, 20, 40, 20);
		panel.add(lblBanco);
		
		txtBanco = new JTextField();
		txtBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtBanco.setBounds(70, 20, 140, 20);
		panel.add(txtBanco);
		txtBanco.setColumns(10);
		
		JLabel lblAgencia = new JLabel("Ag\u00EAncia");
		lblAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAgencia.setBounds(20, 50, 40, 20);
		panel.add(lblAgencia);
		
		txtAgencia = new JTextField();
		txtAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(70, 50, 140, 20);
		panel.add(txtAgencia);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNumero.setBounds(20, 80, 40, 20);
		panel.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtNumero.setColumns(10);
		txtNumero.setBounds(70, 80, 140, 20);
		panel.add(txtNumero);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(75, 110, 80, 20);
		panel.add(btnSalvar);
	}
}
