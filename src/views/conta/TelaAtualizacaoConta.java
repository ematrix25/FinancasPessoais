package views.conta;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//Ambiente de atualização da conta
public class TelaAtualizacaoConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaAtualizacaoConta("Test");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAtualizacaoConta(Object transacao) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 240);
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
		panel.setBounds(17, 50, 230, 140);
		contentPane.add(panel);
		panel.setLayout(null);	
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBanco.setBounds(20, 20, 40, 20);
		panel.add(lblBanco);
		
		//Esses dados serão buscados do banco de dados
		List<String> listaBancos = new ArrayList<String>();
		listaBancos.add("Bradesco");
		listaBancos.add("Itau");
		
		JComboBox<Object> cbBanco = new JComboBox<Object>(listaBancos.toArray());
		cbBanco.setEditable(true);
		cbBanco.setBounds(70, 20, 140, 20);
		panel.add(cbBanco);
		
		JLabel lblAgencia = new JLabel("Ag\u00EAncia");
		lblAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAgencia.setBounds(20, 50, 40, 20);
		panel.add(lblAgencia);
		
		//Esses dados serão buscados do banco de dados
		List<String> listaAgencias = new ArrayList<String>();
		listaAgencias.add("4152-5");
		listaAgencias.add("4578-9");
		
		JComboBox<Object> cbAgencia = new JComboBox<Object>(listaAgencias.toArray());
		cbAgencia.setEditable(true);
		cbAgencia.setBounds(70, 50, 140, 20);
		panel.add(cbAgencia);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNumero.setBounds(20, 80, 40, 20);
		panel.add(lblNumero);
		
		//Esses dados serão buscados do banco de dados
		List<String> listaNumeros = new ArrayList<String>();
		listaNumeros.add("13525-4");
		listaNumeros.add("34589-7");
		
		JComboBox<Object> cbNumero = new JComboBox<Object>(listaNumeros.toArray());
		cbNumero.setEditable(true);
		cbNumero.setBounds(70, 80, 140, 20);
		panel.add(cbNumero);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});				
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(20, 110, 80, 20);
		panel.add(btnSalvar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnApagar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnApagar.setBounds(130, 110, 80, 20);
		panel.add(btnApagar);
	}
}
