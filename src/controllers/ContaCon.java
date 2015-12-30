package controllers;

import java.util.List;

import dao.ContaDAO;
import entities.Conta;

public class ContaCon {

	private ContaDAO contaDAO;

	public ContaCon(String nomeUsuario) {
		contaDAO = new ContaDAO(nomeUsuario);
	}

	public boolean cadastrar(Conta conta) {
		return contaDAO.cadastrar(conta);
	}

	public List<Conta> buscar() {
		return contaDAO.buscar();
	}

	public boolean atualizar(long idAntigo, Conta conta) {
		return contaDAO.atualizar(idAntigo, conta);
	}

	public boolean validarBanco(String banco) {
		return Conta.validateBanco(banco);
	}

	public boolean validarAgencia(String agencia) {
		return Conta.validateAgencia(agencia);
	}

	public boolean validarNumero(String numero) {
		return Conta.validateNumero(numero);
	}

	public boolean remover(long id) {
		return contaDAO.remover(id);
	}
}
