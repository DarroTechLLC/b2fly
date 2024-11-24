package darrotech.project.b2fly.models;

import darrotech.project.b2fly.models.Product;
import darrotech.project.b2fly.models.User;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer quantity;

    // Getters and Setters
}
