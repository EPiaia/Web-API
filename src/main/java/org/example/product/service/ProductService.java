package org.example.product.service;

import org.example.product.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ProductService {

    private EntityManager getEntityManager() {
        return Persistence
                .createEntityManagerFactory("web-api")
                .createEntityManager();
    }

    public Product getProductById(int id) {
        Product product = new Product();
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            product = entityManager.find(Product.class, id);
            transaction.commit();
            return product;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public List<Product> getProducts() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createNativeQuery("SELECT * FROM PRODUCT", Product.class);
            List<Product> products = query.getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public Product save(Product product) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            product = entityManager.merge(product);
            transaction.commit();
            return product;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public int getMaxId() {
        String sql = "SELECT MAX(P_ID) FROM PRODUCT";

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createNativeQuery(sql);
            Object max = query.getSingleResult();
            transaction.commit();

            if (max == null) {
                return 1;
            }

            return Integer.parseInt(max.toString()) + 1;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public boolean delete(int id) {
        Product product = getProductById(id);
        if (product == null) {
            return false;
        }

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }
}
