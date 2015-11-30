package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
import controllers.ExtratoCon;
import entities.Conta;
import entities.Extrato;
import entities.ItemDeExtrato;
import utilities.NomesDeMes;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class TelaExtrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Object> cbBanco;
	private JComboBox<Object> cbAgencia;
	private JComboBox<Object> cbNumConta;
	private JComboBox<Object> cbAno;
	private JComboBox<Object> cbMes;
	private JList<Object> lstItensDeExtrato;
	private ContaCon contaCon;
	private ExtratoCon extratoCon;
	private List<Conta> contas;
	private List<ArrayList<Extrato>> extratos;
	private List<ItemDeExtrato> listaDosItensDeExtrato;
	private DefaultListModel<Object> modeloDeLista;
	private JTextField txtSaldoInicial;
	private JTextField txtSaldoFinal;

	public TelaExtrato(final String nomeUsuario) {
		setTitle("Finan�as Pessoais");
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
			if (extratosAux.isEmpty())
				extratos.add(extratosAux);
		}

		JLabel lblData = new JLabel("Data do Extrato:");
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblData.setBounds(20, 40, 85, 20);
		panel.add(lblData);

		JLabel lblMes = new JLabel("M�s");
		lblMes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMes.setBounds(225, 40, 30, 20);
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
		cbMes.setBounds(265, 40, 100, 20);
		panel.add(cbMes);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAno.setBounds(115, 40, 30, 20);
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
		cbAno.setBounds(155, 40, 60, 20);
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
		mnbConta.setBounds(375, 40, 45, 20);
		panel.add(mnbConta);

		JMenu mnConta = new JMenu("Opi��es");
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

		modeloDeLista = new DefaultListModel<Object>();
		if (!extratos.get(0).isEmpty()) {
			contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
					cbNumConta.getSelectedItem().toString(), 0);
			extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
					Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
			extratoCon.setIdConta(contaAux.getId());
			listaDosItensDeExtrato = extratoCon.gerarExtrato(extratoAux.getId());
			for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
				modeloDeLista.addElement("<html><pre>" + listaDosItensDeExtrato.get(i).getTitulo() + "</pre></html>");
			}
		}

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modeloDeLista.removeAllElements();
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
						Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
				extratoCon.setIdConta(contaAux.getId());
				listaDosItensDeExtrato = extratoCon.gerarExtrato(extratoAux.getId());
				for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
					modeloDeLista.addElement(listaDosItensDeExtrato.get(i).getTitulo());
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

		JButton btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
						Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
				new TelaRelatorio(extratoAux, contaAux.getId());
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

		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(30, 3, 30, 20);
		lblDia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblDia);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(80, 0, 1, 325);
		panelInterno.add(separator);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(140, 3, 30, 20);
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblTitulo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(230, 0, 1, 300);
		panelInterno.add(separator_1);

		JLabel lblSaldoInicial = new JLabel("Saldo Inicial");
		lblSaldoInicial.setBounds(250, 3, 60, 20);
		lblSaldoInicial.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblSaldoInicial);

		txtSaldoInicial = new JTextField();
		txtSaldoInicial.setEditable(false);
		txtSaldoInicial.setBounds(320, 3, 70, 20);
		panelInterno.add(txtSaldoInicial);
		txtSaldoInicial.setColumns(10);

		JLabel lblSaldoFinal = new JLabel("Saldo Final");
		lblSaldoFinal.setBounds(250, 227, 60, 20);
		lblSaldoFinal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panelInterno.add(lblSaldoFinal);

		lstItensDeExtrato = new JList<Object>(modeloDeLista);
		lstItensDeExtrato.setBounds(0, 25, 400, 200);
		panelInterno.add(lstItensDeExtrato);
		lstItensDeExtrato.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstItensDeExtrato.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
						Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
				ItemDeExtrato itemAux = null;
				for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
					if (lstItensDeExtrato.getSelectedValue().toString()
							.equals(listaDosItensDeExtrato.get(i).getTitulo()))
						itemAux = listaDosItensDeExtrato.get(i);
				}
				new TelaAtualizacaoItemDeExtrato(tela, nomeUsuario, contaAux.getId(), extratoAux, itemAux);
			}
		});
		lstItensDeExtrato.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtSaldoFinal = new JTextField();
		txtSaldoFinal.setEditable(false);
		txtSaldoFinal.setBounds(320, 227, 70, 20);
		panelInterno.add(txtSaldoFinal);
		txtSaldoFinal.setColumns(10);
	}
}
