package controllers;

import dao.ContaDAO;
import entities.Conta;

public class ContaCon {

	private ContaDAO contaDAO;

	public ContaCon(String nomeUsuario) {
		contaDAO = new ContaDAO(nomeUsuario);
	}

	public boolean cadastrar(Conta conta) {
		return contaDAO.addConta(conta);
	}

}
