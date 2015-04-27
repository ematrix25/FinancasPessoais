package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblListaDeTransacoes = new JLabel("Lista de Transa\u00E7\u00F5es");
		lblListaDeTransacoes.setBounds(50, 10, 400, 20);
		lblListaDeTransacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeTransacoes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblListaDeTransacoes);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(50, 50, 400, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFiltro.setBounds(20, 20, 40, 20);
		panel.add(lblFiltro);
		
		List<String> listaCategorias = new ArrayList<String>();
		listaCategorias.add("(Todas as Categorias)");
		listaCategorias.add("Viagem");
		JComboBox<Object> cbFiltro = new JComboBox<Object>(listaCategorias.toArray());
		cbFiltro.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbFiltro.setBounds(70, 20, 150, 20);
		cbFiltro.setSelectedIndex(0);
		panel.add(cbFiltro);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnListar.setBounds(300, 20, 80, 20);
		panel.add(btnListar);
		
		final JToggleButton tglbtnRemover = new JToggleButton("Remover");
		tglbtnRemover.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tglbtnRemover.setBounds(280, 270, 100, 20);
		panel.add(tglbtnRemover);
		
		//Essas transacoes serão buscadas atraves do comboBox das categorias
		List<String> listaTransacoes = new ArrayList<String>();
		listaTransacoes.add("IPTU");
		listaTransacoes.add("Pensão");
		listaTransacoes.add("Passagem para Miami");
		listaTransacoes.add("Playstation 4");
		listaTransacoes.add("Noitada");
		listaTransacoes.add("Taxi");
		listaTransacoes.add("Lanche no Burger King");
		listaTransacoes.add("Taxa de AABB");
		final JList<Object> lstTransacoes = new JList<Object>(listaTransacoes.toArray());
		lstTransacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tglbtnRemover.isSelected())
					new RemoverTransacao(lstTransacoes.getSelectedValue());
				else
					new AtualizarTransacao(lstTransacoes.getSelectedValue());
			}
		});
		lstTransacoes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lstTransacoes.setBounds(20, 60, 360, 200);
		panel.add(lstTransacoes);
		
		JButton btnCriarTransacao = new JButton("Criar Transa\u00E7\u00E3o");
		btnCriarTransacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastrarTransacao();
			}
		});
		btnCriarTransacao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCriarTransacao.setBounds(20, 270, 120, 20);
		panel.add(btnCriarTransacao);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaRelatorio();
			}
		});
		btnRelatorio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRelatorio.setBounds(160, 270, 100, 20);
		panel.add(btnRelatorio);
	}
}
