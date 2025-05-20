<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* Reset and Base Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f7fa;
            color: #333;
            line-height: 1.6;
            padding: 20px;
        }
        
        /* Dashboard Layout */
        .dashboard {
            max-width: 1400px;
            margin: 0 auto;
        }
        
        /* Header */
        .dashboard-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        
        .dashboard-title {
            font-size: 28px;
            font-weight: 600;
            color: #2c3e50;
        }
        
        .dashboard-actions {
            display: flex;
            gap: 15px;
        }
        
        .btn {
            padding: 10px 18px;
            border-radius: 6px;
            font-weight: 500;
            cursor: pointer;
            border: none;
            transition: all 0.2s ease;
        }
        
        .btn-primary {
            background-color: #4361ee;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #3a56d4;
        }
        
        .btn-secondary {
            background-color: #f1f2f6;
            color: #2c3e50;
        }
        
        .btn-secondary:hover {
            background-color: #dfe4ea;
        }
        
        /* Stats Cards */
        .stats-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .stat-card {
            flex: 1 1 200px;
            min-width: 200px;
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
        }
        
        .stat-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        
        .stat-icon {
            width: 44px;
            height: 44px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
        }
        
        .stat-icon.products { background-color: #4361ee; }
        .stat-icon.customers { background-color: #7209b7; }
        .stat-icon.orders { background-color: #f8961e; }
        .stat-icon.revenue { background-color: #43aa8b; }
        
        .stat-value {
            font-size: 24px;
            font-weight: 700;
            margin: 5px 0;
        }
        
        .stat-label {
            color: #7f8c8d;
            font-size: 14px;
        }
        
        /* Orders Section */
        .section {
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            margin-bottom: 30px;
        }
        
        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 1px solid #ecf0f1;
        }
        
        .section-title {
            font-size: 20px;
            font-weight: 600;
        }
        
        /* Orders Grid */
        .orders-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        
        .order-card {
            flex: 1 1 300px;
            min-width: 300px;
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
            transition: all 0.2s ease;
            border-left: 4px solid #4361ee;
        }
        
        .order-card:hover {
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
        }
        
        .order-card.delivered { border-left-color: #2ecc71; }
        .order-card.pending { border-left-color: #3498db; }
        .order-card.cancelled { border-left-color: #e74c3c; }
        
        .order-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        
        .order-id {
            font-weight: 600;
        }
        
        .order-status {
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 600;
            text-transform: uppercase;
        }
        
        .status-delivered {
            background-color: #e8f5e9;
            color: #2ecc71;
        }
        
        .status-pending {
            background-color: #e3f2fd;
            color: #3498db;
        }
        
        .status-cancelled {
            background-color: #ffebee;
            color: #e74c3c;
        }
        
        .order-detail {
            display: flex;
            justify-content: space-between;
            margin: 10px 0;
            font-size: 14px;
        }
        
        .order-detail-label {
            color: #7f8c8d;
            font-weight: 500;
        }
        
        .order-actions {
            margin-top: 15px;
            display: flex;
            justify-content: flex-end;
        }
        
        .view-link {
            color: #4361ee;
            text-decoration: none;
            font-weight: 500;
            font-size: 14px;
            display: flex;
            align-items: center;
            gap: 5px;
        }
        
        .view-link:hover {
            text-decoration: underline;
        }
        
        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .dashboard-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 15px;
            }
            
            .stat-card {
                min-width: 100%;
            }
            
            .order-card {
                min-width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="dashboard">
        <!-- Dashboard Header -->
        <div class="dashboard-header">
            <h1 class="dashboard-title">Admin Dashboard</h1>
        </div>
        
        <!-- Stats Cards -->
        <div class="stats-container">
            <div class="stat-card">
                <div class="stat-header">
                    <div>
                        <div class="stat-value">${totalProducts}</div>
                        <div class="stat-label">Total Products</div>
                    </div>
                    <div class="stat-icon products">
                        <i class="fas fa-box"></i>
                    </div>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-header">
                    <div>
                        <div class="stat-value">${totalCustomers}</div>
                        <div class="stat-label">Total Customers</div>
                    </div>
                    <div class="stat-icon customers">
                        <i class="fas fa-users"></i>
                    </div>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-header">
                    <div>
                        <div class="stat-value">${totalOrders}</div>
                        <div class="stat-label">New Orders</div>
                    </div>
                    <div class="stat-icon orders">
                        <i class="fas fa-shopping-cart"></i>
                    </div>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-header">
                    <div>
                        <div class="stat-value">${totalRevenue}</div>
                        <div class="stat-label">Total Revenue</div>
                    </div>
                    <div class="stat-icon revenue">
                        <i class="fas fa-dollar-sign"></i>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Recent Orders Section -->
        <div class="section">
            <div class="section-header">
                <h2 class="section-title">Recent Orders</h2>
                <a href="admin-orders" class="btn btn-secondary">View All</a>
            </div>
            
            <div class="orders-grid">
                <c:forEach var="order" items="${newOrders}">
                    <div class="order-card 
                        <c:choose>
                            <c:when test="${order.status == 'Delivered'}">delivered</c:when>
                            <c:when test="${order.status == 'Cancelled'}">cancelled</c:when>
                            <c:otherwise>pending</c:otherwise>
                        </c:choose>">
                        <div class="order-header">
                            <span class="order-id">#${order.id}</span>
                            <span class="order-status 
                                <c:choose>
                                    <c:when test="${order.status == 'Delivered'}">status-delivered</c:when>
                                    <c:when test="${order.status == 'Cancelled'}">status-cancelled</c:when>
                                    <c:otherwise>status-pending</c:otherwise>
                                </c:choose>">
                                ${order.status}
                            </span>
                        </div>
                        
                        <div class="order-detail">
                            <span class="order-detail-label">Customer:</span>
                            <span>${order.customerName}</span>
                        </div>
                        
                        <div class="order-detail">
                            <span class="order-detail-label">Product:</span>
                            <span>${order.productName}</span>
                        </div>
                        
                        <div class="order-detail">
                            <span class="order-detail-label">Date:</span>
                            <span><fmt:formatDate value="${order.date}" pattern="MMM d, yyyy" /></span>
                        </div>
                        
                        <div class="order-detail">
                            <span class="order-detail-label">Amount:</span>
                            <span>$${order.amount}</span>
                        </div>
                        
                        <div class="order-actions">
                            <a href="admin-orders" class="view-link">
                                <i class="fas fa-eye"></i> View Details
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>