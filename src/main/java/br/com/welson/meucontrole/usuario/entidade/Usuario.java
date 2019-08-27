package br.com.welson.meucontrole.usuario.entidade;

import br.com.welson.meucontrole.usuario.util.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static br.com.welson.meucontrole.usuario.util.TipoUsuario.PADRAO;

@Entity
@Table(name = "USO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(name = "IDUSO", nullable = false, length = 10)
    private String id;

    @Column(name = "NMEUSO", nullable = false)
    private String nome;

    @Column(name = "EMLUSO", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "SNAUSO", nullable = false)
    private String senha;

    @Column(name = "TPOUSO", nullable = false)
    private TipoUsuario tipoUsuario;

    @PrePersist
    private void prePersist() {
        tipoUsuario = PADRAO;
    }
}
