package jpql;

import DTO.ProductInCategoryCounterDTO;
import basic_CRUD.App;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class AutomaticResponseMapping {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select new DTO.ProductInCategoryCounterDTO(p.category.id, count(p)) from Product p group by p.category");

        //--------------------------------------------------------------------------------------------------------------
        List<ProductInCategoryCounterDTO> result = query.getResultList();

        for (ProductInCategoryCounterDTO dto : result) {
            logger.info("Category ID: "+dto.getCategoryId());
            logger.info("Count of products: "+dto.getProductInCategoryCounter());
        }
        //--------------------------------------------------------------------------------------------------------------

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
