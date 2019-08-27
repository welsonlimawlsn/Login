package br.com.welson.meucontrole.usuario.dto;

import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.usuario.util.TipoUsuario;
import lombok.Getter;

@Getter
public class UsuarioRespostaAutenticacaoDTO {

    private String nome;
    private String email;
    private String token;
    private TipoUsuario tipoUsuario;

    public UsuarioRespostaAutenticacaoDTO(Usuario usuario, String token) {
        this.token = token;
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.tipoUsuario = usuario.getTipoUsuario();
    }
}
