package br.com.welson.meucontrole.util;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class GerarIdFactory {

    private char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'e',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'E', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0'};

    private Random random = new Random();

    public String gerarId() {
        char[] id = new char[10];
        for (int i = 0; i < id.length; i++) {
            id[i] = chars[random.nextInt(chars.length)];
        }
        return new String(id);
    }
}
