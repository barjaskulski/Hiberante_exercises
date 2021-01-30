package basic_CRUD;

import entity.Product;
import entity.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class AppUpdate {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class,1L);
        product.setProductType(ProductType.AMD);
        product.setName("5800XT");
        //product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("3999.99"));

        Product updatedProduct = entityManager.merge(product);

        logger.info(updatedProduct);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
