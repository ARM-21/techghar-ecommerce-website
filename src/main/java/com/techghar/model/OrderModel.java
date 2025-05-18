package com.techghar.model;

import java.util.Date;
import java.util.List;

public class OrderModel {
    private int orderId;
    private int userId;
    private double totalPrice;
    private Date orderDate;
    private DeliveryModel delivery; // updated
    private List<OrderItem> items;
    private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public DeliveryModel getDelivery() {
		return delivery;
	}
	public void setDelivery(DeliveryModel delivery) {
		this.delivery = delivery;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


}
