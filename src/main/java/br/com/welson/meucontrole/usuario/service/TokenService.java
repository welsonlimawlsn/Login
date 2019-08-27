package br.com.welson.meucontrole.usuario.service;

import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.util.Mensagem;
import br.com.welson.meucontrole.util.exception.NotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@ApplicationScoped
public class TokenService {

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Usuario usuario) {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        return "Bearer " + Jwts.builder()
                .setSubject(usuario.getId())
                .setExpiration(Date.from(now.plusMinutes(15).toInstant()))
                .signWith(secretKey)
                .compact();
    }

    public String getSubject(String token) throws NotFoundException {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new NotFoundException(Mensagem.ESSE_RECURSO_NAO_EXISTE);
        }
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace("Bearer ", "")).getBody().getSubject();
        } catch (Exception e) {
            throw new NotFoundException(Mensagem.ESSE_RECURSO_NAO_EXISTE);
        }
    }
}
