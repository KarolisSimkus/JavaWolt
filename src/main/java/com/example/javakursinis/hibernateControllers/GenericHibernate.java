package com.example.javakursinis.hibernateControllers;

import com.example.javakursinis.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class GenericHibernate {
    EntityManagerFactory emf;
    EntityManager em;

    public GenericHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public <T> void create(T entity) {
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Cia alertus rasysime
            // em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
}
