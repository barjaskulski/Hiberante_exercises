package jpql;

import DTO.ProductInCategoryCounterDTO;
import basic_CRUD.App;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapping {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select p.category.id, count(p) from Product p group by p.category");

        //--------------------------------------------------------------------------------------------------------------
        List<ProductInCategoryCounterDTO> result = ((List<Object[]>) query.getResultList()).stream()
                .map(objects -> new ProductInCategoryCounterDTO((Long) objects[0],(Long) objects[1]))
                .collect(Collectors.toList());

        for (ProductInCategoryCounterDTO dto : result) {
            logger.info("Category ID: "+dto.getCategoryId());
            logger.info("Count of products: "+dto.getProductInCategoryCounter());
        }
        //--------------------------------------------------------------------------------------------------------------

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
