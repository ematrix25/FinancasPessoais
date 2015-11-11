package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UsuarioDAO;
import entities.Usuario;

/**
 * @author Emanuel
 *
 */
public class UsuarioCon {

	private UsuarioDAO usuarioDAO;

	public UsuarioCon() {
		usuarioDAO = new UsuarioDAO();
	}

	public boolean cadastrar(Usuario usuario) {
		return usuarioDAO.addUsuario(usuario);
	}

	public Usuario getFuncionario(String nome) {
		return usuarioDAO.getUsuario(nome);
	}

	public boolean atualizar(Usuario usuario) {
		return usuarioDAO.setUsuario(usuario);
	}
	
	public boolean validaEmail(String email) {
		if(email.equals("")) 
			return false;
		String emailPattern = "\\b(^[_A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern regexPattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher regMatcher = regexPattern.matcher(email);	    
		return regMatcher.matches();		
	}
}
