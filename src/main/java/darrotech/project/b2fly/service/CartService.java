package darrotech.project.b2fly.service;

import darrotech.project.b2fly.models.Cart;
import darrotech.project.b2fly.models.User;
import darrotech.project.b2fly.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getUserCart(User user) {
        return cartRepository.findByUser(user);
    }

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}

