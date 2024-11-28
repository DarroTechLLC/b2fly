package darrotech.project.b2fly.controllers;

import darrotech.project.b2fly.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        model.addAttribute("cartItems", cartService.getCartItems(principal.getName()));
        model.addAttribute("total", cartService.calculateTotal(principal.getName()));
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Principal principal) {
        cartService.addToCart(principal.getName(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartId, Principal principal) {
        cartService.removeFromCart(principal.getName(), cartId);
        return "redirect:/cart";
    }
}
