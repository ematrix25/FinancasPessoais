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

		contaCon = new ContaCon(nomeUsuario);
		contas = contaCon.buscar();

		extratoCon = new ExtratoCon(0);
		extratos = new ArrayList<ArrayList<Extrato>>();
		for (Conta conta : contas) {
			extratoCon.setIdConta(conta.getId());
			extratos.add(extratoCon.buscar());
		}

		JLabel lblData = new JLabel("Data do Extrato:");
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblData.setBounds(20, 40, 85, 20);
		panel.add(lblData);

		JLabel lblMes = new JLabel("M\u00EAs");
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

		JMenu mnConta = new JMenu("Op\u00E7\u00F5es");
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
		contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
				cbNumConta.getSelectedItem().toString(), 0);
		extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
				Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
		extratoCon.setIdConta(contaAux.getId());
		listaDosItensDeExtrato = extratoCon.gerarExtrato(extratoAux.getId());
		for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
			System.out.println(listaDosItensDeExtrato.get(i));
			modeloDeLista.addElement(listaDosItensDeExtrato.get(i).getTitulo());
		}
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
						Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
				System.out.println(extratoAux);
				extratoCon.setIdConta(contaAux.getId());
				listaDosItensDeExtrato = extratoCon.gerarExtrato(extratoAux.getId());
				for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
					modeloDeLista.addElement(listaDosItensDeExtrato.get(i).getTitulo());
				}
			}
		});
		btnListar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnListar.setBounds(190, 280, 80, 20);
		panel.add(btnListar);

		lstItensDeExtrato = new JList<Object>(modeloDeLista);
		lstItensDeExtrato.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstItensDeExtrato.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				Extrato extratoAux = new Extrato(NomesDeMes.getNumero(cbMes.getSelectedItem().toString()),
						Integer.parseInt(cbAno.getSelectedItem().toString()), 0.0f, 0.0f, contaAux.getId());
				ItemDeExtrato itemAux = null;
				for (int i = 0; i < listaDosItensDeExtrato.size(); i++) {
					if (lstItensDeExtrato.getSelectedValue().toString().equals(listaDosItensDeExtrato.get(i).getTitulo()))
						itemAux = listaDosItensDeExtrato.get(i);
				}
				new TelaAtualizacaoItemDeExtrato(tela, nomeUsuario, contaAux.getId(), extratoAux, itemAux);
			}
		});
		lstItensDeExtrato.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lstItensDeExtrato.setBounds(20, 70, 400, 200);
		panel.add(lstItensDeExtrato);

		JButton btnCriarItemDeExtrato = new JButton("Criar Item de Extrato");
		btnCriarItemDeExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta contaAux = new Conta(cbBanco.getSelectedItem().toString(), cbAgencia.getSelectedItem().toString(),
						cbNumConta.getSelectedItem().toString(), 0);
				new TelaCadastroItemDeExtrato(tela, nomeUsuario, contaAux.getId());
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
