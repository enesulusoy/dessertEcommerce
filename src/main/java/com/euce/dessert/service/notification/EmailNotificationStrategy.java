package com.euce.dessert.service.notification;

import com.euce.dessert.model.Product;

public class EmailNotificationStrategy implements NotificationStrategy {

    private final EmailService emailService;

    public EmailNotificationStrategy(EmailService emailService) {
        this.emailService = emailService;
    }
    @Override
    public void sendNotification(Product product) {
        String message = "Stokta bulunmayan bir ürün için bildirim: " + product.getName();
        emailService.sendEmail(product.getBrand().getEmail(), "Stokta bulunmayan ürün", message);
    }
}
