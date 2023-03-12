package com.euce.dessert.task;

import com.euce.dessert.model.Product;
import com.euce.dessert.repository.ProductRepository;
import com.euce.dessert.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ProductInventoryControlTask {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 0 * * *")
    public void notifyOutOfStockClothing() {
        List<Product> outOfStockClothing = productRepository.findByStockQuantity(0);

        for (Product product : outOfStockClothing) {
            notificationService.sendNotification(product);
        }
    }
}
