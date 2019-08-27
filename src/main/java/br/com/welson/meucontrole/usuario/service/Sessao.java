package br.com.welson.meucontrole.usuario.service;

import br.com.welson.meucontrole.usuario.entidade.Usuario;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Sessao {

    protected Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }
}
