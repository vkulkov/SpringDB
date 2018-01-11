package com.springinaction.spitter.service;

import com.springinaction.spitter.domain.Spitter;
import com.springinaction.spitter.persistence.SpitterDAO;

public class DeclarativeSpitterService implements SpitterService {
    private SpitterDAO spitterDAO;

    public SpitterDAO getSpitterDAO() {
        return spitterDAO;
    }

    public void setSpitterDAO(SpitterDAO spitterDAO) {
        this.spitterDAO = spitterDAO;
    }

    @Override
    public void addSpitter(Spitter spitter) {
        spitterDAO.addSpitter(spitter);
    }

    @Override
    public Spitter getSpitterById(long id) {
        return spitterDAO.getSpitterById(id);
    }
}
