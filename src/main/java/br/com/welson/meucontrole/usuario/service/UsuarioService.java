package br.com.welson.meucontrole.usuario.service;

import br.com.welson.meucontrole.usuario.dao.UsuarioDAO;
import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.usuario.util.CriptografiaSenha;
import br.com.welson.meucontrole.util.Mensagem;
import br.com.welson.meucontrole.util.exception.NotFoundException;
import br.com.welson.meucontrole.util.exception.UnauthorizedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;

@Stateless
public class UsuarioService {

    @Inject
    private UsuarioDAO dao;

    @Inject
    private CriptografiaSenha criptografiaSenha;

    public Usuario procurarPorEmailESenha(String email, String senha) throws UnauthorizedException, NoSuchAlgorithmException {
        return dao.procurarPorEmailESenha(email, criptografiaSenha.criptografar(email, senha))
                .orElseThrow(() -> new UnauthorizedException(Mensagem.EMAIL_OU_SENHA_INVALIDOS));
    }

    public void novo(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(criptografiaSenha.criptografar(usuario.getEmail(), usuario.getSenha()));
        dao.insere(usuario);
    }

    public Usuario consultaPorId(String id) throws NotFoundException {
        return dao.consultaPorId(id).orElseThrow(() -> new NotFoundException(Mensagem.USUARIO_NAO_ENCONTRADO));
    }
}
