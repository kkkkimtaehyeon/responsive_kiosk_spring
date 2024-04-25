package com.example.responsive_kiosk.order.entity;

import com.example.responsive_kiosk.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Orders extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    /*@ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User user;*/

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @Column(name = "total_price", nullable = true)
    private Double totalPrice;

    @Column(name = "takeout", nullable = false)
    private String takeout;

    @Builder
    public Orders(Double totalPrice, String takeout) {
        this.totalPrice = totalPrice;
        this.takeout = takeout;
    }

    public void addDetails(OrderDetails orderDetails) {
        this.orderDetails.add(orderDetails);
        orderDetails.setOrders(this);
    }
}
