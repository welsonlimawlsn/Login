package br.com.welson.meucontrole.usuario.interceptor;

import br.com.welson.meucontrole.usuario.util.TipoUsuario;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Autorizacao {

    @Nonbinding
    TipoUsuario[] value() default TipoUsuario.PADRAO;

}
