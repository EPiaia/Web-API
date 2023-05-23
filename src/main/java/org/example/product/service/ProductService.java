package org.example.product.service;

import org.example.product.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ProductService {

    public Product getProductById(int id) {
        Product product = new Product();
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("web-api")
                .createEntityManager();
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
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("web-api")
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createNativeQuery("SELECT * FROM PRODUCT");
            List<Object> products = query.getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
    }
}
