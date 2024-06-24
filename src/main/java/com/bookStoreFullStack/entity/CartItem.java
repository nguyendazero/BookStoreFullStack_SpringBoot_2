package com.bookStoreFullStack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_item")
public class CartItem {

	@EmbeddedId
	private CartItemId id;
	
	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne
	@MapsId("bookId")
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column(name = "quantity")
	private int quantity;
	
}
