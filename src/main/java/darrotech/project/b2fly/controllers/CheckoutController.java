package darrotech.project.b2fly.controllers;

import darrotech.project.b2fly.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public String checkout(Model model, Principal principal) {
        model.addAttribute("cartItems", checkoutService.getCartItems(principal.getName()));
        model.addAttribute("total", checkoutService.calculateTotal(principal.getName()));
        return "checkout";
    }

    @PostMapping("/process")
    public String processCheckout(@RequestParam String address, Principal principal) {
        checkoutService.processOrder(principal.getName(), address);
        return "redirect:/confirmation";
    }
}

