USE ecommerce_db;
-- Insert sample users
-- Insert sample users (must come first as they're referenced by other tables)
INSERT INTO users (first_name, last_name, email, password, phone, address, role, username, dob, gender)
VALUES
('John', 'Doe', 'staff@gmail.com', 'XTh96TskTjbEnD+j/3dYOA==', '1234567890', '123 Main St, Anytown', 'STAFF', 'johndoe', '1990-01-15', 'Male'),
('Jane', 'Smith', 'customer@gmail.com', 'l1TO7kXZtUSWKybm64+T7Q==', '2345678901', '456 Oak Ave, Somewhere', 'CUSTOMER', 'janesmith', '1985-05-20', 'Female'),
('Admin', 'User', 'admin@gmail.com', 'jwxESPOJRAGrVOeehrj5kg==', '3456789012', '789 Admin Blvd, Adminville', 'ADMIN', 'admin', '1980-10-10', 'Male');

-- Password notes:
-- admin password -> admin@123
-- customer password -> customer@123
-- staff password -> staff@123

-- Insert sample brands (referenced by products)
INSERT INTO brands (brand_name)
VALUES
('Nike'),
('Adidas'),
('Apple'),
('Samsung'),
('Sony'),
('Dell');

-- Insert sample categories (referenced by products)
INSERT INTO categories (name, description)
VALUES
('Electronics', 'Devices and gadgets'),
('Clothing', 'Apparel and fashion items'),
('Footwear', 'Shoes and boots'),
('Computers', 'PCs, laptops and accessories'),
('Home Appliances', 'Household electronic devices');

-- Insert sample products (referenced by order_items and cart)
INSERT INTO products (name, description, price, stock, category_id, brand_id, imageURL)
VALUES
('iPhone 13', 'Latest Apple smartphone with A15 Bionic chip', 999.99, 50, 1, 3, '/assets/images/products/iphone13.jpg'),
('Galaxy S22', 'Samsung flagship smartphone with AMOLED display', 899.99, 40, 1, 4, '/assets/images/products/galaxy-s22.jpg'),
('Air Max 90', 'Classic Nike sneakers with air cushioning', 120.00, 100, 3, 1, '/assets/images/products/airmax90.jpg'),
('Ultraboost 22', 'Adidas running shoes with Boost technology', 180.00, 80, 3, 2, '/assets/images/products/ultraboost22.jpg'),
('XPS 13', 'Dell premium ultrabook with InfinityEdge display', 1299.99, 30, 4, 6, '/assets/images/products/xps13.jpg'),
('PlayStation 5', 'Sony next-gen gaming console', 499.99, 20, 1, 5, '/assets/images/products/ps5.jpg');

-- Insert sample carousel items (independent table)
INSERT INTO carousel_items (title, description, image_url)
VALUES
('Summer Sale', 'Up to 50% off on selected items', '/assets/images/carousel/summer-sale.jpg'),
('New Arrivals', 'Check out our latest products', '/assets/images/carousel/new-arrivals.jpg'),
('Limited Offer', 'Special deals for limited time', '/assets/images/carousel/limited-offer.jpg');

-- Insert sample delivery details (referenced by orders)
INSERT INTO delivery_details (address, phone, delivery_status, delivery_date)
VALUES
('123 Main St, Anytown', '1234567890', 'Delivered', '2023-05-15 14:30:00'),
('456 Oak Ave, Somewhere', '2345678901', 'Pending', '2023-05-20 10:00:00'),
('789 Admin Blvd, Adminville', '3456789012', 'Pending', '2023-05-20 10:00:00');

-- Insert sample orders (referenced by order_items, references users and delivery_details)
INSERT INTO orders (user_id, total_price, delivery_id)
VALUES
(2, 999.99, 1),  -- Jane Smith's order (user_id 2)
(2, 120.00, 2),  -- Jane Smith's second order
(3, 499.99, 3);  -- Admin User's order (user_id 3)

-- Insert sample order items (references orders and products)
INSERT INTO order_items (order_id, product_id, quantity, price, subtotal)
VALUES
(1, 1, 1, 999.99, 999.99),  -- Order 1: iPhone 13
(2, 3, 1, 120.00, 120.00),   -- Order 2: Air Max 90
(3, 6, 1, 499.99, 499.99);    -- Order 3: PlayStation 5

-- Insert sample cart items (references users and products)
INSERT INTO cart (user_id, product_id, quantity)
VALUES
(2, 2, 1),  -- Jane Smith has Galaxy S22 in cart
(2, 4, 2),  -- Jane Smith also has 2 Ultraboost 22 shoes
(3, 5, 1);  -- Admin User has XPS 13 in cart