-- Insert categories
INSERT INTO categories (id, name)
VALUES (1, 'Fruits'),
       (2, 'Vegetables'),
       (3, 'Dairy'),
       (4, 'Bakery'),
       (5, 'Beverages');

-- Insert products
INSERT INTO products (name, price, description, category_id)
VALUES ('Apple', 1.20, 'Fresh red apples, crisp and sweet.', 1),
       ('Banana', 0.80, 'Ripe yellow bananas, perfect for snacking.', 1),
       ('Carrot', 0.50, 'Crunchy orange carrots, rich in vitamin A.', 2),
       ('Broccoli', 1.50, 'Fresh green broccoli florets.', 2),
       ('Milk', 2.30, '1 liter of whole cow’s milk.', 3),
       ('Cheddar Cheese', 4.50, 'Aged cheddar cheese block, 200g.', 3),
       ('Bread Loaf', 2.00, 'Soft white bread loaf, freshly baked.', 4),
       ('Croissant', 1.80, 'Buttery French croissant, flaky layers.', 4),
       ('Orange Juice', 3.20, '1 liter of 100% pure orange juice.', 5),
       ('Green Tea', 2.70, 'Pack of 20 green tea bags.', 5);