package com.bookStoreFullStack.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_entity")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "date")
	private Date date;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	@ManyToOne
    @JoinColumn(name = "coupon_id", nullable = true)
    private Coupon coupon;
}
