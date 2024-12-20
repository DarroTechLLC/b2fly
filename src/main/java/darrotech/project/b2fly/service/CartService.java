package darrotech.project.b2fly.service;

import darrotech.project.b2fly.models.Cart;
import darrotech.project.b2fly.models.Product;
import darrotech.project.b2fly.models.User;
import darrotech.project.b2fly.repositories.CartRepository;
import darrotech.project.b2fly.repositories.ProductRepository;
import darrotech.project.b2fly.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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

    public void addToCart(String username, Long productId, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public void removeFromCart(String username, Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!cart.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized action");
        }
        cartRepository.delete(cart);
    }
}
