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
        EntityManager entityManager = getEntityManager();
        Product product = new Product();

        try {
            product = entityManager.find(Product.class, id);
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
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM PRODUCT", Product.class);
            List<Product> products = query.getResultList();
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
        EntityManager entityManager = getEntityManager();
        String sql = "SELECT MAX(P_ID) FROM PRODUCT";

        try {
            Query query = entityManager.createNativeQuery(sql);
            Object max = query.getSingleResult();

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
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // Não busca pela função já
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return false;
        }

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
