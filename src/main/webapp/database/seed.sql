USE ecommerce_db;

-- Insert sample users
INSERT INTO users (first_name, last_name, email, password, phone, address, role, username, dob, gender)
VALUES
('John', 'Doe', 'staff@gmail.com', 'XTh96TskTjbEnD+j/3dYOA==', '1234567890', '123 Main St, Anytown', 'STAFF', 'johndoe', '1990-01-15', 'Male'),
('Jane', 'Smith', 'customer@gmail.com', 'l1TO7kXZtUSWKybm64+T7Q==', '2345678901', '456 Oak Ave, Somewhere', 'CUSTOMER', 'janesmith', '1985-05-20', 'Female'),
('Admin', 'User', 'admin@gmail.com', 'jwxESPOJRAGrVOeehrj5kg==', '3456789012', '789 Admin Blvd, Adminville', 'ADMIN', 'admin', '1980-10-10', 'Male');
--admin password -> admin@123
--customer password -> customer@123
--staff password -> staff@123


-- Insert sample brands
INSERT INTO brands (brand_name)
VALUES
('Nike'),
('Adidas'),
('Apple'),
('Samsung'),
('Sony'),
('Dell');

-- Insert sample categories
INSERT INTO categories (name, description)
VALUES
('Electronics', 'Devices and gadgets'),
('Clothing', 'Apparel and fashion items'),
('Footwear', 'Shoes and boots'),
('Computers', 'PCs, laptops and accessories'),
('Home Appliances', 'Household electronic devices');

-- Insert sample products
INSERT INTO products (name, description, price, stock, category_id, brand_id, imageURL)
VALUES
('iPhone 13', 'Latest Apple smartphone with A15 Bionic chip', 999.99, 50, 1, 3, '/assets/images/carousel-item1.jpg'),
('Galaxy S22', 'Samsung flagship smartphone with AMOLED display', 899.99, 40, 1, 4, '/assets/images/carousel-item2.png'),
('Air Max 90', 'Classic Nike sneakers with air cushioning', 120.00, 100, 3, 1, '/assets/images/carousel-item3.jpeg'),
('Ultraboost 22', 'Adidas running shoes with Boost technology', 180.00, 80, 3, 2, 'ultraboost22.jpg'),
('XPS 13', 'Dell premium ultrabook with InfinityEdge display', 1299.99, 30, 4, 6, 'xps13.jpg'),
('PlayStation 5', 'Sony next-gen gaming console', 499.99, 20, 1, 5, 'ps5.jpg');

-- Insert sample carousel items
INSERT INTO carousel_items (title, description, image_url)
VALUES
('Summer Sale', 'Up to 50% off on selected items', '/assets/images/carousel-item1.jpg'),
('New Arrivals', 'Check out our latest products', '/assets/images/carousel-item2.png'),
('Limited Offer', 'Special deals for limited time', '/assets/images/carousel-item3.jpeg');

-- Insert sample delivery details
INSERT INTO delivery_details (address, phone, delivery_status, delivery_date)
VALUES
('123 Main St, Anytown', '1234567890', 'Delivered', '2023-05-15 14:30:00'),
('456 Oak Ave, Somewhere', '2345678901', 'Pending', '2023-05-20 10:00:00'),
('789 Admin Blvd, Adminville', '3456789012', 'Pending', '2023-05-20 10:00:00');


-- Insert sample orders
INSERT INTO orders (user_id, total_price, delivery_id)
VALUES
(2, 999.99, 1),
(2, 120.00, 2),
(3, 499.99, 3);

-- Insert sample order items
INSERT INTO order_items (order_id, product_id, quantity, price, subtotal)
VALUES
(1, 1, 1, 999.99, 999.99),
(2, 3, 1, 120.00, 120.00),
(3, 6, 1, 499.99, 499.99);

-- Insert sample cart items
INSERT INTO cart (user_id, product_id, quantity)
VALUES
(2, 2, 1),
(2, 4, 2),
(3, 5, 1);