package darrotech.project.b2fly.controllers;

import darrotech.project.b2fly.models.Cart;
import darrotech.project.b2fly.models.Product;
import darrotech.project.b2fly.models.User;
import darrotech.project.b2fly.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("cartItems", cartService.getUserCart(user));
        return "user/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, @AuthenticationPrincipal User user) {
        Cart cart = new Cart();
        cart.setProduct(new Product(productId)); // Assume a constructor for simplicity
        cart.setQuantity(quantity);
        cart.setUser(user);
        cartService.addToCart(cart);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartId) {
        cartService.removeFromCart(cartId);
        return "redirect:/cart";
    }
}

