package jpql;

import basic_CRUD.App;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

public class ZapytaniaQuery {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //--------------------------------------------------------------------------------------------------------------
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.id=:id", Product.class); //zapytanie z parametrem
        query.setParameter("id",1L);

        Query query1 = entityManager.createQuery("select count(p), AVG(p.price) from Product p");

        Query query2 = entityManager.createQuery("select p.category.id, count(p) from Product p group by p.category");

        //--------------------------------------------------------------------------------------------------------------
        List<Product> resultList = query.getResultList();   // dla wielu wynikow
        for (Product product : resultList) {
            logger.info(product);
        }

        try{
            Product product = query.getSingleResult();      //dla poojedynczego wyniku
            logger.info(product);
        }catch (NoResultException e){
            logger.error("brak wynikow");
        }
        //--------------------------------------------------------------------------------------------------------------
        //Double avgPrice = (Double) query1.getSingleResult();
        Object[] result = (Object[]) query1.getSingleResult();
        logger.info(result[0]+", "+result[1]);
        //--------------------------------------------------------------------------------------------------------------
        List<Object[]> categoryList = query2.getResultList();
        for (Object[] array : categoryList) {
            logger.info(array[0]+", "+array[1]);
        }
        //--------------------------------------------------------------------------------------------------------------

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
