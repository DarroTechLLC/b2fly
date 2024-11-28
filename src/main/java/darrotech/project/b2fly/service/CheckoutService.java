package darrotech.project.b2fly.service;

import darrotech.project.b2fly.models.Cart;
import darrotech.project.b2fly.models.Order;
import darrotech.project.b2fly.models.User;
import darrotech.project.b2fly.repositories.CartRepository;
import darrotech.project.b2fly.repositories.OrderRepository;
import darrotech.project.b2fly.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Cart> getCartItems(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user);
    }

    public BigDecimal calculateTotal(String username) {
        List<Cart> cartItems = getCartItems(username);
        return cartItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void processOrder(String username, String address) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Cart> cartItems = getCartItems(username);

        Order order = new Order();
        order.setUser(user);
        order.setItems(cartItems);
        order.setAddress(address);
        order.setStatus("PENDING");
        orderRepository.save(order);

        // Clear cart
        cartRepository.deleteAll(cartItems);
    }
}
