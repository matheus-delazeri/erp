-- Creating Product table
CREATE TABLE product (
                         product_id INT PRIMARY KEY AUTO_INCREMENT,
                         sku VARCHAR(50) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Creating Stock table
CREATE TABLE stock (
                       item_id INT PRIMARY KEY AUTO_INCREMENT,
                       product_id INT,
                       qty DECIMAL(10, 2) NOT NULL,
                       is_in_stock BOOLEAN DEFAULT TRUE,
                       manage_stock BOOLEAN DEFAULT TRUE,
                       FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Inserting sample data into Product table
INSERT INTO product (sku, name, description, price)
VALUES
    ('SKU001', 'Product 1', 'Description for Product 1', 25.99),
    ('SKU002', 'Product 2', 'Description for Product 2', 35.49),
    ('SKU003', 'Product 3', 'Description for Product 3', 45.79);

-- Inserting sample data into Stock table
INSERT INTO stock (product_id, qty)
VALUES
    (1, 100),  -- Product 1, quantity 100
    (2, 75),   -- Product 2, quantity 75
    (3, 50);   -- Product 3, quantity 50
