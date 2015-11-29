package boundaries.item_de_extrato;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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

import boundaries.TelaExtrato;
import controllers.CategoriaCon;
import controllers.ExtratoCon;
import entities.Categoria;
import entities.Extrato;
import entities.ItemDeExtrato;
import utilities.TipoItemDeExtrato;

//Ambiente de atualização do item do extrato
public class TelaAtualizacaoItemDeExtrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtOcorrencia;
	private JTextField txtValor;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAno;
	private JTextField txtObservacoes;
	private CategoriaCon categoriaCon;
	private ExtratoCon extratoCon;
	private List<Categoria> categorias;

	public TelaAtualizacaoItemDeExtrato(final TelaExtrato tela, final String nomeUsuario, final long idConta,
			final Extrato extratoAnt, final ItemDeExtrato itemDeExtratoAnt) {
		setTitle("Finan\u00E7as Pessoais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		JLabel lblAtualizarItem = new JLabel("Atualizar Item de Extrato");
		lblAtualizarItem.setBounds(17, 10, 280, 20);
		lblAtualizarItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizarItem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblAtualizarItem);

		JPanel panel = new JPanel();
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
		txtTitulo.setText(itemDeExtratoAnt.getTitulo());

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
		txtOcorrencia.setText(Integer.toString(itemDeExtratoAnt.getOcorrencia()));

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblValor.setBounds(20, 50, 40, 20);
		panel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtValor.setBounds(70, 50, 100, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);
		txtValor.setText(Float.toString(itemDeExtratoAnt.getValor()));

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCategoria.setBounds(20, 80, 50, 20);
		panel.add(lblCategoria);

		categoriaCon = new CategoriaCon(nomeUsuario);
		categorias = categoriaCon.buscar();

		DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<Object>();
		Categoria categoriaAux;
		int index = -1;
		for (int i = 0; i < categorias.size(); i++) {
			categoriaAux = categorias.get(i);
			if(itemDeExtratoAnt.getCategoria().equals(categoriaAux.getNome()))
				index = i;
			if (modelo.getIndexOf(categoriaAux.getNome()) == -1)
				modelo.addElement(categoriaAux.getNome());
		}

		final JComboBox<Object> cbCategoria = new JComboBox<Object>(modelo);
		cbCategoria.setEditable(true);
		cbCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbCategoria.setBounds(70, 80, 100, 20);
		cbCategoria.setSelectedIndex(index);
		panel.add(cbCategoria);

		categoriaCon = new CategoriaCon(nomeUsuario);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbCategoria.getSelectedItem() == null)
					return;
				String categoria = cbCategoria.getSelectedItem().toString();
				Object opcoes[] = { "Atualizar", "Remover" };
				int resposta = JOptionPane.showOptionDialog(null,
						"O que deseja fazer com a categoria " + categoria + "?", "Atualizar Categoria",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
				if (resposta == JOptionPane.NO_OPTION) {
					if (categoriaCon.remover(categoria))
						JOptionPane.showMessageDialog(null, "A categoria foi removida com sucesso");
					else
						JOptionPane.showMessageDialog(null, "A categoria não foi removida com sucesso");
				}
				if (resposta == JOptionPane.YES_OPTION) {
					String novaCategoria = JOptionPane.showInputDialog(null,
							"Qual será o novo nome da categoria " + categoria + "?", "Atualizar Categoria",
							JOptionPane.PLAIN_MESSAGE);
					if (categoriaCon.atualizar(categoria, new Categoria(novaCategoria)))
						JOptionPane.showMessageDialog(null, "A categoria foi atualizada com sucesso");
					else
						JOptionPane.showMessageDialog(null, "A categoria não foi atualizada com sucesso");
				}
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
		txtDia.setText(Integer.toString(itemDeExtratoAnt.getDia()));

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
		txtMes.setText(Integer.toString(extratoAnt.getMes()));

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
		txtAno.setText(Integer.toString(extratoAnt.getAno()));

		txtObservacoes = new JTextField();
		txtObservacoes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtObservacoes.setText("Observa\u00E7\u00F5es");
		txtObservacoes.setBounds(20, 140, 240, 20);
		panel.add(txtObservacoes);
		txtObservacoes.setColumns(10);
		txtObservacoes.setText(itemDeExtratoAnt.getObservacao());

		final String[] tiposDeTransacao = { "Receita", "Despesa"};

		final JComboBox<Object> cbTipo = new JComboBox<Object>(tiposDeTransacao);
		cbTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbTipo.setBounds(20, 170, 70, 20);
		if(itemDeExtratoAnt.getTipo().toString().equals(tiposDeTransacao[0].toLowerCase()))
			cbTipo.setSelectedIndex(0);
		else
			cbTipo.setSelectedIndex(1);
		panel.add(cbTipo);

		extratoCon = new ExtratoCon(idConta);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = txtTitulo.getText();
				if (titulo.equals("")) {
					JOptionPane.showMessageDialog(null, "Insira um titulo para o item.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int ocorrencia = Integer.parseInt(txtOcorrencia.getText());
				if (txtOcorrencia.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o numero de parcelas.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				float valor = Float.parseFloat(txtValor.getText());
				if (txtValor.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira um valor para o item.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String categoria = cbCategoria.getSelectedItem().toString();
				categoriaCon.cadastrar(new Categoria(categoria));

				int dia = Integer.parseInt(txtDia.getText());
				if (txtDia.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o dia", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int mes = Integer.parseInt(txtMes.getText());
				if (txtMes.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o mes", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int ano = Integer.parseInt(txtAno.getText());
				if (txtAno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o ano", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String observacao = txtObservacoes.getText();

				String tipo = cbTipo.getSelectedItem().toString();

				Extrato extrato = new Extrato(mes, ano, 0.0f, 0.0f, idConta);
				ItemDeExtrato itemDeExtrato = new ItemDeExtrato(titulo, valor, observacao, dia, ocorrencia,
						TipoItemDeExtrato.valueOf(tipo.toLowerCase()), extrato.getId(), categoria);				
				if (extratoCon.atualizar(extratoAnt.getId(), extrato, itemDeExtratoAnt.getId(), itemDeExtrato))
					JOptionPane.showMessageDialog(null, "O item de extrato foi atualizado com sucesso");
				else
					JOptionPane.showMessageDialog(null, "O item de extrato não foi atualizado");
				dispose();
			}
		});
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBounds(100, 170, 70, 20);
		panel.add(btnSalvar);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (extratoCon.remover(extratoAnt.getId(), itemDeExtratoAnt.getId())) {
					JOptionPane.showMessageDialog(null, "O item de extrato foi removido com sucesso");
					tela.dispose();
					new TelaExtrato(nomeUsuario);
				} else
					JOptionPane.showMessageDialog(null, "A item de extrato não foi removido");

				dispose();
			}
		});
		btnApagar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnApagar.setBounds(180, 170, 80, 20);
		panel.add(btnApagar);
	}
}
