package br.com.welson.meucontrole.usuario.util;

public enum TipoUsuario {
    ADMINISTRADOR(2), PADRAO(1);

    private Integer value;

    TipoUsuario(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
