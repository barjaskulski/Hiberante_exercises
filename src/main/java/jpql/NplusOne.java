package jpql;

import basic_CRUD.App;
import entity.Order;
import entity.OrderRow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NplusOne {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Order> products = entityManager.createQuery(
                "select distinct o from Order o" +
                " inner join fetch o.orderRows"
                , Order.class).getResultList();
//
// ---------------------------------------------------------------------------------------------------------------------------
//        EntityGraph entityGraph = entityManager.getEntityGraph("order-and-rows");
//
//        List<Order> products = entityManager.createQuery(
//                "select distinct o from Order o",
//                Order.class)
//                .setHint("javax.presistance.fetchgraph",entityGraph)
//                .getResultList();


        logger.info("Ilosc zamowien: "+products.size());
        for (Order order : products) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
