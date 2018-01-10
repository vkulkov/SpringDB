package com.springinaction.spitter.persistence;

import com.springinaction.spitter.domain.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSpitterDAO implements SpitterDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpitterDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addSpitter(Spitter spitter) {
        currentSession().save(spitter);
    }

    @Override
    @CacheEvict("spitterCache")
    public void saveSpitter(Spitter spitter) {
        currentSession().update(spitter);
    }

    @Override
    @CacheEvict("spitterCache")
    public void deleteSpitter(Spitter spitter) {
        currentSession().delete(spitter);
    }

    @Override
    @Cacheable("spitterCache")
    public Spitter getSpitterById(long id) {
        return currentSession().get(Spitter.class, id);
    }
}
