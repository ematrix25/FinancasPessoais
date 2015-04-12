package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CadastrarTransacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtRecorrencia;
	private JTextField txtValor;
	private JTextField txtFormaDePgmt;
	private JTextField txtConta;	
	private JTextField txtObservacoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTransacao frame = new CadastrarTransacao();
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
	public CadastrarTransacao() {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblListaTransacoes = new JLabel("Cadastrar Transa\u00E7\u00E3o");
		lblListaTransacoes.setBounds(17, 10, 280, 20);
		lblListaTransacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTransacoes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblListaTransacoes);
		
		final JPanel panel = new JPanel();
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
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblValor.setBounds(20, 50, 40, 20);
		panel.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		listaCategorias.add("Sal�rio");
		JComboBox<Object> cbCategoria = new JComboBox<Object>(listaCategorias.toArray());
		cbCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbCategoria.setBounds(80, 80, 180, 20);
		cbCategoria.setSelectedIndex(0);
		panel.add(cbCategoria);
		
		final JLabel lblFormaDePgmt = new JLabel("Forma de Pgmt");
		lblFormaDePgmt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFormaDePgmt.setBounds(20, 110, 80, 20);
		panel.add(lblFormaDePgmt);
		
		txtFormaDePgmt = new JTextField();
		txtFormaDePgmt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtFormaDePgmt.setBounds(110, 110, 150, 20);
		panel.add(txtFormaDePgmt);
		txtFormaDePgmt.setColumns(10);
		
		final JLabel lblConta = new JLabel("Conta");
		lblConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblConta.setBounds(20, 110, 40, 20);
		
		txtConta = new JTextField();
		txtConta.setBounds(70, 110, 190, 20);
		txtConta.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(180, 170, 80, 20);
		panel.add(btnSalvar);
		
		txtObservacoes = new JTextField();
		txtObservacoes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtObservacoes.setText("Observa\u00E7\u00F5es");
		txtObservacoes.setBounds(20, 140, 240, 20);
		panel.add(txtObservacoes);
		txtObservacoes.setColumns(10);
		
		final String[] tiposDeTransacao = {"Despesa","Receita"};
		final JComboBox<Object> cbTipoDeTransacao = new JComboBox<Object>(tiposDeTransacao);
		cbTipoDeTransacao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cbTipoDeTransacao.getSelectedIndex()==0) {
					panel.remove(lblConta);
					panel.add(lblFormaDePgmt);
					panel.remove(txtConta);
					panel.add(txtFormaDePgmt);
				}
				else {
					panel.remove(lblFormaDePgmt);
					panel.add(lblConta);
					panel.remove(txtFormaDePgmt);
					panel.add(txtConta);
				}
			}
		});
		cbTipoDeTransacao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbTipoDeTransacao.setBounds(20, 170, 120, 20);
		cbTipoDeTransacao.setSelectedIndex(0);
		panel.add(cbTipoDeTransacao);
	}

}
