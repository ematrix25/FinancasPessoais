package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilities.CorreioEletronico;
import utilities.Criptografia;
import dao.UsuarioDAO;
import entities.Usuario;

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
		return usuarioDAO.addUsuario(usuario);
	}

	public boolean autenticar(Usuario usuario) {
		String senha = usuarioDAO.getUsuario(usuario.getNome()).getSenha();
		return senha.equals(cripto.criptografar(usuario.getSenha()));
	}

	public boolean atualizar(Usuario usuario) {
		String codigo = usuario.getSenha().split("-")[0];		
		if(this.codigo.equals(codigo)) {
			String senha = usuario.getSenha().split("-")[1];
			senha = cripto.criptografar(senha);
			usuario.setSenha(senha);
		}
		return usuarioDAO.setUsuario(usuario);
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
		String email = usuarioDAO.getUsuario(nome).getEmail();
		if(email.equals(""))
			return false;
		codigo = gerarCodigo();
		CorreioEletronico ce = new CorreioEletronico();
		ce.enviar(email, codigo);
		return true;
	}

	public boolean validaEmail(String email) {
		if (email.equals(""))
			return false;
		String emailPattern = "\\b(^[_A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern regexPattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher regMatcher = regexPattern.matcher(email);
		return regMatcher.matches();
	}
}
