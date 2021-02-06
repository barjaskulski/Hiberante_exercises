package basic_Relations;

import basic_CRUD.App;
import entity.Attribute;
import entity.Product;
import entity.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddOneToMany {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);
        Review review = new Review();
        review.setContent("najnowsza opinia");
        review.setRating(1);
        //review.setProduct(product);
        product.addReview(review);
        //entityManager.persist(review);


        logger.info(product.getReviewList());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
