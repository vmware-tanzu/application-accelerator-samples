package com.example.music.domain;

// #IF(#persistenceType == 'jpa')
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
// #ENDIF
import java.util.UUID;

// #IF(#persistenceType == 'jpa')
public class RandomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return generateId();
    }

// #ELSE
public class RandomIdGenerator {
// #ENDIF
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
