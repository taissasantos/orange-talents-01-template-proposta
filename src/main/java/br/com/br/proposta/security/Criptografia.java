package br.com.br.proposta.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class Criptografia {

    private static String passwordEncrypt = "cGFzc3dvcmQ=";

    public static String encode(String value) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(passwordEncrypt);

        return textEncryptor.encrypt(value);
    }

    public static String decode(String value) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(passwordEncrypt);

        return textEncryptor.decrypt(value);
    }
    
    
}
	



