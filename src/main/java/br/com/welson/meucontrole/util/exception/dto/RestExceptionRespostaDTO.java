package br.com.welson.meucontrole.util.exception.dto;

import br.com.welson.meucontrole.util.Mensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.Response;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestExceptionRespostaDTO {

    private String mensagem;
    private Mensagem mensagemEnum;
    private Response.Status status;
    private int codigoStatus;
}
