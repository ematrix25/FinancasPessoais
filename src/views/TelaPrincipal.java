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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		JComboBox<Object> comboBox = new JComboBox<Object>(listaCategorias.toArray());
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBox.setBounds(70, 20, 150, 20);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnListar.setBounds(300, 20, 80, 20);
		panel.add(btnListar);
		
		List<String> listaTransacoes = new ArrayList<String>();
		listaTransacoes.add("IPTU");
		listaTransacoes.add("Pensão");
		listaTransacoes.add("Passagem para Miami");
		listaTransacoes.add("Playstation 4");
		listaTransacoes.add("Noitada");
		listaTransacoes.add("Taxi");
		listaTransacoes.add("Lanche no Burger King");
		listaTransacoes.add("Taxa dde AABB");
		final JList<Object> list = new JList<Object>(listaTransacoes.toArray());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AtualizarTransacao(list.getSelectedValue());
			}
		});
		list.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		list.setBounds(20, 60, 360, 200);
		panel.add(list);
	}
}
