-- Insert categories
INSERT INTO categories (id, name)
VALUES (3, 'Fruits'),
       (4, 'Vegetables'),
       (5, 'Dairy'),
       (6, 'Bakery'),
       (7, 'Beverages');

-- Insert products
INSERT INTO products (name, price, description, category_id)
VALUES ('Apple', 1.20, 'Fresh red apples, crisp and sweet.', 3),
       ('Banana', 0.80, 'Ripe yellow bananas, perfect for snacking.', 3),
       ('Carrot', 0.50, 'Crunchy orange carrots, rich in vitamin A.', 4),
       ('Broccoli', 1.50, 'Fresh green broccoli florets.', 4),
       ('Milk', 2.30, '1 liter of whole cow’s milk.', 5),
       ('Cheddar Cheese', 4.50, 'Aged cheddar cheese block, 200g.', 5),
       ('Bread Loaf', 2.00, 'Soft white bread loaf, freshly baked.', 6),
       ('Croissant', 1.80, 'Buttery French croissant, flaky layers.', 6),
       ('Orange Juice', 3.20, '1 liter of 100% pure orange juice.', 7),
       ('Green Tea', 2.70, 'Pack of 20 green tea bags.', 7);