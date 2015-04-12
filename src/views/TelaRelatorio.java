package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TelaRelatorio extends JFrame {

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
					new TelaRelatorio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaRelatorio() {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		JLabel lblListaTransacoes = new JLabel("Relatório das Transações");
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

		JLabel lblDespesas = new JLabel("Despesas");
		lblDespesas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDespesas.setBounds(20, 20, 50, 20);
		panel.add(lblDespesas);

		JLabel lblReceitas = new JLabel("Receitas");
		lblReceitas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblReceitas.setBounds(160, 20, 50, 20);
		panel.add(lblReceitas);

		// Será uma lista de categorias com os respectivos tipo
		List<String> listaCategorias = new ArrayList<String>();
		listaCategorias.add("Viagem");
		listaCategorias.add("Salário");
		listaCategorias.add("Transporte");
		listaCategorias.add("Hora Extra");
		listaCategorias.add("Poupança");
		listaCategorias.add("Aluguel");

		JLabel lblListaDeDespesas = new JLabel("");
		lblListaDeDespesas.setVerticalAlignment(SwingConstants.TOP);
		lblListaDeDespesas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblListaDeDespesas.setBounds(20, 50, 100, 120);
		panel.add(lblListaDeDespesas);
		lblListaDeDespesas.setText(addDespesas(listaCategorias));

		JLabel lblListaDeReceitas = new JLabel("");
		lblListaDeReceitas.setVerticalAlignment(SwingConstants.TOP);
		lblListaDeReceitas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblListaDeReceitas.setBounds(160, 50, 100, 120);
		panel.add(lblListaDeReceitas);
		lblListaDeReceitas.setText(addReceitas(listaCategorias));
	}

	public String addDespesas(List<String> categorias) {
		// O calculo das despesas substituirá esse cálculo aleatório
		StringBuffer sb = new StringBuffer();
		float aux[] = calcPorcentagens();
		sb.append("<html>");
		for (int i = 0; i < categorias.size(); i += 2) {
			sb.append(categorias.get(i));
			sb.append(": ");
			sb.append(aux[ i / 2 ]);
			sb.append("%<br>");
		}
		sb.append("</html>");
		return sb.toString();
	}
		
	public String addReceitas(List<String> categorias) {
		// O calculo das receitas substituirá esse cálculo aleatório
		StringBuffer sb = new StringBuffer();
		float aux[] = calcPorcentagens();
		sb.append("<html>");
		for (int i = 1; i < categorias.size(); i += 2) {
			sb.append(categorias.get(i));
			sb.append(": ");
			sb.append(aux[(i - 1) / 2]);
			sb.append("%<br>");
		}
		sb.append("</html>");
		return sb.toString();
	}

	public float[] calcPorcentagens() {
		int parcela = 100;
		float aux[] = new float[3];
		for (int i = 0; i < 3; i++) {
			aux[i] = new Random().nextInt((int) (parcela / (i + 3))) + (parcela / (i + 2));
			parcela -= aux[i];
		}
		aux[0] += parcela;
		return aux;
	}
}
