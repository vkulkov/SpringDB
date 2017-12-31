package com.springinaction.spitter.persistence;

import com.springinaction.spitter.domain.Spitter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaSpitterDAO implements SpitterDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addSpitter(Spitter spitter) {
        em.persist(spitter);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        em.merge(spitter);
    }

    @Override
    public void deleteSpitter(Spitter spitter) {
        em.remove(spitter);
    }

    @Override
    public Spitter getSpitterById(long id) {
        return em.find(Spitter.class, id);
    }
}
