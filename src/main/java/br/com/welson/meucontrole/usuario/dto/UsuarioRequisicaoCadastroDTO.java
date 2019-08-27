package br.com.welson.meucontrole.usuario.dto;

import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.util.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UsuarioRequisicaoCadastroDTO implements DTO<Usuario> {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String senha;

    @Override
    public Usuario paraObjeto() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }
}
