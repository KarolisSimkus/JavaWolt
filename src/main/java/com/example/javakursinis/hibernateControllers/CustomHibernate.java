package com.example.javakursinis.hibernateControllers;

import com.example.javakursinis.model.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        }finally {
            em.close();
        }
        return user;
    }

    public List<FoodOrder> getRestaurantOrders(Restaurant restaurant) {
        List<FoodOrder> listOrders = new ArrayList<FoodOrder>();
        try{
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<FoodOrder> query = cb.createQuery(FoodOrder.class);
            Root<FoodOrder> root = query.from(FoodOrder.class);
            query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            Query q = em.createQuery(query);
            listOrders = q.getResultList();
        } catch (Exception e) {
            //
        }finally {
            em.close();
        }
        return listOrders;
    }

    public List<FoodOrder> getFilteredRestaurantOrders(
            OrderStatus status,
            Restaurant restaurant,
            BasicUser user,
            LocalDate start,
            LocalDate end
    ) {
        List<FoodOrder> orders = new ArrayList<FoodOrder>();
        try{
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<FoodOrder> query = cb.createQuery(FoodOrder.class);
            Root<FoodOrder> root = query.from(FoodOrder.class);
            if(restaurant != null) {
                query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            }else{
                query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            }

            Query q = em.createQuery(query);
            orders = q.getResultList();
        } catch (Exception e) {
            //
        }finally {
            em.close();
        }
        return orders;
    }

    public List<Cuisine> getRestaurantCuisine(Restaurant restaurant) {
        List<Cuisine> menu = new ArrayList<Cuisine>();
        try{
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Cuisine> query = cb.createQuery(Cuisine.class);
            Root<Cuisine> root = query.from(Cuisine.class);
            query.select(root).where(cb.equal(root.get("restaurant"), restaurant));
            Query q = em.createQuery(query);
            menu = q.getResultList();
        } catch (Exception e) {
            //
        }finally {
            em.close();
        }
        return menu;
    }


}
