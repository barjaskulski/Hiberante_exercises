package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_row")
public class OrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderRow{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
