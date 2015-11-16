package controllers;

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
		return categoriaDAO.adicionarCategoria(categoria);
	}
	
	public boolean atualizar(Categoria categoria) {
		return categoriaDAO.atualizarCategoria(categoria);
	}
	
	public boolean remover(String nome) {
		return categoriaDAO.deletarCategoria(nome);
	}
		
	
}
