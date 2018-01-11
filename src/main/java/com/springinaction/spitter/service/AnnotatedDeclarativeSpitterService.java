package com.springinaction.spitter.service;

import com.springinaction.spitter.domain.Spitter;
import com.springinaction.spitter.persistence.SpitterDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AnnotatedDeclarativeSpitterService implements SpitterService {
    private SpitterDAO spitterDAO;

    public SpitterDAO getSpitterDAO() {
        return spitterDAO;
    }

    public void setSpitterDAO(SpitterDAO spitterDAO) {
        this.spitterDAO = spitterDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSpitter(Spitter spitter) {
        spitterDAO.addSpitter(spitter);
    }

    @Override
    public Spitter getSpitterById(long id) {
        return spitterDAO.getSpitterById(id);
    }
}
