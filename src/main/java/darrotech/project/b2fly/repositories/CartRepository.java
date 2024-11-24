package darrotech.project.b2fly.repositories;


import darrotech.project.b2fly.models.Cart;
import darrotech.project.b2fly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}


