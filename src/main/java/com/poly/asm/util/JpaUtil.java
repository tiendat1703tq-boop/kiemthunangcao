package com.poly.asm.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {//
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("PolyOE1");
        }
        return factory.createEntityManager();
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}