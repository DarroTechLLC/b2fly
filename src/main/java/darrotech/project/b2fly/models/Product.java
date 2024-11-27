package darrotech.project.b2fly.models;

import jakarta.persistence.*;
import lombok.Data; // Lombok annotation

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private int sortOrder;

    @Lob
    private byte[] image;
}
