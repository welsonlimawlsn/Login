package br.com.welson.meucontrole.usuario.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.ws.rs.ext.Provider;

@Converter(autoApply = true)
@Provider
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoUsuario tipoUsuario) {
        return tipoUsuario.getValue();
    }

    @Override
    public TipoUsuario convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return TipoUsuario.PADRAO;
            case 2:
                return TipoUsuario.ADMINISTRADOR;
        }
        throw new IllegalArgumentException("Não existe tipo de usuário " + integer);
    }
}
