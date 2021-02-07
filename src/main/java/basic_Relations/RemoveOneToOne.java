package basic_Relations;

import basic_CRUD.App;
import com.sun.istack.NotNull;
import entity.Category;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoveOneToOne {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class,3L);
        //product.setCategory(null);
        entityManager.remove(product.getCategory());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
