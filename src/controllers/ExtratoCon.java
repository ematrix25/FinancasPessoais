package controllers;

import java.util.List;

import dao.ExtratoDAO;
import dao.ItemDeExtratoDAO;
import entities.Extrato;
import entities.ItemDeExtrato;
import entities.TipoItemDeExtrato;

/**
 * @author Danilo
 */
public class ExtratoCon {
	private ExtratoDAO extratoDAO;
	private ItemDeExtratoDAO itemDeExtratoDAO;
	private float valorExtrato;

	public ExtratoCon(int idConta) {
		extratoDAO = new ExtratoDAO(idConta);
		itemDeExtratoDAO = new ItemDeExtratoDAO(0);
		valorExtrato = 0.0f;
	}

	private boolean atualizarValorFinal(Extrato extrato, float valor) {
		extrato.setValorFinal(extrato.getValorFinal() + valor);
		valorExtrato = extrato.getValorFinal();
		return extratoDAO.atualizar(extrato);
	}

	public boolean cadastrar(Extrato extrato, ItemDeExtrato itemDeExtrato, String categoria) {
		itemDeExtratoDAO.setIdExtrato(extrato.getId());
		extratoDAO.cadastrar(extrato);
		if (itemDeExtratoDAO.cadastrar(itemDeExtrato, categoria)) {
			float valor = 0.0f;
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.receita)
				valor += itemDeExtrato.getValor();
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.despesa)
				valor -= itemDeExtrato.getValor();
			return atualizarValorFinal(extrato, valor);
		}
		return false;
	}

	public List<Extrato> buscar() {
		return extratoDAO.buscar();
	}

	public boolean atualizar(int idAntExtrato, Extrato extrato, int idAntItemDeExtrato, float valorAnt,
			ItemDeExtrato itemDeExtrato, String idCategoria) {
		itemDeExtratoDAO.setIdExtrato(idAntExtrato);
		if (extrato.getId() != idAntExtrato) {
			extratoDAO.cadastrar(extrato);
		}
		if (itemDeExtratoDAO.atualizar(idAntItemDeExtrato, itemDeExtrato, idCategoria)) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(idAntExtrato);
			}
			if (itemDeExtrato.getValor() != valorAnt) {
				itemDeExtrato.setValor(itemDeExtrato.getValor() - valorAnt);
				return atualizarValorFinal(extrato, itemDeExtrato.getValor());
			}
		}
		return false;
	}

	public boolean remover(Extrato extrato, ItemDeExtrato itemDeExtrato) {
		itemDeExtratoDAO.setIdExtrato(extrato.getId());
		if (itemDeExtratoDAO.remover(itemDeExtrato.getId())) {
			if (itemDeExtratoDAO.buscar().size() == 0) {
				extratoDAO.remover(extrato.getId());
			}
			float valor = 0.0f;
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.receita)
				valor -= itemDeExtrato.getValor();
			if (itemDeExtrato.getTipo() == TipoItemDeExtrato.despesa)
				valor += itemDeExtrato.getValor();
			return atualizarValorFinal(extrato, valor);
		}
		return false;
	}

	//Trigger function já faz isso
	public boolean atualizarValores(Extrato extrato) {
		float valorDiferenca = extrato.getValorFinal() - extrato.getValorInicial();
		extrato.setValorInicial(valorExtrato);
		extrato.setValorFinal(valorExtrato + valorDiferenca);
		valorExtrato = extrato.getValorFinal();
		return extratoDAO.atualizar(extrato);
	}

	public static void main(String[] args) {
		ExtratoCon extratoCon = new ExtratoCon(2119118357);
		Extrato extrato = new Extrato(1, 2010, 0.0f, 0.0f, 0);
		ItemDeExtrato itemDeExtrato = new ItemDeExtrato("Contracheque", 1500.0f, "", 10, 1, TipoItemDeExtrato.receita, extrato.getId());

		System.out.println("Cadastro");
		System.out.println("idExtrato = " + extrato.getId());
		System.out.println("idItemDeExtrato = " + itemDeExtrato.getId());
		System.out.println();

		extratoCon.cadastrar(extrato, itemDeExtrato, "Salario");
		
		extrato.setMes(12);
		extrato.setAno(2009);
		itemDeExtrato.setIdExtrato(extrato.getId());
		
		extratoCon.cadastrar(extrato, itemDeExtrato, "Salario");

		/*try {
		    Thread.sleep(10000);          
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		extrato.setMes(2);
		extrato.setValorInicial(extrato.getValorFinal());
		itemDeExtrato.setValor(1300.0f);
		itemDeExtrato.setIdExtrato(extrato.getId());

		System.out.println("Atualizacao");
		System.out.println("idExtrato = " + extrato.getId());
		System.out.println("idItemDeExtrato = " + itemDeExtrato.getId());
		System.out.println();

		extratoCon.atualizar(47730563, extrato, -582511989, 1500.0f, itemDeExtrato, "Salario");*/

		/*try {
		    Thread.sleep(10000);          
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		extrato.setMes(3);
		extrato.setValorInicial(extrato.getValorFinal());
		itemDeExtrato.setValor(1400.0f);
		
		extratoCon.cadastrar(extrato, itemDeExtrato, "Salario");
		
		System.out.println("Remocao");
		System.out.println("idExtrato = " + 47655606);
		System.out.println("idItemDeExtrato = " + (-1783303041));
		System.out.println();

		extratoCon.remover(extrato, itemDeExtrato);*/
	}
}
