package boundaries.conta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import boundaries.TelaExtrato;
import controllers.ContaCon;
import entities.Conta;
import utilities.campo_de_texto.CampoDeTexto;

public class TelaCadastroConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CampoDeTexto txtBanco;
	private JFormattedTextField txtAgencia;
	private JFormattedTextField txtNumero;
	private JTextField txtSaldo;
	private ContaCon contaCon;

	public TelaCadastroConta(final TelaExtrato tela, final String nomeUsuario) {
		setTitle("Finanças Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 270);
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
		panel.setBounds(17, 50, 230, 170);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBanco.setBounds(20, 20, 40, 20);
		panel.add(lblBanco);

		txtBanco = new CampoDeTexto("Nome do Banco");
		txtBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtBanco.setBounds(70, 20, 140, 20);
		panel.add(txtBanco);
		txtBanco.setColumns(10);

		JLabel lblAgencia = new JLabel("Agência");
		lblAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAgencia.setBounds(20, 50, 40, 20);
		panel.add(lblAgencia);

		try {
			txtAgencia = new JFormattedTextField(new MaskFormatter("####-#"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(70, 50, 140, 20);
		panel.add(txtAgencia);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNumero.setBounds(20, 80, 40, 20);
		panel.add(lblNumero);

		try {
			txtNumero = new JFormattedTextField(new MaskFormatter("#####-#"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtNumero.setColumns(10);
		txtNumero.setBounds(70, 80, 140, 20);
		panel.add(txtNumero);

		contaCon = new ContaCon(nomeUsuario);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String banco = txtBanco.getText();
				if (!contaCon.validarBanco(banco)) {
					JOptionPane.showMessageDialog(null, "Banco inválido! Tente de novo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String agencia = txtAgencia.getText();
				if (!contaCon.validarAgencia(agencia)) {
					JOptionPane.showMessageDialog(null, "Agencia inválida! Tente de novo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String numero = txtNumero.getText();
				if (!contaCon.validarNumero(numero)) {
					JOptionPane.showMessageDialog(null, "Número inválido! Tente de novo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (txtSaldo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o saldo da conta", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				float saldo = Float.parseFloat(txtSaldo.getText());

				if (contaCon.cadastrar(new Conta(banco, agencia, numero, saldo))) {
					JOptionPane.showMessageDialog(null, "A conta foi registrada com sucesso");
					tela.dispose();
					new TelaExtrato(nomeUsuario);
				} else
					JOptionPane.showMessageDialog(null, "A conta não foi criada! Essa conta já existe");
				dispose();
			}
		});

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSaldo.setBounds(20, 110, 40, 20);
		panel.add(lblSaldo);

		txtSaldo = new JTextField();
		txtSaldo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(70, 110, 140, 20);
		panel.add(txtSaldo);
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(75, 140, 80, 20);
		panel.add(btnSalvar);
	}
}
