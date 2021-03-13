package jpql;

import basic_CRUD.App;
import entity.Category;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MultiJoin {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Category> query = entityManager.createQuery(
                "select c from Category c" +
                " left join fetch c.product p" +
                " left join fetch p.reviewList" +
                " where c.id=:id",
                Category.class);

        query.setParameter("id",1L);

        List<Category> resultList = query.getResultList();
        for (Category category : resultList) {
            logger.info(category);
            logger.info(category);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
