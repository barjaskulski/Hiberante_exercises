package basic_Relations;

import basic_CRUD.App;
import entity.Category;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddOneToOne {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = (Logger) LogManager.getLogger(App.class);


    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class,3L);
        Category category = new Category();
        category.setName("Nowa Categoria");
        category.setDescription("opis nowej kategorii");

        entityManager.persist(category);

        product.setCategory(category);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
