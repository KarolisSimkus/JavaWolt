package com.example.javakursinis.hibernateControllers;

import com.example.javakursinis.Utils.FxUtils;
import com.example.javakursinis.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class GenericHibernate {
    protected EntityManagerFactory emf;
    protected EntityManager em;

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
            FxUtils.generateAlert(Alert.AlertType.INFORMATION,
            "Error", "Error", "DB create generic error");
            // em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    public <T> void update(T entity) {
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(entity); // UPDATE
            em.getTransaction().commit();
        } catch (Exception e) {
            // Cia alertus rasysime
            FxUtils.generateAlert(Alert.AlertType.INFORMATION,
                    "Error", "Error", "DB update generic error");
            // em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    public <T> void delete(T entity) {
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Cia alertus rasysime
            FxUtils.generateAlert(Alert.AlertType.INFORMATION,
                    "Error", "Error", "DB delete generic error");
            // em.getTransaction().rollback();
            FxUtils.generateDialogAlert(Alert.AlertType.ERROR, "During delete", e);
        }finally{
            em.close();
        }
    }
public <T> void deleteById(Class<T> entityClass, int id) {
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Cia alertus rasysime
            FxUtils.generateAlert(Alert.AlertType.INFORMATION,
                    "Error", "Error", "DB delete generic error");
            // em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    public <T> List<T> getAllRecords(Class<T> entityClass) {
        List<T> list = new ArrayList<T>();
        try{
            em = emf.createEntityManager();
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(entityClass));
            Query q = em.createQuery(query);
            list = q.getResultList();
        } catch (Exception e) {
            // Cia alertus rasysime
            FxUtils.generateAlert(Alert.AlertType.INFORMATION,
                    "Error", "Error", "DB getAllRecords generic error");
            // em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return list;
    }

    public <T> T getEntityById(Class<T> entityClass, int id) {
        T entity = null;

        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            entity = em.find(entityClass, id);
            em.getTransaction().commit();
        }catch (Exception e) {
            FxUtils.generateAlert(Alert.AlertType.INFORMATION,
                    "Error", "Error", "DB getEntityById generic error");
        }finally{
            em.close();
        }

        return entity;
    }
}
