package com.techghar.model;

/**
 * CategorySalesReport represents sales data for a specific product category.
 * This model is used for generating category-based sales analytics in the admin dashboard.
 */
public class CategorySalesReport {
    private String categoryName;
    private int productsSold;
    private double totalRevenue;
    private double percentageOfSales;

    /**
     * Constructor for creating a new category sales report.
     *
     * @param categoryName      The name of the product category
     * @param productsSold      The total number of products sold in this category
     * @param totalRevenue      The total revenue generated from this category
     * @param percentageOfSales The percentage this category contributes to overall sales
     */
    public CategorySalesReport(String categoryName, int productsSold, double totalRevenue, double percentageOfSales) {
        this.categoryName = categoryName;
        this.productsSold = productsSold;
        this.totalRevenue = totalRevenue;
        this.percentageOfSales = percentageOfSales;
    }

    /**
     * Gets the category name.
     *
     * @return The name of the product category
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name.
     *
     * @param categoryName The name of the product category to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the total number of products sold in this category.
     *
     * @return The number of products sold
     */
    public int getProductsSold() {
        return productsSold;
    }

    /**
     * Sets the total number of products sold in this category.
     *
     * @param productsSold The number of products sold to set
     */
    public void setProductsSold(int productsSold) {
        this.productsSold = productsSold;
    }

    /**
     * Gets the total revenue generated from this category.
     *
     * @return The total revenue
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Sets the total revenue generated from this category.
     *
     * @param totalRevenue The total revenue to set
     */
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    /**
     * Gets the percentage this category contributes to overall sales.
     *
     * @return The percentage of overall sales
     */
    public double getPercentageOfSales() {
        return percentageOfSales;
    }

    /**
     * Sets the percentage this category contributes to overall sales.
     *
     * @param percentageOfSales The percentage of overall sales to set
     */
    public void setPercentageOfSales(double percentageOfSales) {
        this.percentageOfSales = percentageOfSales;
    }
}
