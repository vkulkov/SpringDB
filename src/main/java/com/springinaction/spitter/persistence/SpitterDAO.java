package com.springinaction.spitter.persistence;

import com.springinaction.spitter.domain.Spitter;

public interface SpitterDAO {
    void addSpitter(Spitter spitter);
    void saveSpitter(Spitter spitter);
    void deleteSpitter(Spitter spitter);
    Spitter getSpitterById(long id);
}
