package jpql;

import basic_CRUD.App;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrzypadekKartezjanski {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Product> resultList = entityManager.createQuery(
                "select distinct p from Product p" + " left join fetch p.attributes",
                Product.class).getResultList();

        resultList = entityManager.createQuery(
                "select distinct p from Product p" + " left join fetch p.reviewList",
                Product.class).getResultList();

        logger.info("Size: "+resultList.size());
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getReviewList());
            logger.info(product.getCategory());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
