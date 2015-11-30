package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import boundaries.conta.TelaAtualizacaoConta;
import boundaries.conta.TelaCadastroConta;
import boundaries.item_de_extrato.TelaAtualizacaoItemDeExtrato;
import boundaries.item_de_extrato.TelaCadastroItemDeExtrato;
import controllers.ContaCon;
import controllers.ExtratoCon;
import entities.Conta;
import entities.Extrato;
import entities.ItemDeExtrato;
import utilities.NomesDeMes;
import utilities.TipoItemDeExtrato;

public class TelaExtrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Object> cbBanco;
	private JComboBox<Object> cbAgencia;
	private JComboBox<Object> cbNumConta;
	private JComboBox<Object> cbAno;
	private JComboBox<Object> cbMes;
	private JTable tabela;
	private ContaCon contaCon;
	private ExtratoCon extratoCon;
	private List<Conta> contas;
	private List<ArrayList<Extrato>> extratos;
	private List<ItemDeExtrato> listaDosItensDeExtrato;
	private DefaultTableModel modeloDeTabela;
	private JTextField txtSaldoInicial;
	private JTextField txtSaldoFinal;

	public TelaExtrato(final String nomeUsuario) {
		setTitle("Finanças Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
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
		panel.setBounds(22, 40, 450, 360);
		contentPane.add(panel);
		panel.setLayout(null);

		contaCon = new ContaCon(nomeUsuario);
		contas = contaCon.buscar();

		extratoCon = new ExtratoCon(0);
		extratos = new ArrayList<ArrayList<Extrato>>();
		ArrayList<Extrato> extratosAux = new ArrayList<Extrato>();
		for (Conta conta : contas) {
			extratoCon.setIdConta(conta.getId());
			extratosAux = extratoCon.buscar();
			if (!extratosAux.isEmpty())
				extratos.add(extratosAux);
		}

		JLabel lblData = new JLabel("Data do Extrato:");
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblData.setBounds(20, 40, 80, 20);
		panel.add(lblData);

		JLabel lblMes = new JLabel("Mês");
		lblMes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMes.setBounds(220, 40, 30, 20);
		panel.add(lblMes);

		DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<Object>();
		Extrato extratoAux;
		String mes;
		if (!extratos.isEmpty())
			for (int i = 0; i < extratos.get(0).size(); i++) {
				extratoAux = extratos.get(0).get(i);
				if (extratos.get(0).get(0).getAno() == extratoAux.getAno()) {
					mes = NomesDeMes.getNome(extratoAux.getMes());
					if (modelo.getIndexOf(mes) == -1)
						modelo.addElement(mes);
				}
			}

		cbMes = new JComboBox<Object>(modelo);
		cbMes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbMes.setBounds(260, 40, 100, 20);
		panel.add(cbMes);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAno.setBounds(110, 40, 30, 20);
		panel.add(lblAno);

		modelo = new DefaultComboBoxModel<Object>();
		if (!extratos.isEmpty())
			for (int i = 0; i < extratos.get(0).size(); i++) {
				extratoAux = extratos.get(0).get(i);
				if (modelo.getIndexOf(extratoAux.getAno()) == -1)
					modelo.addElement(extratoAux.getAno());
			}

		cbAno = new JComboBox<Object>(modelo);
		cbAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<Object>();
				cbMes.removeAllItems();
				if (cbAno.getSelectedIndex() == -1)
					return;
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux;
				String mes;
				int index = contas.indexOf(contaAux);
				if (!extratos.isEmpty())
					for (int i = 0; i < extratos.get(index).size(); i++) {
						extratoAux = extratos.get(index).get(i);
						if (cbAno.getSelectedItem().toString().equals(extratoAux.getAno())) {
							mes = NomesDeMes.getNome(extratoAux.getMes());
							if (modelo.getIndexOf(mes) == -1)
								modelo.addElement(mes);
						}
					}
				cbMes.setModel(modelo);
			}
		});
		cbAno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbAno.setBounds(150, 40, 60, 20);
		panel.add(cbAno);

		JLabel lblConta = new JLabel("Conta");
		lblConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblConta.setBounds(305, 10, 35, 20);
		panel.add(lblConta);

		modelo = new DefaultComboBoxModel<Object>();
		Conta contaAux;
		for (int i = 0; i < contas.size(); i++) {
			contaAux = contas.get(i);
			if (contas.get(0).getAgencia().equals(contaAux.getAgencia()))
				if (modelo.getIndexOf(contaAux.getNumero()) == -1)
					modelo.addElement(contaAux.getNumero());
		}

		cbNumConta = new JComboBox<Object>(modelo);
		cbNumConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<Object>();
				DefaultComboBoxModel<Object> modeloAux = new DefaultComboBoxModel<Object>();
				cbAno.removeAllItems();
				cbMes.removeAllItems();
				if (cbNumConta.getSelectedIndex() == -1)
					return;
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux;
				String mes;
				int anoInicial = 0;
				int index = contas.indexOf(contaAux);
				if (cbNumConta.getSelectedItem().toString().equals(contaAux.getNumero())) {
					if (!extratos.isEmpty())
						for (int j = 0; j < extratos.get(index).size(); j++) {
							extratoAux = extratos.get(index).get(j);
							if (modelo.getSize() == 0)
								anoInicial = extratoAux.getAno();
							if (modelo.getIndexOf(extratoAux.getAno()) == -1)
								modelo.addElement(extratoAux.getAno());
							if (anoInicial == extratoAux.getAno()) {
								mes = NomesDeMes.getNome(extratoAux.getMes());
								if (modeloAux.getIndexOf(mes) == -1)
									modeloAux.addElement(mes);
							}
						}
				}
				cbMes.setModel(modeloAux);
				cbAno.setModel(modelo);
			}
		});
		cbNumConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbNumConta.setBounds(350, 10, 70, 20);
		panel.add(cbNumConta);

		JLabel lblAgencia = new JLabel("Agencia");
		lblAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAgencia.setBounds(180, 10, 45, 20);
		panel.add(lblAgencia);

		modelo = new DefaultComboBoxModel<Object>();
		for (int i = 0; i < contas.size(); i++) {
			contaAux = contas.get(i);
			if (contas.get(0).getBanco().equals(contaAux.getBanco()))
				if (modelo.getIndexOf(contaAux.getAgencia()) == -1)
					modelo.addElement(contaAux.getAgencia());
		}

		cbAgencia = new JComboBox<Object>(modelo);
		cbAgencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<DefaultComboBoxModel<Object>> modelos = new ArrayList<DefaultComboBoxModel<Object>>();
				for (int i = 0; i < 3; i++) {
					modelos.add(new DefaultComboBoxModel<Object>());
				}
				cbNumConta.removeAllItems();
				cbAno.removeAllItems();
				cbMes.removeAllItems();
				if (cbAgencia.getSelectedIndex() == -1)
					return;
				Conta contaAux;
				Extrato extratoAux;
				String mes;
				int anoInicial = 0;
				int index;
				for (int i = 0; i < contas.size(); i++) {
					contaAux = contas.get(i);
					if (cbAgencia.getSelectedItem().toString().equals(contaAux.getAgencia())) {
						if (modelos.get(0).getSize() == 0) {
							index = contas.indexOf(contaAux);
							if (!extratos.isEmpty())
								for (int j = 0; j < extratos.get(index).size(); j++) {
									extratoAux = extratos.get(index).get(j);
									if (modelos.get(1).getSize() == 0)
										anoInicial = extratoAux.getAno();
									if (modelos.get(1).getIndexOf(extratoAux.getAno()) == -1)
										modelos.get(1).addElement(extratoAux.getAno());
									if (anoInicial == extratoAux.getAno()) {
										mes = NomesDeMes.getNome(extratoAux.getMes());
										if (modelos.get(2).getIndexOf(mes) == -1)
											modelos.get(2).addElement(mes);
									}
								}
						}
						if (modelos.get(0).getIndexOf(contaAux.getNumero()) == -1)
							modelos.get(0).addElement(contaAux.getNumero());
					}
				}
				cbMes.setModel(modelos.get(2));
				cbAno.setModel(modelos.get(1));
				cbNumConta.setModel(modelos.get(0));
			}
		});
		cbAgencia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbAgencia.setBounds(235, 10, 60, 20);
		panel.add(cbAgencia);

		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblBanco.setBounds(20, 10, 35, 20);
		panel.add(lblBanco);

		modelo = new DefaultComboBoxModel<Object>();
		for (int i = 0; i < contas.size(); i++) {
			contaAux = contas.get(i);
			if (modelo.getIndexOf(contaAux.getBanco()) == -1)
				modelo.addElement(contaAux.getBanco());
		}

		cbBanco = new JComboBox<Object>(modelo);
		cbBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<DefaultComboBoxModel<Object>> modelos = new ArrayList<DefaultComboBoxModel<Object>>();
				for (int i = 0; i < 4; i++) {
					modelos.add(new DefaultComboBoxModel<Object>());
				}
				cbAgencia.removeAllItems();
				cbNumConta.removeAllItems();
				cbAno.removeAllItems();
				cbMes.removeAllItems();
				if (cbBanco.getSelectedIndex() == -1)
					return;
				String agenciaInicial = "";
				Conta contaAux;
				Extrato extratoAux;
				String mes;
				int anoInicial = 0;
				int index;
				for (int i = 0; i < contas.size(); i++) {
					contaAux = contas.get(i);
					if (cbBanco.getSelectedItem().toString().equals(contaAux.getBanco())) {
						if (modelos.get(0).getSize() == 0) {
							agenciaInicial = contaAux.getAgencia();
						}
						if (modelos.get(0).getIndexOf(contaAux.getAgencia()) == -1)
							modelos.get(0).addElement(contaAux.getAgencia());
						if (modelos.get(1).getSize() == 0) {
							index = contas.indexOf(contaAux);
							if (!extratos.isEmpty())
								for (int j = 0; j < extratos.get(index).size(); j++) {
									extratoAux = extratos.get(index).get(j);
									if (modelos.get(2).getSize() == 0)
										anoInicial = extratoAux.getAno();
									if (modelos.get(2).getIndexOf(extratoAux.getAno()) == -1)
										modelos.get(2).addElement(extratoAux.getAno());
									if (anoInicial == extratoAux.getAno()) {
										mes = NomesDeMes.getNome(extratoAux.getMes());
										if (modelos.get(3).getIndexOf(mes) == -1)
											modelos.get(3).addElement(mes);
									}
								}
						}
						if (agenciaInicial.equals(contaAux.getAgencia()))
							if (modelos.get(1).getIndexOf(contaAux.getNumero()) == -1)
								modelos.get(1).addElement(contaAux.getNumero());
					}
				}
				cbMes.setModel(modelos.get(3));
				cbAno.setModel(modelos.get(2));
				cbNumConta.setModel(modelos.get(1));
				cbAgencia.setModel(modelos.get(0));
			}
		});
		cbBanco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbBanco.setBounds(65, 10, 105, 20);
		panel.add(cbBanco);

		JMenuBar mnbConta = new JMenuBar();
		mnbConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnbConta.setBounds(370, 40, 50, 20);
		panel.add(mnbConta);

		JMenu mnConta = new JMenu("Opções");
		mnConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnbConta.add(mnConta);

		final TelaExtrato tela = this;

		JMenuItem mntmCadastrarConta = new JMenuItem("Cadastrar Conta");
		mntmCadastrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaCadastroConta(tela, nomeUsuario);
			}
		});
		mntmCadastrarConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnConta.add(mntmCadastrarConta);

		JMenuItem mntmAtualizarConta = new JMenuItem("Atualizar Conta");
		mntmAtualizarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				new TelaAtualizacaoConta(tela, nomeUsuario, contaAux);
			}
		});
		mntmAtualizarConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mnConta.add(mntmAtualizarConta);

		listaDosItensDeExtrato = new ArrayList<ItemDeExtrato>();
		modeloDeTabela = new DefaultTableModel();
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if (!extratos.isEmpty() && !extratos.get(0).isEmpty()) {
					Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
							cbNumConta.getSelectedItem().toString(), 0);
					Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
							Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
					extratoCon.setIdConta(contaAux.getId());
					listaDosItensDeExtrato = extratoCon.gerarExtrato(extratoAux.getId());
				}
				
				for (int i = 0; i < modeloDeTabela.getRowCount(); i++) {
					modeloDeTabela.removeRow(i);
				}
				String[] dados = new String[4];
				for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
					dados[0] = Integer.toString(listaDosItensDeExtrato.get(i).getDia());
					dados[1] = listaDosItensDeExtrato.get(i).getTitulo();
					dados[2] = listaDosItensDeExtrato.get(i).getTipo().toString();
					dados[3] = Float.toString(listaDosItensDeExtrato.get(i).getValor());
					modeloDeTabela.addRow(dados);
				}
			}
		});
		btnListar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnListar.setBounds(190, 330, 80, 20);
		panel.add(btnListar);

		JButton btnCriarItemDeExtrato = new JButton("Criar Item de Extrato");
		btnCriarItemDeExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				new TelaCadastroItemDeExtrato(tela, nomeUsuario, contaAux.getId());
			}
		});
		btnCriarItemDeExtrato.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCriarItemDeExtrato.setBounds(20, 330, 140, 20);
		panel.add(btnCriarItemDeExtrato);

		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!extratos.isEmpty() && !extratos.get(0).isEmpty()) {
					Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(),
							cbAgencia.getSelectedItem().toString(), cbNumConta.getSelectedItem().toString(), 0);
					Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
							Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
					new TelaRelatorio(extratoAux, contaAux.getId());
				}
			}
		});
		btnGerarRelatorio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGerarRelatorio.setBounds(300, 330, 120, 20);
		panel.add(btnGerarRelatorio);

		JPanel panelInterno = new JPanel();
		panelInterno.setBackground(Color.GRAY);
		panelInterno.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInterno.setBounds(20, 70, 400, 250);
		panel.add(panelInterno);
		panelInterno.setLayout(null);

		JLabel lblTituloDoPainel = new JLabel("Lista das Operações do Extrato");
		lblTituloDoPainel.setBounds(5, 3, 150, 20);
		lblTituloDoPainel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblTituloDoPainel);

		JLabel lblSaldoInicial = new JLabel("Saldo Inicial");
		lblSaldoInicial.setBounds(250, 3, 60, 20);
		lblSaldoInicial.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblSaldoInicial);

		txtSaldoInicial = new JTextField();
		if (!extratos.isEmpty() && !extratos.get(0).isEmpty())
			txtSaldoInicial.setText(Float.toString(extratos.get(0).get(0).getValorInicial()));
		txtSaldoInicial.setEditable(false);
		txtSaldoInicial.setBounds(320, 3, 70, 20);
		panelInterno.add(txtSaldoInicial);
		txtSaldoInicial.setColumns(10);

		if (!extratos.isEmpty() && !extratos.get(0).isEmpty()) {
			contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
					cbNumConta.getSelectedItem().toString(), 0);
			extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
					Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
			extratoCon.setIdConta(contaAux.getId());
			listaDosItensDeExtrato = extratoCon.gerarExtrato(extratoAux.getId());
		}

		String[] cabecalho = { "Dia", "Titulo", "Tipo", "Valor" };	
		modeloDeTabela.setColumnIdentifiers(cabecalho);
		
		String[] dados = new String[4];
		for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
			dados[0] = Integer.toString(listaDosItensDeExtrato.get(i).getDia());
			dados[1] = listaDosItensDeExtrato.get(i).getTitulo();
			dados[2] = listaDosItensDeExtrato.get(i).getTipo().toString();
			dados[3] = Float.toString(listaDosItensDeExtrato.get(i).getValor());
			modeloDeTabela.addRow(dados);
		}
		
		tabela = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
		};
		tabela.setSize(400, 200);
		tabela.setLocation(0, 25);
		tabela.setFillsViewportHeight(true);
		tabela.setModel(modeloDeTabela);

		JScrollPane painelDeRolagem = new JScrollPane(tabela);
		painelDeRolagem.setViewportBorder(null);
		painelDeRolagem.setSize(396, 200);
		painelDeRolagem.setLocation(2, 25);
		panelInterno.add(painelDeRolagem);

		ListSelectionModel modeloDeSelecao = tabela.getSelectionModel();
		modeloDeSelecao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloDeSelecao.addListSelectionListener(new LeitorDeLinha(this, nomeUsuario));

		JLabel lblSaldoFinal = new JLabel("Saldo Final");
		lblSaldoFinal.setBounds(250, 227, 60, 20);
		lblSaldoFinal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblSaldoFinal);

		txtSaldoFinal = new JTextField();
		if (!extratos.isEmpty() && !extratos.get(0).isEmpty())
			txtSaldoFinal.setText(Float.toString(extratos.get(0).get(0).getValorFinal()));
		txtSaldoFinal.setEditable(false);
		txtSaldoFinal.setBounds(320, 227, 70, 20);
		panelInterno.add(txtSaldoFinal);
		txtSaldoFinal.setColumns(10);
	}

	class LeitorDeLinha implements ListSelectionListener {
		private JTable tabela;
		private TelaExtrato tela;
		private String nomeUsuario;

		public LeitorDeLinha(TelaExtrato tela, String nomeUsuario) {
			this.tela = tela;
			this.tabela = tela.tabela;
			this.nomeUsuario = nomeUsuario;
		}

		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel modelo = tabela.getSelectionModel();
				int linha = modelo.getLeadSelectionIndex();

				if (contas.isEmpty())
					return;
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
						Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
				ItemDeExtrato itemAux = new ItemDeExtrato(tabela.getValueAt(linha, 1).toString(),
						Float.parseFloat(tabela.getValueAt(linha, 3).toString()), "",
						Integer.parseInt(tabela.getValueAt(linha, 0).toString()), 1,
						TipoItemDeExtrato.valueOf(tabela.getValueAt(linha, 2).toString()), extratoAux.getId(), "");
				for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
					if (itemAux.getId() == listaDosItensDeExtrato.get(i).getId())
						itemAux.setCategoria(listaDosItensDeExtrato.get(i).getCategoria());
				}
				new TelaAtualizacaoItemDeExtrato(tela, nomeUsuario, contaAux.getId(), extratoAux, itemAux);
			}
		}
	}
}
