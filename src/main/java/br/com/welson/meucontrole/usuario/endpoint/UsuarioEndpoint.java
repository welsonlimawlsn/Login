package br.com.welson.meucontrole.usuario.endpoint;

import br.com.welson.meucontrole.usuario.dto.UsuarioRequisicaoCadastroDTO;
import br.com.welson.meucontrole.usuario.dto.UsuarioRequisicaoLoginDTO;
import br.com.welson.meucontrole.usuario.dto.UsuarioRespostaAutenticacaoDTO;
import br.com.welson.meucontrole.usuario.interceptor.Autorizacao;
import br.com.welson.meucontrole.usuario.service.AutenticacaoService;
import br.com.welson.meucontrole.usuario.service.UsuarioService;
import br.com.welson.meucontrole.usuario.util.TipoUsuario;
import br.com.welson.meucontrole.util.exception.UnauthorizedException;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.security.NoSuchAlgorithmException;

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioEndpoint {

    @EJB
    private AutenticacaoService autenticacaoService;

    @EJB
    private UsuarioService usuarioService;


    @POST
    public Response novo(@Valid UsuarioRequisicaoCadastroDTO usuarioRequisicaoCadastroDTO) throws NoSuchAlgorithmException {
        usuarioService.novo(usuarioRequisicaoCadastroDTO.paraObjeto());
        return Response.created(UriBuilder.fromPath("usuario").build()).build();
    }

    @Path("login")
    @POST
    public Response login(UsuarioRequisicaoLoginDTO usuarioRequisicaoLoginDTO) throws UnauthorizedException, NoSuchAlgorithmException {
        UsuarioRespostaAutenticacaoDTO usuario = autenticacaoService.fazerLogin(usuarioRequisicaoLoginDTO.getEmail(), usuarioRequisicaoLoginDTO.getSenha());
        return Response
                .ok(usuario)
                .header(HttpHeaders.AUTHORIZATION, usuario.getToken())
                .build();
    }

    @GET
    @Autorizacao({TipoUsuario.PADRAO, TipoUsuario.ADMINISTRADOR})
    public Response consultaUsuario() {
        return Response.ok().build();
    }
}
