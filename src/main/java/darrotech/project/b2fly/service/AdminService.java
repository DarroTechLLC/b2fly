package darrotech.project.b2fly.service;

import darrotech.project.b2fly.models.Order;
import darrotech.project.b2fly.models.Product;
import darrotech.project.b2fly.repositories.OrderRepository;
import darrotech.project.b2fly.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Product> viewAllProducts() {
        return productRepository.findAll();
    }

    public List<Order> viewAllOrders() {
        return orderRepository.findAll();
    }

    public void reorderProducts(List<Long> productIds) {
        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepository.findById(productIds.get(i)).orElseThrow();
            product.setSortOrder(i); // Ensure a `sortOrder` field exists in `Product`
            productRepository.save(product);
        }
    }

}

