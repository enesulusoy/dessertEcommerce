package com.euce.dessert.model;

import javax.persistence.*;

import com.euce.dessert.model.account.User;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
