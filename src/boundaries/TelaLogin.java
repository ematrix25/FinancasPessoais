package boundaries;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.UsuarioCon;
import entities.Usuario;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pfSenha;
	private UsuarioCon usuarioCon;
	private JButton btnEntrar;
	
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

	public TelaLogin() {
		setTitle("Finanças Pessoais");
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

		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		pfSenha.setBounds(90, 80, 180, 20);
		panel.add(pfSenha);

		usuarioCon = new UsuarioCon();

		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txtUsuario.getText();
				if (!usuarioCon.validarNome(nome)) {
					JOptionPane.showMessageDialog(null, "Nome inválido. Tente de novo", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String senha = new String(pfSenha.getPassword());
				if (!usuarioCon.validarSenha(senha)) {
					JOptionPane.showMessageDialog(null, "Senha inválida. Tente de novo", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Usuario usuario = new Usuario(nome, senha);
				if (usuarioCon.autenticar(usuario)) {
					new TelaExtrato(usuario.getNome());
					dispose();
				} else
					JOptionPane.showMessageDialog(null, "A autenticação falhou! Usuário ou senha errados");

			}
		});
		btnEntrar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEntrar.setBounds(110, 120, 80, 20);
		panel.add(btnEntrar);

		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txtUsuario.getText();
				if (!usuarioCon.validarNome(nome)) {
					JOptionPane.showMessageDialog(null, "Nome inválido. Tente de novo", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String senha = new String(pfSenha.getPassword());
				if (!usuarioCon.validarSenha(senha)) {
					JOptionPane.showMessageDialog(null, "Senha inválida. Tente de novo", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String email = JOptionPane.showInputDialog(null, "Digite seu e-mail", "Criar Conta do Usuario",
						JOptionPane.PLAIN_MESSAGE);
				if (!usuarioCon.validarEmail(email)) {
					JOptionPane.showMessageDialog(null, "E-mail inválido. Tente de novo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Usuario usuario = new Usuario(nome, senha, email);
				if (usuarioCon.cadastrar(usuario))
					JOptionPane.showMessageDialog(null, "A conta do usuário foi criada com sucesso");
				else
					JOptionPane.showMessageDialog(null, "A conta do usuário não foi criada! Nome de usuário já existe");
			}
		});
		btnCriarConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCriarConta.setBounds(40, 160, 90, 20);
		panel.add(btnCriarConta);

		JButton btnEsqueciASenha = new JButton("Esqueci a Senha");
		btnEsqueciASenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txtUsuario.getText();
				if (!usuarioCon.validarNome(nome)) {
					JOptionPane.showMessageDialog(null, "Nome inválido. Tente de novo", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (!usuarioCon.enviarEmail(nome)) {
					JOptionPane.showMessageDialog(null, "Nome do usuário invalido! Tente de novo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String codNovaSenha = JOptionPane.showInputDialog(null,
						"Digite o codigo enviado para seu email com sua nova senha", "Recriar Senha do Usuario",
						JOptionPane.PLAIN_MESSAGE);
				
				Usuario usuario = new Usuario(nome, codNovaSenha);
				if (usuarioCon.atualizar(usuario))
					JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso");
				else
					JOptionPane.showMessageDialog(null, "A senha não foi atualizada");
			}
		});
		btnEsqueciASenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEsqueciASenha.setBounds(160, 160, 110, 20);
		panel.add(btnEsqueciASenha);
	}
}
