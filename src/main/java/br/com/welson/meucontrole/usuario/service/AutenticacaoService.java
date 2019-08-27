package br.com.welson.meucontrole.usuario.service;

import br.com.welson.meucontrole.usuario.dto.UsuarioRespostaAutenticacaoDTO;
import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.util.exception.UnauthorizedException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;

@Stateless
public class AutenticacaoService {

    @EJB
    private UsuarioService usuarioService;

    @Inject
    private TokenService tokenService;

    public UsuarioRespostaAutenticacaoDTO fazerLogin(String email, String senha) throws UnauthorizedException, NoSuchAlgorithmException {
        Usuario usuario = usuarioService.procurarPorEmailESenha(email, senha);
        return new UsuarioRespostaAutenticacaoDTO(usuario, tokenService.generateToken(usuario));
    }
}
