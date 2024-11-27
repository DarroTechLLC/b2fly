package darrotech.project.b2fly.models;

import darrotech.project.b2fly.models.Product;
import darrotech.project.b2fly.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer quantity;

    public void setProduct(Product product) {
    }

    // Getters and Setters
}
