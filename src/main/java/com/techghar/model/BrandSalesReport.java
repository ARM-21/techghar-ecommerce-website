package com.techghar.model;

/**
 * BrandSalesReport represents sales data for a specific product brand.
 * This model is used for generating brand-based sales analytics in the admin dashboard.
 */
public class BrandSalesReport {
    private String brandName;
    private int productsSold;
    private double totalRevenue;
    private double percentageOfSales;

    /**
     * Constructor for creating a new brand sales report.
     *
     * @param brandName         The name of the product brand
     * @param productsSold      The total number of products sold from this brand
     * @param totalRevenue      The total revenue generated from this brand
     * @param percentageOfSales The percentage this brand contributes to overall sales
     */
    public BrandSalesReport(String brandName, int productsSold, double totalRevenue, double percentageOfSales) {
        this.brandName = brandName;
        this.productsSold = productsSold;
        this.totalRevenue = totalRevenue;
        this.percentageOfSales = percentageOfSales;
    }

    /**
     * Gets the brand name.
     *
     * @return The name of the product brand
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the brand name.
     *
     * @param brandName The name of the product brand to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Gets the total number of products sold from this brand.
     *
     * @return The number of products sold
     */
    public int getProductsSold() {
        return productsSold;
    }

    /**
     * Sets the total number of products sold from this brand.
     *
     * @param productsSold The number of products sold to set
     */
    public void setProductsSold(int productsSold) {
        this.productsSold = productsSold;
    }

    /**
     * Gets the total revenue generated from this brand.
     *
     * @return The total revenue
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Sets the total revenue generated from this brand.
     *
     * @param totalRevenue The total revenue to set
     */
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    /**
     * Gets the percentage this brand contributes to overall sales.
     *
     * @return The percentage of overall sales
     */
    public double getPercentageOfSales() {
        return percentageOfSales;
    }

    /**
     * Sets the percentage this brand contributes to overall sales.
     *
     * @param percentageOfSales The percentage of overall sales to set
     */
    public void setPercentageOfSales(double percentageOfSales) {
        this.percentageOfSales = percentageOfSales;
    }
}