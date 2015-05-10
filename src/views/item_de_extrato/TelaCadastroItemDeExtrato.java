package views.item_de_extrato;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//Ambiente de cadastro do item do extrato
public class TelaCadastroItemDeExtrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtOcorrencia;
	private JTextField txtValor;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAno;
	private JTextField txtObservacoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaCadastroItemDeExtrato();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroItemDeExtrato() {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblCadastrarItem = new JLabel("Cadastrar Item de Extrato");
		lblCadastrarItem.setBounds(17, 10, 280, 20);
		lblCadastrarItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarItem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblCadastrarItem);
		
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
		
		JLabel lblOcorrencia = new JLabel("x");
		lblOcorrencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcorrencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblOcorrencia.setBounds(170, 50, 20, 20);
		panel.add(lblOcorrencia);
		
		txtOcorrencia = new JTextField();
		txtOcorrencia.setText("Vezes");
		txtOcorrencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtOcorrencia.setBounds(190, 50, 70, 20);
		panel.add(txtOcorrencia);
		txtOcorrencia.setColumns(10);
		
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
		lblCategoria.setBounds(20, 80, 50, 20);
		panel.add(lblCategoria);
		
		//Esses dados serão buscados do banco de dados
		List<String> listaCategorias = new ArrayList<String>();
		listaCategorias.add("Viagem");
		listaCategorias.add("Salário");
		
		final JComboBox<Object> cbCategoria = new JComboBox<Object>(listaCategorias.toArray());
		cbCategoria.setEditable(true);
		cbCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbCategoria.setBounds(70, 80, 100, 20);
		cbCategoria.setSelectedIndex(-1);
		panel.add(cbCategoria);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbCategoria.getSelectedItem() == null)
					return;
				String categoria = cbCategoria.getSelectedItem().toString();
				Object opcoes[] = {"Atualizar", "Remover"};
				int resposta = JOptionPane.showOptionDialog(null,
						"O que deseja fazer com a categoria "+categoria+"?",
						"Atualizar Categoria", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null, opcoes, null);
				if(resposta == JOptionPane.NO_OPTION)
					System.out.println("Remover");
				if(resposta == JOptionPane.YES_OPTION)
					categoria = JOptionPane.showInputDialog(null,
							"Qual será o novo nome da categoria "+categoria+"?",
							"Atualizar Categoria", JOptionPane.PLAIN_MESSAGE);
				System.out.println("Categoria atualizada para "+categoria);
			}
		});
		btnEditar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEditar.setBounds(180, 80, 80, 20);
		panel.add(btnEditar);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblData.setBounds(20, 110, 40, 20);
		panel.add(lblData);
		
		txtDia = new JTextField();
		txtDia.setText("Dia");
		txtDia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDia.setBounds(70, 110, 40, 20);
		panel.add(txtDia);
		txtDia.setColumns(10);
		
		JLabel lblBarra1 = new JLabel("/");
		lblBarra1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarra1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBarra1.setBounds(110, 110, 20, 20);
		panel.add(lblBarra1);
		
		txtMes = new JTextField();
		txtMes.setText("M\u00EAs");
		txtMes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtMes.setColumns(10);
		txtMes.setBounds(130, 110, 40, 20);
		panel.add(txtMes);
		
		JLabel lblBarra2 = new JLabel("/");
		lblBarra2.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBarra2.setBounds(170, 111, 20, 20);
		panel.add(lblBarra2);
		
		txtAno = new JTextField();
		txtAno.setText("Ano");
		txtAno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtAno.setColumns(10);
		txtAno.setBounds(200, 110, 60, 20);
		panel.add(txtAno);
		
		txtObservacoes = new JTextField();
		txtObservacoes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtObservacoes.setText("Observa\u00E7\u00F5es");
		txtObservacoes.setBounds(20, 140, 240, 20);
		panel.add(txtObservacoes);
		txtObservacoes.setColumns(10);
		
		final String[] tiposDeTransacao = {"Despesa","Receita"};
		
		final JComboBox<Object> cbTipo = new JComboBox<Object>(tiposDeTransacao);
		cbTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbTipo.setBounds(20, 170, 70, 20);
		cbTipo.setSelectedIndex(0);
		panel.add(cbTipo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(180, 170, 80, 20);
		panel.add(btnSalvar);
	}

}
