package com.bookStoreFullStack.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();
	
	private double total;
	
	

	public void calculateTotal() {
        total = items.stream()
                     .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                     .sum();
    }
    
	public Double applyCoupon(Coupon coupon) {
		double discountedTotal  = total - (total * coupon.getDiscount() / 100);
		return discountedTotal;
	}
}
