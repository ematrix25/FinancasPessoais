package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RemoverTransacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtRecorrencia;
	private JTextField txtValor;
	private JTextField txtFormaDePgmt;
	private JTextField txtConta;	
	private JTextField txtObservacoes;
	private JTextField txtCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new RemoverTransacao("Test");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemoverTransacao(Object transacao) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblListaTransacoes = new JLabel("Remover Transa\u00E7\u00E3o");
		lblListaTransacoes.setBounds(17, 10, 280, 20);
		lblListaTransacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTransacoes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblListaTransacoes);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(17, 50, 280, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTitulo.setBounds(20, 20, 40, 20);
		panel.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTitulo.setBounds(70, 20, 190, 20);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		//Buscar os dados pelo titulo da transacao
		txtTitulo.setText(transacao.toString());
		txtTitulo.setEditable(false);
		
		JLabel lblRecorrencia = new JLabel("x");
		lblRecorrencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecorrencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblRecorrencia.setBounds(170, 50, 20, 20);
		panel.add(lblRecorrencia);
		
		txtRecorrencia = new JTextField();
		txtRecorrencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtRecorrencia.setBounds(190, 50, 70, 20);
		panel.add(txtRecorrencia);
		txtRecorrencia.setColumns(10);
		txtRecorrencia.setEditable(false);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblValor.setBounds(20, 50, 40, 20);
		panel.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtValor.setBounds(70, 50, 100, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);
		txtValor.setEditable(false);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCategoria.setBounds(20, 80, 60, 20);
		panel.add(lblCategoria);
		
		List<String> listaCategorias = new ArrayList<String>();
		listaCategorias.add("Viagem");
		listaCategorias.add("Salário");		
		txtCategoria = new JTextField();
		txtCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtCategoria.setBounds(80, 80, 180, 20);	
		txtCategoria.setColumns(10);
		if(new Random().nextBoolean())
			txtCategoria.setText(listaCategorias.get(0));
		else
			txtCategoria.setText(listaCategorias.get(1));
		txtCategoria.setEditable(false);
		panel.add(txtCategoria);
		
		JLabel lblFormaDePgmt = new JLabel("Forma de Pgmt");
		lblFormaDePgmt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFormaDePgmt.setBounds(20, 110, 80, 20);
		
		txtFormaDePgmt = new JTextField();
		txtFormaDePgmt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtFormaDePgmt.setBounds(110, 110, 150, 20);		
		txtFormaDePgmt.setColumns(10);
		txtFormaDePgmt.setEditable(false);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblConta.setBounds(20, 110, 40, 20);
		
		
		txtConta = new JTextField();
		txtConta.setBounds(70, 110, 190, 20);
		txtConta.setColumns(10);
		txtConta.setEditable(false);
		
		//Tipo da transacao buscado pelo parametro transacao
		String tipoTransacao = "Despesa";
		if(new Random().nextBoolean())
			tipoTransacao = "Receita";
		if(tipoTransacao.equals("Despesa")) {
			panel.add(lblFormaDePgmt);
			panel.add(txtFormaDePgmt);
		}			
		else {
			panel.add(lblConta);
			panel.add(txtConta);
		}
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnApagar.setBounds(100, 171, 80, 20);
		panel.add(btnApagar);
		
		txtObservacoes = new JTextField();
		txtObservacoes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtObservacoes.setText("Observa\u00E7\u00F5es");
		txtObservacoes.setBounds(20, 140, 240, 20);
		panel.add(txtObservacoes);
		txtObservacoes.setColumns(10);
		txtObservacoes.setEditable(false);
	}
}
