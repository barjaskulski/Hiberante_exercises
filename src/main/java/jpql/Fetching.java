package jpql;

import basic_CRUD.App;
import entity.Order;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class Fetching {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

//        Product product = entityManager.find(Product.class, 1L);

        List<Order> orders = entityManager.createQuery("select o from Order o order by o.created desc ", Order.class).setMaxResults(5).getResultList();

        for (Order order : orders) {
            logger.info(order);
            logger.info(order.getOrderRows());

        }
//        logger.info(product);
//        logger.info(product.getCategory());
//        logger.info(product.getReviewList());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
