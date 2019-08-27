package br.com.welson.meucontrole.util.exception;

import br.com.welson.meucontrole.util.Mensagem;

import javax.ws.rs.core.Response;

public class UnauthorizedException extends RestException {
    public UnauthorizedException(Mensagem mensagem) {
        super(Response.Status.UNAUTHORIZED, mensagem);
    }
}
