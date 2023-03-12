package com.euce.dessert.service.notification;

import com.euce.dessert.model.Product;

public interface NotificationStrategy {
    void sendNotification(Product product);
}
