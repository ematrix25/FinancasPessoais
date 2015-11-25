package controllers;

import java.util.List;

import dao.CategoriaDAO;
import entities.Categoria;

/**
 * @author Danilo
 */
public class CategoriaCon {
	private CategoriaDAO categoriaDAO;
	
	public CategoriaCon(String nomeUsuario) {
		categoriaDAO = new CategoriaDAO(nomeUsuario);
	}
	
	public boolean cadastrar(Categoria categoria) {
		return categoriaDAO.cadastrar(categoria);
	}
	
	public List<Categoria> buscar() {
		return categoriaDAO.buscar();
	}
	
	public boolean atualizar(String idAntigo, Categoria categoria) {
		return categoriaDAO.atualizar(idAntigo, categoria);
	}
	
	public boolean remover(String nome) {
		return categoriaDAO.remover(nome);
	}	
}
