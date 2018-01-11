package com.springinaction.spitter.service;

import com.springinaction.spitter.domain.Spitter;

public interface SpitterService {
    void addSpitter(Spitter spitter);
    Spitter getSpitterById(long id);
}
