package jpql;

import DTO.ProductInCategoryCounterDTO;
import basic_CRUD.App;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.*;
import java.util.List;

public class JoinQuery {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p" +
                " left join fetch p.category c",Product.class);

        //query.setParameter("id",3L);

        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getCategory());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
