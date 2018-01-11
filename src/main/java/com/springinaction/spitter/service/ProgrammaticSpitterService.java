package com.springinaction.spitter.service;

import com.springinaction.spitter.domain.Spitter;
import com.springinaction.spitter.persistence.SpitterDAO;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class ProgrammaticSpitterService implements SpitterService {
    private SpitterDAO spitterDAO;
    private TransactionTemplate txTemplate;

    public SpitterDAO getSpitterDAO() {
        return spitterDAO;
    }

    public void setSpitterDAO(SpitterDAO spitterDAO) {
        this.spitterDAO = spitterDAO;
    }

    public TransactionTemplate getTxTemplate() {
        return txTemplate;
    }

    public void setTxTemplate(TransactionTemplate txTemplate) {
        this.txTemplate = txTemplate;
    }

    @Override
    public void addSpitter(final Spitter spitter) {
        txTemplate.execute((TransactionCallback<Void>) txStatus -> {
            try {
                spitterDAO.addSpitter(spitter);
            } catch (RuntimeException e) {
                txStatus.setRollbackOnly();
                throw e;
            }
            return null;
        });
    }

    @Override
    public Spitter getSpitterById(long id) {
        return spitterDAO.getSpitterById(id);
    }
}
