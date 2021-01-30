package basic_CRUD;

import entity.Product;
import entity.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppCreate {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = new Product();
        product.setName("RTX3090");
        product.setDescription("Grafika");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("7999.99"));
        product.setProductType(ProductType.NVIDIA);

        entityManager.persist(product);

        logger.info(product);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
