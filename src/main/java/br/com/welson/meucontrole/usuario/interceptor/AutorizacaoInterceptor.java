package br.com.welson.meucontrole.usuario.interceptor;

import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.usuario.service.TokenService;
import br.com.welson.meucontrole.usuario.service.UsuarioService;
import br.com.welson.meucontrole.util.Mensagem;
import br.com.welson.meucontrole.util.exception.NotFoundException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Arrays;

@Interceptor
@Autorizacao
public class AutorizacaoInterceptor implements Serializable {

    @Inject
    private HttpServletRequest httpServletRequest;

    @Inject
    private TokenService tokenService;

    @EJB
    private UsuarioService usuarioService;

    private Autorizacao classAnnotation;

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {
        return validaUsuario(invocationContext);
    }

    private Object validaUsuario(InvocationContext invocationContext) throws Exception {
        Usuario usuario = getUsuarioFromAuthorization();
        Autorizacao annotation = getAnnotation(invocationContext);
        boolean usuarioNaoEhPermitido = Arrays.stream(annotation.value()).noneMatch(tipoUsuario -> tipoUsuario == usuario.getTipoUsuario());
        if (usuarioNaoEhPermitido) {
            throw new NotFoundException(Mensagem.ESSE_RECURSO_NAO_EXISTE);
        }
        Object returnOfMethod = invocationContext.proceed();
        if (returnOfMethod instanceof Response) {
            ((Response) returnOfMethod).getHeaders().add("Next-Authorization", tokenService.generateToken(usuario));
        }
        return returnOfMethod;
    }

    @AroundConstruct
    public Object constructInvoke(InvocationContext invocationContext) throws Exception {
        Object proceed = invocationContext.proceed();
        this.classAnnotation = invocationContext.getTarget().getClass().getAnnotation(Autorizacao.class);
        return proceed;
    }

    private Usuario getUsuarioFromAuthorization() throws NotFoundException {
        String id = tokenService.getSubject(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return usuarioService.consultaPorId(id);
    }

    private Autorizacao getAnnotation(InvocationContext invocationContext) {
        Autorizacao methodAnnotation = invocationContext.getMethod().getAnnotation(Autorizacao.class);
        return methodAnnotation != null ? methodAnnotation : classAnnotation;
    }
}
