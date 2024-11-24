package darrotech.project.b2fly.repositories;

import darrotech.project.b2fly.models.Order;
import darrotech.project.b2fly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}