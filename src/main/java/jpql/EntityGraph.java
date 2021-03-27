package jpql;

import DTO.ProductInCategoryCounterDTO;
import basic_CRUD.App;
import entity.Order;
import entity.OrderRow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityGraph {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        javax.persistence.EntityGraph<?> entityGraph = entityManager.getEntityGraph("order-rows");

        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistance.loadgraph",entityGraph);

        Order order = entityManager.find(Order.class, 1L, map);
        logger.info(order);
        for (OrderRow orderRow : order.getOrderRows()) {
            logger.info(order.getOrderRows());
            logger.info(order.getCustomer());
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
