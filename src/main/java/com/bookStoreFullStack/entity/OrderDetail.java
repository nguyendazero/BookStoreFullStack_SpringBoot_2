package com.bookStoreFullStack.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailId id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("bookId")
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
    private double price;
}