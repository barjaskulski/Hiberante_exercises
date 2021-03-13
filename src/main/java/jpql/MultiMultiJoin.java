package jpql;

import basic_CRUD.App;
import entity.Category;
import entity.Customer;
import entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.*;
import java.util.List;

public class MultiMultiJoin {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "select distinct c.id, c.lastName as customer, ca.name as category, SUM(orw.price) as total from Customer c" +
                " inner join c.orders o" +
                " inner join o.orderRows orw" +
                " inner join orw.product p" +
                " inner join p.category ca" +
                " group by ca, c" +
                " having SUM(orw.price) > 50" +
                " order by total DESC ");

        /* select cu.id, cu.lastname customer, ca.name category, SUM(orw.price) total from customer cu
inner join orders o on cu.id=o.customer_id
inner join order_row orw on o.id=orw.order_id
inner join product p on orw.product_id=p.id
inner join category ca on p.category_id=ca.id
group by ca.id, cu.id
having total > 50
order by total DESC
;*/

        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            logger.info(row[0] + ", \t" +row[1] + ", \t" +row[2] + ", \t" +row[3]);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
