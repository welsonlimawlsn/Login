package br.com.welson.meucontrole.usuario.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaSenha {

    public String criptografar(String email, String senha) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] digest = instance.digest((email + senha).getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02X", 0xFF & b));
        }
        return hex.toString();
    }
}
