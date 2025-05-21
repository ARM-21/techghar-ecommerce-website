package com.techghar.model;

import java.util.Date;

/**
 * CustomerPurchaseReport represents daily customer purchase data.
 * This model is used for generating weekly customer purchase analytics in the admin dashboard.
 */
public class CustomerPurchaseReport {
    private Date date;
    private int numberOfCustomers;
    private int numberOfOrders;
    private double totalRevenue;

    /**
     * Constructor for creating a new customer purchase report.
     *
     * @param date              The date of the purchase data
     * @param numberOfCustomers The number of unique customers who made purchases on this date
     * @param numberOfOrders    The total number of orders placed on this date
     * @param totalRevenue      The total revenue generated on this date
     */
    public CustomerPurchaseReport(Date date, int numberOfCustomers, int numberOfOrders, double totalRevenue) {
        this.date = date;
        this.numberOfCustomers = numberOfCustomers;
        this.numberOfOrders = numberOfOrders;
        this.totalRevenue = totalRevenue;
    }

    /**
     * Gets the date of the purchase data.
     *
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the purchase data.
     *
     * @param date The date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the number of unique customers who made purchases on this date.
     *
     * @return The number of customers
     */
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    /**
     * Sets the number of unique customers who made purchases on this date.
     *
     * @param numberOfCustomers The number of customers to set
     */
    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    /**
     * Gets the total number of orders placed on this date.
     *
     * @return The number of orders
     */
    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * Sets the total number of orders placed on this date.
     *
     * @param numberOfOrders The number of orders to set
     */
    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    /**
     * Gets the total revenue generated on this date.
     *
     * @return The total revenue
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Sets the total revenue generated on this date.
     *
     * @param totalRevenue The total revenue to set
     */
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
