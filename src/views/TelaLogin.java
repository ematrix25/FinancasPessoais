package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Ambiente de login do usuário
public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblSejaBemVindo = new JLabel("Seja Bem-vindo");
		lblSejaBemVindo.setBounds(97, 30, 300, 50);
		lblSejaBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSejaBemVindo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblSejaBemVindo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(97, 100, 300, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUsuario.setBounds(40, 40, 40, 20);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtUsuario.setBounds(90, 40, 180, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSenha.setBounds(40, 80, 40, 20);
		panel.add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				new TelaExtrato();
				dispose();
			}
		});
		btnEntrar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEntrar.setBounds(110, 120, 80, 20);
		panel.add(btnEntrar);
		
		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		pfSenha.setBounds(90, 80, 180, 20);
		panel.add(pfSenha);
		
		JButton btnEsqueciASenha = new JButton("Esqueci a Senha");
		btnEsqueciASenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEsqueciASenha.setBounds(160, 160, 110, 20);
		panel.add(btnEsqueciASenha);
		
		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCriarConta.setBounds(40, 160, 90, 20);
		panel.add(btnCriarConta);
	}
}
