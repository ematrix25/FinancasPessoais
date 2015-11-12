package utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Emanuel
 *
 */
public class CorreioEletronico {
	private String remetente;
	private String senha;
	private String hospedeiro;

	public CorreioEletronico() {
		this.remetente = "financaspessoais25@gmail.com";
		this.senha = "finpes25";
		this.hospedeiro = "smtp.gmail.com";
	}

	public void enviar(String destinatario, String codigo) {
		Properties propriedades = System.getProperties();
		propriedades.put("mail.smtp.host", hospedeiro);
		propriedades.put("mail.smtp.starttls.enable", "true");
		propriedades.put("mail.smtp.port", "587");
		propriedades.put("mail.smtp.auth", "true");
		propriedades.put("mail.user", remetente);
		propriedades.put("mail.password", senha);
		
		Session sessao = Session.getInstance(propriedades);
		sessao.setDebug(true);

		MimeMessage mensagem = new MimeMessage(sessao);

		try {
			mensagem.setFrom(new InternetAddress(remetente));
			mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mensagem.setSubject("Finanças Pessoais - Recuperação de Senha");
			mensagem.setText("Digite no programa: " + codigo + "-<NOVA_SENHA>");
			Transport transport = sessao.getTransport("smtp");
			transport.connect(hospedeiro, remetente, senha);
			transport.sendMessage(mensagem, mensagem.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
