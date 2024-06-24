package com.bookStoreFullStack.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderDetailId implements Serializable{
	
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "book_id")
	private int bookId;
}
