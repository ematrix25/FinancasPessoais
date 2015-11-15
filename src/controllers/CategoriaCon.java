package controllers;

import dao.CategoriaDAO;
import entities.Categoria;

/**
 * @author Danilo
 */
public class CategoriaCon {
	private CategoriaDAO categoriaDAO;
	
	public CategoriaCon() {
		categoriaDAO = new CategoriaDAO();
	}
	
	public boolean cadastrar(Categoria categoria) {
		return categoriaDAO.addCategoria(categoria);
	}
	
	public boolean atualizar(Categoria categoria) {
		return categoriaDAO.setCategoria(categoria);
	}
	
	public boolean remover(String nome) {
		return true;
	}
		
	
}
