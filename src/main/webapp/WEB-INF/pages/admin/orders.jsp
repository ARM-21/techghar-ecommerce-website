<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Orders</title>
    <style>
        /* color variables */
        :root {
            --primary-color: #4b6584;
            --secondary-color: #f7f7f7;
            --accent-color: #3498db;
            --success-color: #2ecc71;
            --warning-color: #f39c12;
            --danger-color: #e74c3c;
            --text-color: #333;
            --border-color: #ddd;
        }
        
        /* Basic page styling */
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: var(--text-color);
            background-color: #f0f2f5;
            margin: 0;
            padding: 20px;
        }
        
        /* Container styling */
        .main-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        
        /* Page title */
        h2 {
            color: var(--primary-color);
            margin-top: 0;
            padding-bottom: 10px;
            border-bottom: 2px solid var(--border-color);
        }
        
        /* Order box styling */
        .order-box {
            border: 1px solid var(--border-color);
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 25px;
            background-color: white;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
        }
        
        /* Order header */
        .order-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 1px solid var(--border-color);
        }
        
        /* Order ID style */
        .order-id {
            font-size: 18px;
            font-weight: bold;
            color: var(--primary-color);
        }
        
        /* Order details grid */
        .order-details {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 15px;
            margin-bottom: 20px;
        }
        
        /* Detail item style */
        .detail-item {
            margin-bottom: 10px;
        }
        
        /* Labels */
        .detail-label {
            font-weight: bold;
            margin-bottom: 5px;
            color: var(--text-color);
            display: block;
        }
        
        /* Values */
        .detail-value {
            color: #555;
        }
        
        /* Status badges */
        .status-badge {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 15px;
            font-size: 14px;
            font-weight: bold;
            background-color: #e8f5e9;
            color: var(--success-color);
        }
        
        .status-PENDING {
            background-color: #fff8e1;
            color: var(--warning-color);
        }
        
        .status-SHIPPED {
            background-color: #e3f2fd;
            color: var(--accent-color);
        }
        
        .status-OUT.FOR.DELIVERY {
            background-color: #e0f7fa;
            color: #00acc1;
        }
        
        .status-DELIVERED {
            background-color: #e8f5e9;
            color: var(--success-color);
        }
        
        .status-CANCELLED {
            background-color: #ffebee;
            color: var(--danger-color);
        }
        
        .status-RETURNED {
            background-color: #fce4ec;
            color: #ec407a;
        }
        
        /* Update form styling */
        .update-form {
            margin: 15px 0;
            padding: 15px;
            background-color: var(--secondary-color);
            border-radius: 5px;
            border: 1px solid var(--border-color);
        }
        
        /* Form controls */
        select {
            padding: 8px 10px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            margin-right: 10px;
            margin-left: 5px;
        }
        
        button {
            padding: 8px 12px;
            background-color: var(--accent-color);
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        
        button:hover {
            background-color: #2980b9;
        }
        
        /* Section headings */
        h4 {
            margin: 20px 0 10px;
            color: var(--primary-color);
            border-bottom: 1px solid var(--border-color);
            padding-bottom: 5px;
        }
        
        /* Table styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
        }
        
        th {
            background-color: var(--secondary-color);
            color: var(--primary-color);
            font-weight: bold;
            text-align: left;
            padding: 12px 10px;
            border: 1px solid var(--border-color);
        }
        
        td {
            padding: 10px;
            border: 1px solid var(--border-color);
            vertical-align: middle;
        }
        
        /* Alternating row colors */
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        /* Hover effect for rows */
        tbody tr:hover {
            background-color: #f0f7ff;
        }
        
        /* Responsive adjustments */
        @media (max-width: 768px) {
            .order-details {
                grid-template-columns: 1fr;
            }
            
            .main-container {
                padding: 15px;
            }
            
            body {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h2>Manage Orders</h2>
        
        <c:forEach var="order" items="${orders}">
            <div class="order-box">
                <div class="order-header">
                    <span class="order-id">Order #${order.id}</span>
                    
                    <c:if test="${order.deliveryModel != null}">
                        <!-- Set status class based on delivery status -->
                        <c:set var="statusClass" value="status-${order.deliveryModel.deliveryStatus}" />
                        <span class="status-badge ${statusClass}">${order.deliveryModel.deliveryStatus}</span>
                    </c:if>
                </div>
                
                <div class="order-details">
                    <div class="detail-item">
                        <span class="detail-label">Order ID</span>
                        <span class="detail-value">${order.id}</span>
                    </div>
                    
                    <div class="detail-item">
                        <span class="detail-label">User ID</span>
                        <span class="detail-value">${order.userId}</span>
                    </div>
                    
                    <div class="detail-item">
                        <span class="detail-label">Total Price</span>
                        <span class="detail-value">$${order.totalAmount}</span>
                    </div>
                    
                    <div class="detail-item">
                        <span class="detail-label">Order Date</span>
                        <span class="detail-value">${order.orderDate}</span>
                    </div>
                    
                    <c:if test="${order.deliveryModel != null}">
                        <div class="detail-item">
                            <span class="detail-label">Delivery Address</span>
                            <span class="detail-value">${order.deliveryModel.address}</span>
                        </div>
                        
                        <div class="detail-item">
                            <span class="detail-label">Phone</span>
                            <span class="detail-value">${order.deliveryModel.phone}</span>
                        </div>
                        
                        <div class="detail-item">
                            <span class="detail-label">Delivery Date</span>
                            <span class="detail-value">${order.deliveryModel.deliveryDate}</span>
                        </div>
                    </c:if>
                </div>
                
                <c:if test="${order.deliveryModel != null}">
                    <div class="update-form">
                        <form action="admin-orders" method="post">
                            <input type="hidden" name="orderId" value="${order.id}" />
                            <input type="hidden" name="deliveryId" value="${order.deliveryModel.deliveryId}" />
                            
                            <label for="statusSelect">Update Status:</label>
                            <select name="deliveryStatus" id="statusSelect">
                                <option value="PENDING" ${order.deliveryModel.deliveryStatus == 'PENDING' ? 'selected' : ''}>PENDING</option>
                                <option value="SHIPPED" ${order.deliveryModel.deliveryStatus == 'SHIPPED' ? 'selected' : ''}>SHIPPED</option>
                                <option value="OUT FOR DELIVERY" ${order.deliveryModel.deliveryStatus == 'OUT FOR DELIVERY' ? 'selected' : ''}>OUT FOR DELIVERY</option>
                                <option value="DELIVERED" ${order.deliveryModel.deliveryStatus == 'DELIVERED' ? 'selected' : ''}>DELIVERED</option>
                                <option value="CANCELLED" ${order.deliveryModel.deliveryStatus == 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                                <option value="RETURNED" ${order.deliveryModel.deliveryStatus == 'RETURNED' ? 'selected' : ''}>RETURNED</option>
                            </select>
                            <button type="submit">Update Status</button>
                        </form>
                    </div>
                </c:if>

                <h4>Order Items</h4>
                <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${order.orderItems}">
                            <tr>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>$${item.price}</td>
                                <td>$${item.subtotal}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</body>
</html>
