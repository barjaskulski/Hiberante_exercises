package jpql;

import basic_CRUD.App;
import entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;


public class In {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Order> orders = entityManager.createQuery("select o from Order o where id in (:ids)",
                Order.class)
                .setParameter("ids", Arrays.asList(1L, 3L, 5L))
                .getResultList();

        List<Order> ordersNegation = entityManager.createQuery("select o from Order o where id not in (:ids)",
                Order.class)
                .setParameter("ids", Arrays.asList(1L, 3L, 5L))
                .getResultList();

        for (Order order : orders) {
            logger.info("with IN " + order);
        }

        for (Order order : ordersNegation) {
            logger.info("with not IN " + order);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
