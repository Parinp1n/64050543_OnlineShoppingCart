/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pinpin
 */
public class ShoppingcartTable {
    
        public static List<Shoppingcart> findAllShoppingcart() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Shoppingcart.findAll", Shoppingcart.class).getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static Shoppingcart findShoppingcartById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Shoppingcart.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void updateShoppingcart(Shoppingcart sc) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, sc.getShoppingcartPK());
            if (target == null) {
                return;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void removeShoppingcart(ShoppingcartPK pk) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, pk);
            if (target == null) {
            }
            em.remove(target);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void insertShoppingCart(Shoppingcart shoppingCart){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(shoppingCart);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

     public static int findLastestCartID(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        int lastestCartID = 0;
        List<Shoppingcart> shoppingCartList = null;
        try{
            shoppingCartList = (List<Shoppingcart>) em.createNamedQuery("Shoppingcart.findAll").getResultList();
            for(Shoppingcart spCart : shoppingCartList){
                if(spCart.getShoppingcartPK().getCartId() > lastestCartID){
                    lastestCartID = spCart.getShoppingcartPK().getCartId();
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }
        return lastestCartID;
    }
   
}
