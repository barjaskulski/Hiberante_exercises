package basic_Relations;

import basic_CRUD.App;
import entity.Attribute;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class RemoveManyToMany {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //Product product = entityManager.find(Product.class,5L);
        //entityManager.remove(product);
        //product.getAttributes().clear();

        Attribute attribute = entityManager.find(Attribute.class, 1L);
        for (Product product : new ArrayList<>(attribute.getProducts())) {
            attribute.removeProduct(product);
        }
        entityManager.remove(attribute);

        //logger.info(attribute);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
