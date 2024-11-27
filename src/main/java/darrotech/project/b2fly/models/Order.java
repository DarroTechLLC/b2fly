package darrotech.project.b2fly.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Cart> items;

    private String status; // "PENDING", "SHIPPED", etc.

    public void setStatus(String status) {
    }

    // Getters and Setters
}
