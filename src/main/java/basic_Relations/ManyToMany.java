package basic_Relations;

import basic_CRUD.App;
import entity.Attribute;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ManyToMany {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 3L);
        logger.info(product);
        logger.info(product.getAttributes());

        Attribute attribute = entityManager.find(Attribute.class, 3L);
        logger.info(attribute);
        logger.info(attribute.getProducts());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
