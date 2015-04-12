package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AtualizarTransacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtRecorrencia;
	private JTextField txtValor;
	private JTextField txtFormaDePgmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AtualizarTransacao("Test");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AtualizarTransacao(Object transacao) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblListaTransacoes = new JLabel("Atualizar Transa\u00E7\u00E3o");
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
		txtTitulo.setText(transacao.toString());
		
		JLabel lblRecorrencia = new JLabel("x");
		lblRecorrencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecorrencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblRecorrencia.setBounds(170, 50, 20, 20);
		panel.add(lblRecorrencia);
		
		txtRecorrencia = new JTextField();
		txtRecorrencia.setBounds(190, 50, 70, 20);
		panel.add(txtRecorrencia);
		txtRecorrencia.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblValor.setBounds(20, 50, 40, 20);
		panel.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setBounds(70, 50, 100, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCategoria.setBounds(20, 80, 60, 20);
		panel.add(lblCategoria);
		
		List<String> listaCategorias = new ArrayList<String>();
		listaCategorias.add("(Todas as Categorias)");
		listaCategorias.add("Viagem");
		JComboBox<Object> comboBox = new JComboBox<Object>(listaCategorias.toArray());
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBox.setBounds(80, 80, 180, 20);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);
		
		JLabel lblFormaDePgmt = new JLabel("Forma de Pgmt");
		lblFormaDePgmt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFormaDePgmt.setBounds(20, 110, 80, 20);
		panel.add(lblFormaDePgmt);
		
		txtFormaDePgmt = new JTextField();
		txtFormaDePgmt.setBounds(110, 110, 150, 20);
		panel.add(txtFormaDePgmt);
		txtFormaDePgmt.setColumns(10);
		
		JTextArea txtrObservacoes = new JTextArea();
		txtrObservacoes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtrObservacoes.setText("Observacoes");
		txtrObservacoes.setBounds(20, 140, 240, 20);
		panel.add(txtrObservacoes);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(20, 170, 80, 20);
		panel.add(btnSalvar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(180, 170, 80, 20);
		panel.add(btnApagar);
	}
}
