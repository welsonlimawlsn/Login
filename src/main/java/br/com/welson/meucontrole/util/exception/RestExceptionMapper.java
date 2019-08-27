package br.com.welson.meucontrole.util.exception;

import br.com.welson.meucontrole.util.exception.dto.RestExceptionRespostaDTO;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {
    @Override
    public Response toResponse(RestException e) {
        return Response.status(e.getStatus())
                .entity(new RestExceptionRespostaDTO(e.getMessage(), e.getMensagem(), e.getStatus(), e.getStatus().getStatusCode()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
