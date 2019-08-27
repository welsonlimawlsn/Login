package br.com.welson.meucontrole.usuario.dao;

import br.com.welson.meucontrole.usuario.entidade.Usuario;

import java.util.Optional;

public interface UsuarioDAO {

    Optional<Usuario> procurarPorEmailESenha(String email, String senha);

    void insere(Usuario usuario);

    Optional<Usuario> consultaPorId(String id);
}
