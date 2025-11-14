package com.example.javakursinis.hibernateControllers;

import com.example.javakursinis.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CustomHibernate extends GenericHibernate{
    public CustomHibernate(EntityManagerFactory emf) {
        super(emf);
    }

    public User getUserByCredentials(String username, String password) {
        User user = null;
        try{
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root).where(cb.and(
                    cb.equal(root.get("login"), username),
                    cb.equal(root.get("password"), password)
            ));
            Query q = em.createQuery(query);
            user = (User) q.getSingleResult();
        } catch (Exception e) {
        //
        }
        return user;
    }
}
