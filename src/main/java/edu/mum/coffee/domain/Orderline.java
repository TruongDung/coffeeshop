package edu.mum.coffee.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_LINE")
public class Orderline implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;

	@Column(name = "QUANTITY")
	private int quantity;

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@Transient
	private double subTotal;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getSubtotal() {
		return quantity * product.getPrice();
	}

	public double getPrice() {
		return product.getPrice();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSubTotal() {
		return product.getPrice() * quantity;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
