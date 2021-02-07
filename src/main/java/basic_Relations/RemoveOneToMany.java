package basic_Relations;

import basic_CRUD.App;
import entity.Product;
import entity.Review;
import entity.ReviewDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class RemoveOneToMany {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<ReviewDTO> reviewDTOS = getUpdatedReviews();

        Product product = entityManager.find(Product.class,3L);
        for (Review review : product.getReviewList()) {
            for (ReviewDTO reviewDTO : reviewDTOS) {
                if(review.getId().equals(reviewDTO.getId())){
                    review.setContent(reviewDTO.getContent());
                    review.setRating(reviewDTO.getRating());
                }
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static List<ReviewDTO> getUpdatedReviews(){
        List<ReviewDTO> list = new ArrayList<>();
        list.add(new ReviewDTO(13L,"opinia 3",10));
        list.add(new ReviewDTO(14L,"opinia 4",10));
        list.add(new ReviewDTO(15L,"opinia 5",10));
        return list;
    }
}
