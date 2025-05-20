package com.techghar.model;

import java.util.Date;
import java.util.List;

public class OrderModel {
	private int id;
	private int userId;
	private double totalAmount;
	private Date orderDate;
	private int orderId;

	private List<OrderItem> orderItems;
	private DeliveryModel deliveryModel;
	private String address;
	private String phone;
	private String deliveryStatus;
	private Date deliveryDate;

	// Constructors

	public OrderModel() {

	}

	public OrderModel(int Id, int userId, double totalAmount, Date orderDate, List<OrderItem> orderItems,
			DeliveryModel deliveryModel) {
		this.id = Id;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
		this.deliveryModel = deliveryModel;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderID() {
		return orderId;
	}

	public void setOrderID(int orderID) {
		this.orderId = orderID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public DeliveryModel getDeliveryModel() {
		return deliveryModel;
	}

	public void setDeliveryModel(DeliveryModel deliveryModel) {
		this.deliveryModel = deliveryModel;
	}

}