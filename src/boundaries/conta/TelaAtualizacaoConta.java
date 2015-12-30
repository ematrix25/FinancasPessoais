package boundaries.conta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import boundaries.TelaExtrato;
import controllers.ContaCon;
import entities.Conta;

public class TelaAtualizacaoConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBanco;
	private JTextField txtAgencia;
	private JTextField txtNumero;
	private JTextField txtSaldo;
	private ContaCon contaCon;

	public TelaAtualizacaoConta(final TelaExtrato tela, final String nomeUsuario, final Conta contaAux) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		JLabel lblAtualizarConta = new JLabel("Atualizar Conta");
		lblAtualizarConta.setBounds(17, 10, 230, 20);
		lblAtualizarConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizarConta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblAtualizarConta);

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

		txtBanco = new JTextField();
		txtBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtBanco.setBounds(70, 20, 140, 20);
		txtBanco.setText(contaAux.getBanco());
		panel.add(txtBanco);

		JLabel lblAgencia = new JLabel("Ag\u00EAncia");
		lblAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAgencia.setBounds(20, 50, 40, 20);
		panel.add(lblAgencia);

		txtAgencia = new JTextField();
		txtAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtAgencia.setBounds(70, 50, 140, 20);
		txtAgencia.setText(contaAux.getAgencia());
		panel.add(txtAgencia);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNumero.setBounds(20, 80, 40, 20);
		panel.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtNumero.setBounds(70, 80, 140, 20);
		txtNumero.setText(contaAux.getNumero());
		panel.add(txtNumero);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSaldo.setBounds(20, 110, 40, 20);
		panel.add(lblSaldo);

		txtSaldo = new JTextField();
		txtSaldo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(70, 110, 140, 20);

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

				if (contaCon.atualizar(contaAux.getId(), new Conta(banco, agencia, numero, saldo))) {
					JOptionPane.showMessageDialog(null, "A conta foi atualizada com sucesso");
					tela.dispose();
					new TelaExtrato(nomeUsuario);
				} else
					JOptionPane.showMessageDialog(null, "A conta não foi atualizada");
				dispose();
			}
		});
		panel.add(txtSaldo);
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(20, 140, 80, 20);
		panel.add(btnSalvar);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
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
				
				Conta conta = new Conta(banco, agencia, numero, saldo);
				if (contaAux.getId() != conta.getId()) {
					JOptionPane.showMessageDialog(null, "Não altere os dados se quiser remover", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (contaCon.remover(conta.getId())) {
					JOptionPane.showMessageDialog(null, "A conta foi removida com sucesso");
					tela.dispose();
					new TelaExtrato(nomeUsuario);
				} else
					JOptionPane.showMessageDialog(null, "A conta não foi removida");
				dispose();
			}
		});
		btnApagar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnApagar.setBounds(130, 140, 80, 20);
		panel.add(btnApagar);
	}
}
