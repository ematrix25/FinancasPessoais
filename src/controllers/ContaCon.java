package controllers;

import java.util.List;

import dao.ContaDAO;
import entities.Conta;

public class ContaCon {

	private ContaDAO contaDAO;

	public ContaCon(String nomeUsuario) {
		contaDAO = new ContaDAO(nomeUsuario);
	}
	
	public List<Conta> getContas() {
		return contaDAO.buscarContas();
	}

	public boolean cadastrar(Conta conta) {
		return contaDAO.adicionarConta(conta);
	}
	
	public boolean atualizar(int idAntigo, Conta conta) {
		return contaDAO.atualizarConta(idAntigo, conta);
	}
	
	public boolean remover(int id) {
		return contaDAO.deletarConta(id);
	}
}
