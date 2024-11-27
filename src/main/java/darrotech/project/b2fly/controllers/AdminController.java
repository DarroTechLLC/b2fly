package darrotech.project.b2fly.controllers;

import darrotech.project.b2fly.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("products", adminService.viewAllProducts());
        model.addAttribute("orders", adminService.viewAllOrders());
        return "admin/dashboard";
    }

    @GetMapping("/inventory")
    public String manageInventory(Model model) {
        model.addAttribute("products", adminService.viewAllProducts());
        return "admin/inventory";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", adminService.viewAllOrders());
        return "admin/orders";
    }

    @PostMapping("/inventory/reorder")
    @ResponseBody
    public ResponseEntity<Void> reorderProducts(@RequestBody List<Long> productIds) {
        adminService.reorderProducts(productIds);
        return ResponseEntity.ok().build();
    }

}

