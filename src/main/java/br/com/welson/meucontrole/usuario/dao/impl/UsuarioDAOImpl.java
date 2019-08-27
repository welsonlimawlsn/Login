package br.com.welson.meucontrole.usuario.dao.impl;

import br.com.welson.meucontrole.usuario.dao.UsuarioDAO;
import br.com.welson.meucontrole.usuario.entidade.Usuario;
import br.com.welson.meucontrole.util.GerarIdFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Inject
    private GerarIdFactory gerarIdFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Usuario> procurarPorEmailESenha(String email, String senha) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void insere(Usuario usuario) {
        usuario.setId(gerarIdFactory.gerarId());
        em.persist(usuario);
    }

    @Override
    public Optional<Usuario> consultaPorId(String id) {
        return Optional.ofNullable(em.find(Usuario.class, id));
    }
}
