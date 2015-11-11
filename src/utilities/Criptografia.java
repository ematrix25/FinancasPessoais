package utilities;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Emanuel
 *
 */
public class Criptografia {
	private Key chavePrivada;

	private Key gerarChPrivada() {
		try {
			KeyGenerator gerador = KeyGenerator.getInstance("DES");
			SecureRandom aleatorio = new SecureRandom("UserPasswordKeySeed".getBytes());
			gerador.init(56, aleatorio);
			Key chave = gerador.generateKey();
			return chave;
		} catch (Exception e) {
			System.err.println("Criptografia.gerarChPrivada() Exception: " + e);
			return null;
		}
	}

	public Criptografia() {
		chavePrivada = gerarChPrivada();
	}

	public String criptografar(String original) {
		try {
			Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cifrador.init(Cipher.ENCRYPT_MODE, chavePrivada);

			byte[] bytesEntrada = original.getBytes("UTF8");
			byte[] bytesSaida = cifrador.doFinal(bytesEntrada);

			BASE64Encoder codificador = new BASE64Encoder();
			String criptografado = codificador.encode(bytesSaida);

			return criptografado;
		} catch (Exception e) {
			System.err.println("Criptografia.encriptar() Exception: " + e);
			return null;
		}
	}

	public String decriptografar(String criptografado) {
		try {
			Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cifrador.init(Cipher.DECRYPT_MODE, chavePrivada);

			BASE64Decoder decodificador = new BASE64Decoder();

			byte[] bytesEntrada = decodificador.decodeBuffer(criptografado);
			byte[] bytesSaida = cifrador.doFinal(bytesEntrada);

			String original = new String(bytesSaida, "UTF8");

			return original;
		} catch (Exception e) {
			System.err.println("Criptografia.encriptar() Exception: " + e);
			return null;
		}
	}
}
