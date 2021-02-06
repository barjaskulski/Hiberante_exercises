package basic_Relations;

import basic_CRUD.App;
import entity.Attribute;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddManyToMany {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);
        //product.addAttributes(entityManager.find(Attribute.class,2L));
        //logger.info(product.getAttributes());

        Attribute attribute = new Attribute();
        attribute.setName("COLOR");
        attribute.setValue("BLACK");
        product.addAttributes(attribute);
        logger.info(product.getAttributes());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
