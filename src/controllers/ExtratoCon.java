package controllers;

import java.util.List;
import dao.ExtratoDAO;
import entities.Extrato;

/**
 * @author Danilo
 */
public class ExtratoCon {
	private ExtratoDAO extratoDAO;
	
	public ExtratoCon(int idConta) {
		extratoDAO = new ExtratoDAO(idConta);
	}
	
	public boolean cadastrar(Extrato extrato) {
		return extratoDAO.cadastrar(extrato);
	}
	
	public List<Extrato> buscar() {
		return extratoDAO.buscar();
	}
	
	public boolean atualizar(int idAntigo, Extrato extrato) {
		return extratoDAO.atualizar(idAntigo, extrato);
	}
	
	public boolean remover(int id) {
		return extratoDAO.remover(id);
	}	
	
}
