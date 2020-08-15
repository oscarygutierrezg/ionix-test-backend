package cl.com.ionix.testbackend.util.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.com.ionix.testbackend.util.IEncryptUtil;

/**
 * The Class EncryptUtilImpl.
 */
@Component
public class EncryptUtilImpl implements IEncryptUtil{
	

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(EncryptUtilImpl.class);
	

	/** The key. */
	@Value("${cipher.key}")
	private String key;

	/** The algorithm. */
	@Value("${cipher.algorithm}")
	private String algorithm;


	/**
	 * Encrypt.
	 * 
	 *  Método encargado de cifrar el rutdado por parámetro.
	 *
	 * @param param the param
	 * @return the string
	 */
	public  String encrypt(String param ) {
		String encryptado = "";
		try {
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance(algorithm);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedBytes = Base64.getEncoder().encode(cipher.doFinal(param.getBytes(StandardCharsets.UTF_8)));
	        encryptado = new String(encryptedBytes);
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		return encryptado;
	}
}
