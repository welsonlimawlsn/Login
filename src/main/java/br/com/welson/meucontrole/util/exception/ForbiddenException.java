package br.com.welson.meucontrole.util.exception;

import br.com.welson.meucontrole.util.Mensagem;

import javax.ws.rs.core.Response;

public class ForbiddenException extends RestException {
    public ForbiddenException(Mensagem mensagem) {
        super(Response.Status.FORBIDDEN, mensagem);
    }
}
