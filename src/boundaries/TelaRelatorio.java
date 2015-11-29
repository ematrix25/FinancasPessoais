package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.ExtratoCon;
import entities.Extrato;
import utilities.ItemDoRelatorio;
import utilities.TipoItemDeExtrato;

public class TelaRelatorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ExtratoCon extratoCon;

	/**
	 * Create the frame.
	 */
	public TelaRelatorio(Extrato extrato, long idConta) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		JLabel lblListaTransacoes = new JLabel("Relatório");
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

		extratoCon = new ExtratoCon(idConta);
		
		JLabel lblListaDeDespesas = new JLabel("");
		lblListaDeDespesas.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblListaDeDespesas.setOpaque(true);
		lblListaDeDespesas.setVerticalAlignment(SwingConstants.TOP);
		lblListaDeDespesas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblListaDeDespesas.setBounds(20, 50, 100, 120);
		panel.add(lblListaDeDespesas);
		lblListaDeDespesas.setText(formatarRelatorio(extratoCon.gerarRelatorio(TipoItemDeExtrato.despesa, extrato)));
		
		JLabel lblListaDeReceitas = new JLabel("");
		lblListaDeReceitas.setVerticalAlignment(SwingConstants.TOP);
		lblListaDeReceitas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblListaDeReceitas.setBounds(160, 50, 100, 120);
		lblListaDeReceitas.setBorder(BorderFactory.createLineBorder(Color.black));
		lblListaDeReceitas.setOpaque(true);
		panel.add(lblListaDeReceitas);
		lblListaDeReceitas.setText(formatarRelatorio(extratoCon.gerarRelatorio(TipoItemDeExtrato.receita, extrato)));
	}
	
	public String formatarRelatorio(List<ItemDoRelatorio> relatorio) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		for (int i = 0; i < relatorio.size(); i++) {
			sb.append(relatorio.get(i).toString());
			sb.append("<br>");
		}
		sb.append("</html>");
		return sb.toString();
	}
}
