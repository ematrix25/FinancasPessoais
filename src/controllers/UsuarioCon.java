package controllers;

import dao.UsuarioDAO;
import entities.Usuario;
import utilities.support.CorreioEletronico;
import utilities.support.Criptografia;

/**
 * @author Emanuel
 *
 */
public class UsuarioCon {

	private UsuarioDAO usuarioDAO;
	private Criptografia cripto;
	private String codigo;

	public UsuarioCon() {
		usuarioDAO = new UsuarioDAO();
		cripto = new Criptografia();
		codigo = "";
	}

	public boolean cadastrar(Usuario usuario) {
		String senhaCripto = cripto.criptografar(usuario.getSenha());
		usuario.setSenha(senhaCripto);
		return usuarioDAO.cadastrar(usuario);
	}

	public boolean autenticar(Usuario usuario) {
		String senha = usuarioDAO.buscar(usuario.getNome()).getSenha();
		return senha.equals(cripto.criptografar(usuario.getSenha()));
	}

	public boolean atualizar(Usuario usuario) {
		String codigo = usuario.getSenha().split("-")[0];		
		if(this.codigo.equals(codigo)) {
			String senha = usuario.getSenha().split("-")[1];
			senha = cripto.criptografar(senha);
			usuario.setSenha(senha);
		}
		return usuarioDAO.atualizar(usuario);
	}
	
	public boolean validarNome(String nome) {
		return Usuario.validateNome(nome);
	}
	
	public boolean validarSenha(String senha) {
		return Usuario.validateSenha(senha);
	}

	public boolean validarEmail(String email) {
		return Usuario.validateEmail(email);
	}

	private String gerarCodigo() {
		char[] caracteres = new char[4];
		for (int i = 0; i < caracteres.length; i++) {
			caracteres[i] = (char) (gerarNumero());
		}
		return new String(caracteres);
	}

	private int gerarNumero() {
		int num = 65 + (int) (Math.random() * (90-65));
		return num;
	}

	public boolean enviarEmail(String nome) {
		String email = usuarioDAO.buscar(nome).getEmail();
		if(email.equals(""))
			return false;
		codigo = gerarCodigo();
		CorreioEletronico ce = new CorreioEletronico();
		ce.enviar(email, codigo);
		return true;
	}
}
