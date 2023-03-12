package com.euce.dessert.service.notification;

import com.euce.dessert.model.Product;
import com.euce.dessert.model.constant.NotificationType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {
    private  final Map<NotificationType, NotificationStrategy> strategies;

    public NotificationService(EmailService emailService) {
        this.strategies = new HashMap<>();
        this.strategies.put(NotificationType.EMAIL, new EmailNotificationStrategy(emailService));
    }

    public void sendNotification(Product product){
        NotificationStrategy notificationStrategy = strategies.get(product.getBrand().getNotificationType());
        if (notificationStrategy == null) {
            throw new IllegalArgumentException("Invalid notification type");
        }
        notificationStrategy.sendNotification(product);
    }
}
