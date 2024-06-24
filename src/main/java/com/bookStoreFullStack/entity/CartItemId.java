package com.bookStoreFullStack.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CartItemId implements Serializable{

	@Column(name = "book_id")
	private int bookId;
	
	@Column(name = "cart_id")
	private int cartId;
	
}
