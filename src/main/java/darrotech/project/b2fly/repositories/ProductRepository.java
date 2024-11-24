package darrotech.project.b2fly.repositories;

import darrotech.project.b2fly.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}