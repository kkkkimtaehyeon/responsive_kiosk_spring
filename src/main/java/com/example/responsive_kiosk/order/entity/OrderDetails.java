package com.example.responsive_kiosk.order.entity;

import com.example.responsive_kiosk.product.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(referencedColumnName = "orders_id")
    private Orders orders;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Double price;

    @Column(name = "temperature")
    private String temperature;

    @Builder
    public OrderDetails(Menu menu, Orders orders, Integer amount, Double price, String temperature) {
        this.menu = menu;
        this.orders = orders;
        this.amount = amount;
        this.price = price;
        this.temperature = temperature;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
