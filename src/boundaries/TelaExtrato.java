package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import boundaries.conta.TelaAtualizacaoConta;
import boundaries.conta.TelaCadastroConta;
import boundaries.item_de_extrato.TelaAtualizacaoItemDeExtrato;
import boundaries.item_de_extrato.TelaCadastroItemDeExtrato;
import controllers.ContaCon;
import entities.Conta;

//Ambiente de visualização do extrato
public class TelaExtrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ContaCon contaCon;

	/**
	 * Create the frame.
	 */
	public TelaExtrato(final String nomeUsuario) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		JLabel lblExtrato = new JLabel("Extrato");
		lblExtrato.setBounds(22, 10, 450, 20);
		lblExtrato.setHorizontalAlignment(SwingConstants.CENTER);
		lblExtrato.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblExtrato);

		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(22, 40, 450, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblConta = new JLabel("Conta");
		lblConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblConta.setBounds(220, 10, 40, 20);
		panel.add(lblConta);

		contaCon = new ContaCon(nomeUsuario);
		final List<Conta> contas = contaCon.getContas();
		
		final List<String> listaNumConta = new ArrayList<String>();		
		for (int i = 0; i < contas.size(); i++) {
			Conta contaAux = contas.get(i);
			if (contas.get(0).getAgencia().equals(contaAux.getAgencia()))
				listaNumConta.add(contaAux.getNumero());
		}

		final JComboBox<Object> cbNumConta = new JComboBox<Object>(listaNumConta.toArray());
		cbNumConta.setSelectedIndex(0);
		cbNumConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbNumConta.setBounds(260, 10, 70, 20);
		panel.add(cbNumConta);
		
		JLabel lblAgencia = new JLabel("Agencia");
		lblAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAgencia.setBounds(99, 10, 50, 20);
		panel.add(lblAgencia);

		final List<String> listaAgencias = new ArrayList<String>();
		for (int i = 0; i < contas.size(); i++) {
			Conta contaAux = contas.get(i);
			if (contas.get(0).getBanco().equals(contaAux.getBanco()))
				listaAgencias.add(contaAux.getAgencia());
		}
		
		final JComboBox<Object> cbAgencia = new JComboBox<Object>(listaAgencias.toArray());
		cbAgencia.setSelectedIndex(0);
		cbAgencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaNumConta.clear();
				for (int i = 0; i < contas.size(); i++) {
					Conta contaAux = contas.get(i);
					if (cbAgencia.getSelectedItem().equals(contaAux.getAgencia()))
						listaNumConta.add(contaAux.getNumero());
				}
			}
		});
		cbAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbAgencia.setBounds(150, 10, 60, 20);
		panel.add(cbAgencia);		
		
		List<String> listaBancos = new ArrayList<String>();
		for (int i = 0; i < contas.size(); i++) {
			Conta contaAux = contas.get(i);
			listaBancos.add(contaAux.getBanco());
		}

		final JComboBox<Object> cbBanco = new JComboBox<Object>(listaBancos.toArray());
		cbBanco.setSelectedIndex(0);
		cbBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaAgencias.clear();
				listaNumConta.clear();
				for (int i = 0; i < contas.size(); i++) {
					Conta contaAux = contas.get(i);
					String agenciaInicial = "";
					if (cbBanco.getSelectedItem().equals(contaAux.getBanco())) {
						if(listaAgencias.isEmpty()) {
							agenciaInicial = contaAux.getAgencia();
						}
						listaAgencias.add(contaAux.getAgencia());
						if(agenciaInicial.equals(contaAux.getAgencia()))
							listaNumConta.add(contaAux.getNumero());
					}
				}
			}
		});
		cbBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbBanco.setBounds(20, 10, 70, 20);
		panel.add(cbBanco);		

		JMenuBar mnbConta = new JMenuBar();
		mnbConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnbConta.setBounds(340, 10, 80, 20);
		panel.add(mnbConta);

		JMenu mnConta = new JMenu("Op\u00E7\u00F5es Conta");
		mnConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnbConta.add(mnConta);
		
		final TelaExtrato tela = this;
		
		JMenuItem mntmCadastrarConta = new JMenuItem("Cadastrar");
		mntmCadastrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaCadastroConta(tela, nomeUsuario);					
			}
		});
		mntmCadastrarConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnConta.add(mntmCadastrarConta);

		JMenuItem mntmAtualizarConta = new JMenuItem("Atualizar");
		mntmAtualizarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(),
						cbAgencia.getSelectedItem().toString(), cbNumConta.getSelectedItem().toString());
				new TelaAtualizacaoConta(tela, nomeUsuario, contaAux);
			}
		});
		mntmAtualizarConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnConta.add(mntmAtualizarConta);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAno.setBounds(220, 40, 30, 20);
		panel.add(lblAno);

		// Esses anos serão buscados do banco de dados
		List<String> listaAnos = new ArrayList<String>();
		listaAnos.add("2015");
		listaAnos.add("2014");

		JComboBox<Object> cbAno = new JComboBox<Object>(listaAnos.toArray());
		cbAno.setSelectedIndex(0);
		cbAno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbAno.setBounds(260, 40, 70, 20);
		panel.add(cbAno);

		JLabel lblMes = new JLabel("M\u00EAs");
		lblMes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMes.setBounds(20, 40, 30, 20);
		panel.add(lblMes);

		// Esses meses serão buscados do banco de dados
		List<String> listaMeses = new ArrayList<String>();
		listaMeses.add("Janeiro");
		listaMeses.add("Fevereiro");
		listaMeses.add("Março");
		listaMeses.add("Abril");

		JComboBox<Object> cbMes = new JComboBox<Object>(listaMeses.toArray());
		cbMes.setSelectedIndex(0);
		cbMes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbMes.setBounds(60, 40, 140, 20);
		panel.add(cbMes);

		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnListar.setBounds(340, 40, 80, 20);
		panel.add(btnListar);

		// Esses itens de extrato serão buscados do banco de dados
		List<String> listaDeItensDeExtrato = new ArrayList<String>();
		listaDeItensDeExtrato.add("IPTU");
		listaDeItensDeExtrato.add("Pensão");
		listaDeItensDeExtrato.add("Passagem para Miami");
		listaDeItensDeExtrato.add("Playstation 4");
		listaDeItensDeExtrato.add("Noitada");
		listaDeItensDeExtrato.add("Taxi");
		listaDeItensDeExtrato.add("Lanche no Burger King");
		listaDeItensDeExtrato.add("Taxa de AABB");

		final JList<Object> lstItensDeExtrato = new JList<Object>(listaDeItensDeExtrato.toArray());
		lstItensDeExtrato.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstItensDeExtrato.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(),
						cbAgencia.getSelectedItem().toString(), cbNumConta.getSelectedItem().toString());
				new TelaAtualizacaoItemDeExtrato(contaAux.getId(), lstItensDeExtrato.getSelectedValue().toString());
			}
		});
		lstItensDeExtrato.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lstItensDeExtrato.setBounds(20, 70, 400, 200);
		panel.add(lstItensDeExtrato);

		JButton btnCriarItemDeExtrato = new JButton("Criar Item de Extrato");
		btnCriarItemDeExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(),
						cbAgencia.getSelectedItem().toString(), cbNumConta.getSelectedItem().toString());
				new TelaCadastroItemDeExtrato(contaAux.getId());
			}
		});
		btnCriarItemDeExtrato.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCriarItemDeExtrato.setBounds(20, 280, 140, 20);
		panel.add(btnCriarItemDeExtrato);

		JButton btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaRelatorio();
			}
		});
		btnGerarRelatorio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGerarRelatorio.setBounds(300, 280, 120, 20);
		panel.add(btnGerarRelatorio);
	}
}
